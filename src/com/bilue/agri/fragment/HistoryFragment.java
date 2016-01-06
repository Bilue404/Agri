package com.bilue.agri.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bilue.agri.adapter.HistoryFragmentAadapter;
import com.bilue.agri.util.MyChartView;
import com.bilue.bean.Mysensor;
import com.bilue.database.DatabaseConfig;
import com.bilue.database.Mydatabase;
import com.bilue.database.SensorConfig;
import com.bilue.agri.R;

public class HistoryFragment extends Fragment {

	private static HistoryFragment instance = null;

	private Button selectButton; // ��ѯ��ť
	private Spinner typeSpinner; // ����������
	private Spinner timeSpinner; // ʱ��
	private ViewPager historyPage;

	private String[] types = { SensorConfig.Air_temp,
			SensorConfig.Air_humidity, SensorConfig.Soil_temp,
			SensorConfig.Soil_humidity, SensorConfig.light, SensorConfig.Co2 };
	private String[] times = { "60��", "5����" };

	private String type = SensorConfig.Air_temp;
	private String time = "60��";

	private ArrayList<Mysensor> mySensors;
	private MyChartView myChartView;
	private ArrayList<View> items;
	
	private HistoryFragment() {

	}

	public static HistoryFragment getInstance() {
		if (instance == null) {
			instance = new HistoryFragment();
		}
		return instance;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_history, container,
				false);

		initView(view);

		return view;
	}

	/**
	 * ��ʼ���������
	 * 
	 * @param view
	 */
	private void initView(View view) {
		// TODO Auto-generated method stub
		selectButton = (Button) view.findViewById(R.id.history_select);
		historyPage = (ViewPager) view.findViewById(R.id.history_view);

		selectButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				searchHistory();
			}
		});

		typeSpinner = (Spinner) view.findViewById(R.id.history_sensortype);
		typeSpinner.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, types));

		timeSpinner = (Spinner) view.findViewById(R.id.history_time);
		timeSpinner.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, times));

		typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				type = (String) arg0.getItemAtPosition(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		timeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				time = (String) arg0.getItemAtPosition(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
	}

	/**
	 * ��ѯ��ʷ���
	 */
	protected void searchHistory() {
		mySensors = query(type, time);
		myChartView = new MyChartView(getActivity(), type, mySensors);
		View lineView = myChartView.getHistoryLineView();
		View barView = myChartView.getHistoryBarView();
		View pieView = myChartView.getHistoryPieView();
		items = new ArrayList<View>();
		items.add(lineView);
		items.add(barView);
		items.add(pieView);

		historyPage.setAdapter(new HistoryFragmentAadapter(items));
	}

	private ArrayList<Mysensor> query(String type2, String time2) {
		Mydatabase mydatabase = new Mydatabase(getActivity());

		if (times[0].equals(time2)) {
			ArrayList<Mysensor> list = mydatabase.query60(type2);
			Log.e("��С", list.size() + "a");
			return list;
		} else {
			ArrayList<Mysensor> list = mydatabase.query5(type2);
			Log.e("��С", list.size() + "a");
			return list;
		}
	}
}

package com.bilue.agri.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.bilue.agri.activity.RealTimeActivity;
import com.bilue.agri.adapter.HomeFragmentAdapter;
import com.bilue.bean.Sensor;
import com.bilue.bean.SensorView;
import com.bilue.agri.R;

public class HomeFragment extends Fragment {

	private static HomeFragment instance = null;
	private View view;
	private GridView homeBodyView;
	private ArrayList<SensorView> items;
	private Context context;
	private HomeFragmentAdapter homeFragmentAdapter;

	private HomeFragment() {
	}

	public static HomeFragment getInstance() {
		if (instance == null) {
			instance = new HomeFragment();
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_home, null);
		context = getActivity();
		initView();
		return view;
	}

	private void initView() {
		homeBodyView = (GridView) view.findViewById(R.id.fragment_home_body);

		if (items == null) {
			items = new ArrayList<SensorView>();

			items.add(new SensorView("�����¶�", 7));
			items.add(new SensorView("����ʪ��", 7));
			items.add(new SensorView("�����¶�", 7));
			items.add(new SensorView("����ʪ��", 7));
			items.add(new SensorView("����ǿ��", 7));
			items.add(new SensorView("CO2Ũ��", 7));
		}

		homeFragmentAdapter = new HomeFragmentAdapter(items, context);
		homeBodyView.setAdapter(homeFragmentAdapter);
		homeBodyView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent it = new Intent(context, RealTimeActivity.class);
				it.putExtra("sensorStyle", arg2);
				startActivity(it);
			}

		});
	}

	public void refresh(Sensor sensor) {
		items.get(0).setSensorValue(sensor.getAirTemp());
		items.get(1).setSensorValue(sensor.getAirHumid());
		items.get(2).setSensorValue(sensor.getSoilTemp());
		items.get(3).setSensorValue(sensor.getSoilHumid());
		items.get(4).setSensorValue(sensor.getLight());
		items.get(5).setSensorValue(sensor.getCo2());
		homeFragmentAdapter.notifyDataSetChanged();
	}

}

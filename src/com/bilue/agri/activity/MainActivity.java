package com.bilue.agri.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.bilue.agri.MyApplication;
import com.bilue.agri.fragment.ControlFragment;
import com.bilue.agri.fragment.HistoryFragment;
import com.bilue.agri.fragment.HomeFragment;
import com.bilue.agri.fragment.SettingFragment;
import com.bilue.agri.server.GetSensorService;
import com.bilue.bean.Sensor;
import com.bilue.agri.R;

public class MainActivity extends BaseActivity implements OnClickListener {

	private TextView homeView;
	private TextView historyView;
	private TextView control;
	private TextView settingView;

	private FragmentManager fm;
	private FragmentTransaction ft;

	private int currentIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

		// ��������
		startService(new Intent(MainActivity.this, GetSensorService.class));
	}

	private void initView() {
		homeView = (TextView) findViewById(R.id.main_home);
		historyView = (TextView) findViewById(R.id.main_history);
		control = (TextView) findViewById(R.id.main_control);
		settingView = (TextView) findViewById(R.id.main_setting);

		homeView.setOnClickListener(this);
		historyView.setOnClickListener(this);
		control.setOnClickListener(this);
		settingView.setOnClickListener(this);

		fm = getFragmentManager();
		ft = fm.beginTransaction();
		ft.replace(R.id.main_body, HomeFragment.getInstance());
		homeView.setTextColor(Color.BLUE);
		ft.commit();
	}

	@Override
	public void onClick(View arg0) {
		fm = getFragmentManager();
		ft = fm.beginTransaction();
		switch (arg0.getId()) {
		case R.id.main_home:

			sta();
			if (currentIndex != 0) {
				ft.replace(R.id.main_body, HomeFragment.getInstance());
				homeView.setTextColor(Color.BLUE);
				historyView.setTextColor(Color.BLACK);
				control.setTextColor(Color.BLACK);
				settingView.setTextColor(Color.BLACK);
				currentIndex = 0;
			}

			break;
		case R.id.main_history:
			if (currentIndex != 1) {
				ft.replace(R.id.main_body, HistoryFragment.getInstance());
				homeView.setTextColor(Color.BLACK);
				historyView.setTextColor(Color.BLUE);
				control.setTextColor(Color.BLACK);
				settingView.setTextColor(Color.BLACK);
				currentIndex = 1;
			}
			break;
		case R.id.main_control:

			ControlFragment.getInstance().updateStatus();

			if (currentIndex != 2) {
				ft.replace(R.id.main_body, ControlFragment.getInstance());
				homeView.setTextColor(Color.BLACK);
				historyView.setTextColor(Color.BLACK);
				control.setTextColor(Color.BLUE);
				settingView.setTextColor(Color.BLACK);
				currentIndex = 2;
			}
			break;
		case R.id.main_setting:
			if (currentIndex != 3) {
				ft.replace(R.id.main_body, SettingFragment.getInstance());
				homeView.setTextColor(Color.BLACK);
				historyView.setTextColor(Color.BLACK);
				control.setTextColor(Color.BLACK);
				settingView.setTextColor(Color.BLUE);
				currentIndex = 3;
			}
			break;
		default:
			break;
		}
		ft.commit();
	}

	private void sta() {
		if (MyApplication.handler == null) {
			MyApplication.handler = mHandler;
		} else {
			if (MyApplication.handler != mHandler) {
				MyApplication.handler = mHandler;
			}
		}
	}

	@Override
	protected void sensorUpdate(Sensor sensor) {
		Log.i(TAG, "�յ�Sensor����ֵ....");
		HomeFragment.getInstance().refresh(sensor);
	}

	@Override
	protected void update(Object ob) {
		// TODO Auto-generated method stub

	}

}

package com.bilue.agri.activity;

import java.util.ArrayList;
import java.util.List;

import com.bilue.agri.util.MyChartView;
import com.bilue.bean.Mysensor;
import com.bilue.bean.Sensor;
import com.bilue.agri.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class RealTimeActivity extends BaseActivity {

	private ViewPager viewPager = null;

	private MyChartView airTemp;
	private MyChartView airHumd;
	private MyChartView soidTemp;
	private MyChartView soidHumd;
	private MyChartView light;
	private MyChartView co2;

	private View airTempView;
	private View airHumdView;
	private View soidTempView;
	private View soidHumdView;
	private View lightView;
	private View co2View;

	private ArrayList<View> items;
	private int postion = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realtime);

		postion = getIntent().getExtras().getInt("sensorStyle");
		
		initView();
	}

	private void initView() {
		items = new ArrayList<View>();

		airTemp = new MyChartView(this, "�����¶�");
		airHumd = new MyChartView(this, "����ʪ��");
		soidTemp = new MyChartView(this, "�����¶�");
		soidHumd = new MyChartView(this, "����ʪ��");
		light = new MyChartView(this, "����ǿ��");
		co2 = new MyChartView(this, "������̼");

		airTempView = airTemp.getRealTimeView();
		airHumdView = airHumd.getRealTimeView();
		soidTempView = soidTemp.getRealTimeView();
		soidHumdView = soidHumd.getRealTimeView();
		lightView = light.getRealTimeView();
		co2View = co2.getRealTimeView();

		items.add(airTempView);
		items.add(airHumdView);
		items.add(soidTempView);
		items.add(soidHumdView);
		items.add(lightView);
		items.add(co2View);

		viewPager = (ViewPager) findViewById(R.id.realtime_viewpage);
		viewPager.setAdapter(new MyAdapter(items));
		viewPager.setCurrentItem(postion);
	}

	class MyAdapter extends PagerAdapter {
		List<View> items;

		public MyAdapter(ArrayList<View> items) {
			this.items = items;
		}

		@Override
		public int getCount() {
			return items.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(items.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(items.get(position));
			return items.get(position);
		}

	}

	@Override
	protected void sensorUpdate(Sensor sensor) {
		airTemp.update(new Mysensor(sensor.getTime(), sensor.getAirTemp()));
		airHumd.update(new Mysensor(sensor.getTime(), sensor.getAirHumid()));
		soidTemp.update(new Mysensor(sensor.getTime(), sensor.getSoilTemp()));
		soidHumd.update(new Mysensor(sensor.getTime(), sensor.getSoilHumid()));
		light.update(new Mysensor(sensor.getTime(), sensor.getLight()));
		co2.update(new Mysensor(sensor.getTime(), sensor.getCo2()));
		for (int i = 0; i < items.size(); i++) {
			View view = items.get(i);
			view.invalidate();
		}

	}

	@Override
	protected void update(Object ob) {
		// TODO Auto-generated method stub

	}
}

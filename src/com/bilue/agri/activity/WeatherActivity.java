package com.bilue.agri.activity;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilue.agri.request.GetWeatherRequest;
import com.bilue.bean.Weather;
import com.bilue.agri.R;

public class WeatherActivity extends BaseActivity {

	private GridView myGridView;
	private ArrayList<Weather> weathers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);

		initView();
	}

	private void initView() {
		myGridView = (GridView) findViewById(R.id.weather_gridview);
		weathers = new ArrayList<Weather>();
		//
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		GetWeatherRequest getWeatherRequest = new GetWeatherRequest();
		startRequest(getWeatherRequest);
	}

	@Override
	protected void update(Object ob) {
		weathers = (ArrayList<Weather>) ob;
		myGridView.setAdapter(new MyAdapter(this, weathers));
	}

	class MyAdapter extends BaseAdapter {

		private List<Weather> weathers;
		private LayoutInflater inflater;
		private Context context;

		public MyAdapter(Context context, ArrayList<Weather> weathers) {
			this.weathers = weathers;
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return weathers.size();
		}

		@Override
		public Object getItem(int arg0) {
			return weathers.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			Weather weather = weathers.get(arg0);
			View view = inflater.inflate(R.layout.adapter_weacther_item, null);
			ImageView im = (ImageView) view
					.findViewById(R.id.adapter_weather_im);
			TextView tv = (TextView) view.findViewById(R.id.adapter_weather_tv);

			switch (weather.getWeather()) {
			case Weather.CLEAR:
				im.setImageResource(R.drawable.ic_launcher);
				break;
			case Weather.RAIN:
				im.setImageResource(R.drawable.ic_launcher);
				break;
			default:
				im.setImageResource(R.drawable.ic_launcher);
				break;
			}
			tv.setText(weather.getInfo());
			return view;
		}

	}

}

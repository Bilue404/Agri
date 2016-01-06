package com.bilue.agri.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bilue.bean.SensorView;
import com.bilue.agri.R;

public class HomeFragmentAdapter extends BaseAdapter{

	private List<SensorView> items;
	private Context context;
	private LayoutInflater inflater;
	
	public HomeFragmentAdapter(List<SensorView> items, Context context) {
		this.items = items;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHodler viewHodler;
		SensorView sensorView = items.get(position);
		if(convertView==null){
			convertView = inflater.inflate(R.layout.adapter_fragment_home_item, null);
			viewHodler = new ViewHodler();
			viewHodler.sensorNameView = (TextView) convertView.findViewById(R.id.adapter_fragment_home_name);
			viewHodler.sensorValueView = (TextView) convertView.findViewById(R.id.adapter_fragment_home_value);
		
			convertView.setTag(viewHodler);
		}
		else{
			viewHodler = (ViewHodler) convertView.getTag();
		}
		
		viewHodler.sensorNameView.setText(sensorView.getSensorName());
		viewHodler.sensorValueView.setText(sensorView.getSensorValue()+"");
		
		return convertView;

	}
	
	class ViewHodler{
		public TextView sensorNameView;
		public TextView sensorValueView;
	}

}

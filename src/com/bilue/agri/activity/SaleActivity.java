package com.bilue.agri.activity;

import java.util.ArrayList;
import java.util.List;

import com.bilue.bean.MySale;
import com.bilue.agri.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class SaleActivity extends Activity{
	
	private ListView myListView ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale);
		
	}
	
	private void init(){
		myListView = (ListView) findViewById(R.id.activity_sale_mylist);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	class MyAdapter extends BaseAdapter{

		private List<MySale> sales;
		private Context context;
		private LayoutInflater inflater;
		
		public MyAdapter(Context context ,ArrayList<MySale> sales) {
			this.context = context;
			this.sales = sales;
			inflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			return sales.size();
		}

		@Override
		public Object getItem(int arg0) {
			return sales.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			
			MySale mySale = sales.get(arg0);
			View view = inflater.inflate(R.layout.adapter_sale_item, null);
			
			
			
			
			return view;
		}
		
	}
		
}

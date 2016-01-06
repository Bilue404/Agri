package com.bilue.agri.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class HistoryFragmentAadapter extends PagerAdapter{

	private List<View> lists;
	
	public HistoryFragmentAadapter(ArrayList<View> lists) {
		this.lists = lists;
	}
	
	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {

//		super.destroyItem(container, position, object);
		container.removeView(lists.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		
		container.addView(lists.get(position));
		return lists.get(position);
	}

}

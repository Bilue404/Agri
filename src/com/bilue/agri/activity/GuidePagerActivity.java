package com.bilue.agri.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bilue.agri.MyApplication;
import com.bilue.agri.server.GetNotificationService;
import com.bilue.agri.R;

public class GuidePagerActivity extends Activity {

	List<View> viewlist = new ArrayList<View>();
	Button loginButton = null;
	ImageView point1, point2, point3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// �ж��Ƿ���ʾ����ҳ
		if (!MyApplication.share.getShow()) {
			finish();
			startActivity(new Intent(getApplicationContext(),
					LoginActivity.class));
		}

		setContentView(R.layout.activity_guidepager);

		point1 = (ImageView) findViewById(R.id.guidepagerpoint1);
		point2 = (ImageView) findViewById(R.id.guidepagerpoint2);
		point3 = (ImageView) findViewById(R.id.guidepagerpoint3);

		ViewPager viewPager;

		View view1 = new View(GuidePagerActivity.this);
		view1.setBackgroundResource(R.drawable.bg1);

		View view2 = new View(GuidePagerActivity.this);
		view2.setBackgroundResource(R.drawable.bg2);

		LayoutInflater inflater = LayoutInflater.from(GuidePagerActivity.this);
		View view3 = inflater.inflate(R.layout.activity_guidelogin, null);
		loginButton = (Button) view3.findViewById(R.id.guidepager_login_button);

		viewlist.add(view1);
		viewlist.add(view2);
		viewlist.add(view3);

		viewPager = (ViewPager) findViewById(R.id.guidepager_viewpager);
		viewPager.setAdapter(new MyViewPagerAdapter());
		viewPager.setOnPageChangeListener(new MyOnpagerChangerListenner());
		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						LoginActivity.class));
				finish();
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		startService(new Intent(getApplicationContext(),
				GetNotificationService.class));
	}

	class MyViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return viewlist.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(viewlist.get(position));

		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager) container).addView(viewlist.get(position));
			return viewlist.get(position);
		}

	}

	class MyOnpagerChangerListenner implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			if (arg0 == 0) {
				point1.setImageResource(R.drawable.page_now);
				point2.setImageResource(R.drawable.page);
				point3.setImageResource(R.drawable.page);

			} else if (arg0 == 1) {
				point1.setImageResource(R.drawable.page);
				point2.setImageResource(R.drawable.page_now);
				point3.setImageResource(R.drawable.page);

			} else if (arg0 == 2) {
				point1.setImageResource(R.drawable.page);
				point2.setImageResource(R.drawable.page);
				point3.setImageResource(R.drawable.page_now);

			}

		}

	}
}

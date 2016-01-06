package com.bilue.agri;

import com.bilue.agri.util.MyShareUtil;

import android.app.Application;
import android.os.Handler;
import android.text.TextUtils;

public class MyApplication extends Application {

	public static MyShareUtil share;
	public static Handler handler;

	private String username;

	private static MyApplication mAp;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mAp = this;
		share = new MyShareUtil(mAp);
	}

	public static MyApplication getInstance() {
		return mAp;
	}

	public String getUsername() {
		if (TextUtils.isEmpty(username)) {
			username = share.getName();
		}
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		share.setName(username);
	}

}

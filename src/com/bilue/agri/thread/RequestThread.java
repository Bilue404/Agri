package com.bilue.agri.thread;

import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.bilue.agri.MyApplication;
import com.bilue.agri.request.BaseRequest;

public class RequestThread extends Thread {

	public static final String TAG = "RequestThread";
	public static final int other_result = 1;

	private BaseRequest request;

	public RequestThread(BaseRequest request) {
		// TODO Auto-generated constructor stub
		this.request = request;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		String resultStr = HttpRequest.start(request.getUrl(),
				request.requestJson());
		if (TextUtils.isEmpty(resultStr)) {
			Log.e(TAG, request.getUrl() + "���Բ������󷵻ؽ��Ϊnull...");
			return;
		}

		Message msg = new Message();
		msg.what = other_result;
		msg.obj = request.responseJson(resultStr);
		if (MyApplication.handler != null) {
			MyApplication.handler.sendMessage(msg);
		} else {
			Log.w(TAG, "������Application��Χ��HandlerΪnull....");
		}
	}
}

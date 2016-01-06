package com.bilue.agri.request;

import org.json.JSONObject;

import com.bilue.agri.MyApplication;

public abstract class BaseRequest {

	protected String username;
	protected String url;
	protected JSONObject requestJson;
	protected JSONObject responseJson;

	public BaseRequest() {
		// TODO Auto-generated constructor stub
		username = MyApplication.getInstance().getUsername();
	}

	public String getUrl() {
		return "http://" + MyApplication.share.getIp() + ":8890/" + url;
	}

	public abstract String requestJson();

	public abstract Object responseJson(String result);
}

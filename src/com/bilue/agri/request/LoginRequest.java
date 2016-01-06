package com.bilue.agri.request;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class LoginRequest extends BaseRequest {

	private String name;
	private String pass;

	public LoginRequest(String name, String pass) {
		// TODO Auto-generated constructor stub
		url = "type/jason/action/login";
		this.name = name;
		this.pass = pass;
	}

	@Override
	public String requestJson() {
		// TODO Auto-generated method stub
		try {
			requestJson = new JSONObject();
			requestJson.put("username", name);
			requestJson.put("password", pass);

			return requestJson.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object responseJson(String result) {
		// TODO Auto-generated method stub
		try {
			responseJson = new JSONObject(result);
			if (responseJson.has("result")) {
				if (TextUtils.equals("ok", responseJson.getString("result"))) {
					return true;
				}
			}
//
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}

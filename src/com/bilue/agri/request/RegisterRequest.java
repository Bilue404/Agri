package com.bilue.agri.request;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class RegisterRequest extends BaseRequest {

	private String name;
	private String pass;
	private String email;

	public RegisterRequest(String name, String pass, String email) {
		// TODO Auto-generated constructor stub
		url = "type/jason/action/register";
		this.name = name;
		this.pass = pass;
		this.email = email;
	}

	@Override
	public String requestJson() {
		// TODO Auto-generated method stub
		try {
			requestJson = new JSONObject();
			requestJson.put("username", name);
			requestJson.put("password", pass);
			requestJson.put("email", email);

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

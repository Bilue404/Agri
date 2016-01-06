package com.bilue.agri.request;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class FindPwdRequest extends BaseRequest {

	private String name;

	public FindPwdRequest(String name) {
		// TODO Auto-generated constructor stub
		url = "type/jason/action/findPassword";
		this.name = name;
	}

	@Override
	public String requestJson() {
		// TODO Auto-generated method stub
		try {
			requestJson = new JSONObject();
			requestJson.put("username", name);

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

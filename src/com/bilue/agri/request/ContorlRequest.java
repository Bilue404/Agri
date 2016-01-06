package com.bilue.agri.request;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.bilue.bean.Controller;

public class ContorlRequest extends BaseRequest {

	private Controller control;

	public ContorlRequest(Controller control) {
		// TODO Auto-generated constructor stub
		url = "type/jason/action/control";
		this.control = control;
	}

	@Override
	public String requestJson() {
		// TODO Auto-generated method stub
		try {
			requestJson = new JSONObject();
			requestJson.put("username", username);
			String[] devices = { Controller.Blower, Controller.Buzzer,
					Controller.Roadlamp, Controller.Water };
			int[] vals = { control.getBlower(), control.getBuzzer(),
					control.getRoadlamp(), control.getWaterpump() };
			for (int i = 0; i < devices.length; i++) {
				if (vals[i] != 2) {
					requestJson.put(devices[i], vals[i]);
				}
			}

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

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}

package com.bilue.agri.request;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.bilue.bean.Controller;

public class GetCoStatusRequest extends BaseRequest {

	public GetCoStatusRequest() {
		// TODO Auto-generated constructor stub
		url = "type/jason/action/getControllerStatus";
	}

	@Override
	public String requestJson() {
		// TODO Auto-generated method stub
		try {
			requestJson = new JSONObject();
			requestJson.put("username", username);

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

		Log.i("XDLK", "���أ�" + result);

		Controller control = new Controller();
		try {

			responseJson = new JSONObject(result);
			if (responseJson.has(Controller.Blower)) {
				control.setBlower(responseJson.getInt(Controller.Blower));
			}
			if (responseJson.has(Controller.Buzzer)) {
				control.setBlower(responseJson.getInt(Controller.Buzzer));
			}
			if (responseJson.has(Controller.Roadlamp)) {
				control.setBlower(responseJson.getInt(Controller.Roadlamp));
			}
			if (responseJson.has(Controller.Water)) {
				control.setBlower(responseJson.getInt(Controller.Water));
			}

			return control;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

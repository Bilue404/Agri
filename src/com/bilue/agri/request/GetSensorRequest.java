package com.bilue.agri.request;

import org.json.JSONException;
import org.json.JSONObject;

import com.bilue.bean.Sensor;

public class GetSensorRequest extends BaseRequest {

	public GetSensorRequest() {
		// TODO Auto-generated constructor stub
		url = "type/jason/action/getSensor";
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
		Sensor sensor = new Sensor();
		try {
			responseJson = new JSONObject(result);
			sensor.setAirHumid(responseJson.getInt("airHumidity"));
			sensor.setAirTemp(responseJson.getInt("airTemperature"));
			sensor.setCo2(responseJson.getInt("co2"));
			sensor.setLight(responseJson.getInt("light"));
			sensor.setPm25(responseJson.getInt("PM25"));
			sensor.setSoilHumid(responseJson.getInt("soilHumidity"));
			sensor.setSoilTemp(responseJson.getInt("soilTemperature"));

			return sensor;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

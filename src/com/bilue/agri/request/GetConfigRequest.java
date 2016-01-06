package com.bilue.agri.request;

import org.json.JSONException;
import org.json.JSONObject;

import com.bilue.bean.Config;

public class GetConfigRequest extends BaseRequest {

	public GetConfigRequest() {
		// TODO Auto-generated constructor stub
		url = "type/jason/action/getConfig";
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
		Config config = new Config();
		try {
			responseJson = new JSONObject(result);
			config.setAutoControl(responseJson.getInt("controlAuto"));
			config.setMaxAirHum(responseJson.getInt("maxAirHumidity"));
			config.setMaxAirTemp(responseJson.getInt("maxAirTemperature"));
			config.setMaxCo2(responseJson.getInt("maxCo2"));
			config.setMaxLight(responseJson.getInt("maxLight"));
			config.setMaxPM25(responseJson.getInt("maxPM25"));
			config.setMaxSoilHum(responseJson.getInt("maxSoilHumidity"));
			config.setMaxSoilTemp(responseJson.getInt("maxSoilTemperature"));
			config.setMinAirHum(responseJson.getInt("minAirHumidity"));
			config.setMinAirTemp(responseJson.getInt("minAirTemperature"));
			config.setMinCo2(responseJson.getInt("minCo2"));
			config.setMinLight(responseJson.getInt("minLight"));
			config.setMinSoilHum(responseJson.getInt("minSoilHumidity"));
			config.setMinSoilTemp(responseJson.getInt("minSoilTemperature"));

			return config;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

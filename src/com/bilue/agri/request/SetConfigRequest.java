package com.bilue.agri.request;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;

import com.bilue.bean.Config;

public class SetConfigRequest extends BaseRequest {

	private Config config;

	public SetConfigRequest(Config config) {
		// TODO Auto-generated constructor stub
		url = "type/jason/action/setConfig";
		this.config = config;
	}

	@Override
	public String requestJson() {
		// TODO Auto-generated method stub
		try {
			requestJson = new JSONObject();
			requestJson.put("username", username);

			if (config.getAutoControl() != -1) {
				requestJson.put("controlAuto", config.getAutoControl());
			}
			if (config.getMaxAirHum() != -1) {
				requestJson.put("maxAirHumidity", config.getMaxAirHum());
			}
			if (config.getMaxAirTemp() != -1) {
				requestJson.put("maxAirTemperature", config.getMaxAirTemp());
			}
			if (config.getMaxCo2() != -1) {
				requestJson.put("maxCo2", config.getMaxCo2());
			}
			if (config.getMaxLight() != -1) {
				requestJson.put("maxLight", config.getMaxLight());
			}
			if (config.getMaxPM25() != -1) {
				requestJson.put("maxPM25", config.getMaxPM25());
			}
			if (config.getMaxSoilHum() != -1) {
				requestJson.put("maxSoilHumidity", config.getMaxSoilHum());
			}
			if (config.getMaxSoilTemp() != -1) {
				requestJson.put("maxSoilTemperature", config.getMaxSoilTemp());
			}
			if (config.getMinAirHum() != -1) {
				requestJson.put("minAirHumidity", config.getMinAirHum());
			}
			if (config.getMinAirTemp() != -1) {
				requestJson.put("minAirTemperature", config.getMinAirTemp());
			}
			if (config.getMinCo2() != -1) {
				requestJson.put("minCo2", config.getMinCo2());
			}
			if (config.getMinLight() != -1) {
				requestJson.put("minLight", config.getMinLight());
			}
			if (config.getMinSoilHum() != -1) {
				requestJson.put("minSoilHumidity", config.getMinSoilHum());
			}
			if (config.getMinSoilTemp() != -1) {
				requestJson.put("minSoilTemperature", config.getMinSoilTemp());
			}

			Log.i("XDLK", "SetConfigRequest��" + requestJson.toString());
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

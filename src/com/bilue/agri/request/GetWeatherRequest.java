package com.bilue.agri.request;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bilue.bean.Weather;

public class GetWeatherRequest extends BaseRequest {

	private ArrayList<Weather> weathers;

	public GetWeatherRequest() {
		url = "type/jason/action/getweather";
	}

	@Override
	public String requestJson() {

		try {
			requestJson = new JSONObject();
			requestJson.put("username", username);
			return requestJson.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Object responseJson(String result) {

		try {
			responseJson = new JSONObject(result);
			JSONArray jsonArray = responseJson.getJSONArray("weather");
			JSONObject jsonBean = new JSONObject();
			weathers = new ArrayList<Weather>();
			for (int i = 0; i < jsonArray.length(); i++) {
				jsonBean = jsonArray.getJSONObject(i);
				Weather weather = new Weather();
				weather.setWeather(jsonBean.getInt("weatherStype"));
				weather.setTemptrue(jsonBean.getInt("temptrue"));
				weather.setInfo(jsonBean.getString("info"));
				weather.setDate(jsonBean.getString("date"));
				weathers.add(weather);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return weathers;
	}

}

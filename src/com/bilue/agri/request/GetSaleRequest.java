package com.bilue.agri.request;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bilue.bean.MySale;

public class GetSaleRequest extends BaseRequest{

	private ArrayList<MySale> mySales;
	
	public GetSaleRequest() {
		url = "type/jason/action/getsale";
	}
	
	@Override
	public String requestJson() {
		requestJson = new JSONObject();
		try {
			requestJson.put("username", username);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}

	@Override
	public Object responseJson(String result) {
		mySales = new ArrayList<MySale>();
		try {
			responseJson = new JSONObject(result);
			JSONArray jsonArray = new JSONArray();
			
			jsonArray = responseJson.getJSONArray("sale");
			MySale mySale;
			for(int i=0; i<jsonArray.length(); i++){
				JSONObject jsonObject = new JSONObject();
				jsonObject = jsonArray.getJSONObject(i);
				
				mySale = new MySale();
				mySale.setType(jsonObject.getString(""));
				mySale.setInfo(jsonObject.getString(""));
				mySale.setTime(jsonObject.getString(""));
				mySales.add(mySale);
			}
			
			
			return mySales;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}

package com.bilue.agri.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyShareUtil {

	public static final String NAME = "share_name";
	public static final String PASS = "sahre_pass";
	public static final String Ip = "share_ip";
	public static final String ISSHOW = "share_isshow";

	public static final String MIN_AIR_TMP = "share_min_air_tmp";
	public static final String MAX_AIR_TMP = "share_max_air_tmp";
	public static final String MIN_AIR_HUM = "share_min_air_hum";
	public static final String MAX_AIR_HUM = "share_max_air_hum";

	public static final String MIN_SOIL_TMP = "share_min_soil_tmp";
	public static final String MAX_SOIL_TMP = "share_max_soil_tmp";
	public static final String MIN_SOIL_HUM = "share_min_soil_hum";
	public static final String MAX_SOIL_HUM = "share_max_soil_hum";

	public static final String MIN_CO2 = "share_min_co2";
	public static final String MAX_CO2 = "share_max_co2";

	public static final String MIN_LIGHT = "share_min_light";
	public static final String MAX_LIGHT = "share_max_light";

	public static final String MAX_PM25 = "share_max_pm25";

	public static final String CONTROLAUTO = "share_controlauto";

	private SharedPreferences share;

	public MyShareUtil(Context context) {
		// TODO Auto-generated constructor stub
		share = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public void save(String key, Object value) {
		if (value instanceof String) {
			share.edit().putString(key, (String) value).commit();
		} else if (value instanceof Integer) {
			share.edit().putInt(key, (Integer) value).commit();
		} else if (value instanceof Boolean) {
			share.edit().putBoolean(key, (Boolean) value).commit();
		}
	}

	public void setShow(boolean show) {
		save(ISSHOW, show);
	}

	public boolean getShow() {
		return share.getBoolean(ISSHOW, true);
	}

	public void setName(String name) {
		save(NAME, name);
	}

	public String getName() {
		return share.getString(NAME, "");
	}

	public void setPass(String pass) {
		save(PASS, pass);
	}

	public String getPass() {
		return share.getString(PASS, "");
	}

	public void setIp(String ip) {
		save(Ip, ip);
	}

	public String getIp() {
		return share.getString(Ip, "192.168.228.12");
	}

	public void setMinAirTmp(int minAirTmp) {
		save(MIN_AIR_TMP, minAirTmp);
	}

	public int getMinAirTmp() {
		return share.getInt(MIN_AIR_TMP, 0);
	}

	public void setMaxAirTmp(int maxAirTmp) {
		save(MAX_AIR_TMP, maxAirTmp);
	}

	public int getMaxAirTmp() {
		return share.getInt(MAX_AIR_TMP, 60);
	}

	public void setMinAirHum(int minAirHum) {
		save(MIN_AIR_HUM, minAirHum);
	}

	public int getMinAirHum() {
		return share.getInt(MIN_AIR_HUM, 10);
	}

	public void setMaxAirHum(int maxAirHum) {
		save(MAX_AIR_HUM, maxAirHum);
	}

	public int getMaxAirHum() {
		return share.getInt(MAX_AIR_HUM, 70);
	}

	public void setMinSoilHum(int minSoilHum) {
		save(MIN_SOIL_HUM, minSoilHum);
	}

	public int getMinSoilHum() {
		return share.getInt(MIN_SOIL_HUM, 10);
	}

	public void setMaxSoilHum(int maxSoilHum) {
		save(MAX_SOIL_HUM, maxSoilHum);
	}

	public int getMaxSoilHum() {
		return share.getInt(MAX_SOIL_HUM, 70);
	}

	public void setMinSoilTmp(int minSoilTmp) {
		save(MIN_SOIL_TMP, minSoilTmp);
	}

	public int getMinSoilTmp() {
		return share.getInt(MIN_SOIL_TMP, 0);
	}

	public void setMaxSoilTmp(int maxSoilTmp) {
		save(MAX_SOIL_TMP, maxSoilTmp);
	}

	public int getMaxSoilTmp() {
		return share.getInt(MAX_SOIL_TMP, 90);
	}

	public void setMaxCo2(int maxCo2) {
		save(MAX_CO2, maxCo2);
	}

	public int getMaxCo2() {
		return share.getInt(MAX_CO2, 500);
	}

	public void setMinCo2(int minCo2) {
		save(MIN_CO2, minCo2);
	}

	public int getMinCo2() {
		return share.getInt(MIN_CO2, 100);
	}

	public void setMaxLight(int maxLight) {
		save(MAX_LIGHT, maxLight);
	}

	public int getMaxLight() {
		return share.getInt(MAX_LIGHT, 10000);
	}

	public void setMinLight(int minLight) {
		save(MIN_LIGHT, minLight);
	}

	public int getMinLight() {
		return share.getInt(MIN_LIGHT, 500);
	}

	public void setControAuto(int controlAuto) {
		save(CONTROLAUTO, controlAuto);
	}

	public int getControlAuto() {
		return share.getInt(CONTROLAUTO, 500);
	}

}

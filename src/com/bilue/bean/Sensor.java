package com.bilue.bean;

public class Sensor {
	private int pm25;
	private int co2;
	private int airTemp;
	private int airHumid;
	private int soilTemp;
	private int soilHumid;
	private int light;
	private String time;

	public int getPm25() {
		return pm25;
	}

	public void setPm25(int pm25) {
		this.pm25 = pm25;
	}

	public int getCo2() {
		return co2;
	}

	public void setCo2(int co2) {
		this.co2 = co2;
	}

	public int getAirTemp() {
		return airTemp;
	}

	public void setAirTemp(int airTemp) {
		this.airTemp = airTemp;
	}

	public int getAirHumid() {
		return airHumid;
	}

	public void setAirHumid(int airHumid) {
		this.airHumid = airHumid;
	}

	public int getSoilTemp() {
		return soilTemp;
	}

	public void setSoilTemp(int soilTemp) {
		this.soilTemp = soilTemp;
	}

	public int getSoilHumid() {
		return soilHumid;
	}

	public void setSoilHumid(int soilHumid) {
		this.soilHumid = soilHumid;
	}

	public int getLight() {
		return light;
	}

	public void setLight(int light) {
		this.light = light;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}

package com.bilue.bean;

public class SensorView {

	private String sensorName;
	private int sensorValue;
	
	public SensorView(String name, int value) {
		super();
		this.sensorName = name;
		sensorValue = value;
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public int getSensorValue() {
		return sensorValue;
	}

	public void setSensorValue(int sensorValue) {
		this.sensorValue = sensorValue;
	}
	
	
	
}

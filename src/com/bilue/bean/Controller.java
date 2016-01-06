package com.bilue.bean;

public class Controller {

	public static final String Water = "WaterPump";
	public static final String Blower = "Blower";
	public static final String Roadlamp = "Roadlamp";
	public static final String Buzzer = "Buzzer";

	private int blower = 2;
	private int buzzer = 2;
	private int roadlamp = 2;
	private int waterpump = 2;

	public int getBlower() {
		return blower;
	}

	public void setBlower(int blower) {
		this.blower = blower;
	}

	public int getBuzzer() {
		return buzzer;
	}

	public void setBuzzer(int buzzer) {
		this.buzzer = buzzer;
	}

	public int getRoadlamp() {
		return roadlamp;
	}

	public void setRoadlamp(int roadlamp) {
		this.roadlamp = roadlamp;
	}

	public int getWaterpump() {
		return waterpump;
	}

	public void setWaterpump(int waterpump) {
		this.waterpump = waterpump;
	}

}

package com.bilue.bean;

public class Weather {
	public static final int CLEAR = 1; //��
	public static final int RAIN = 2; //
	public static final int CLOUDY = 3;
	
	private int weatherStype ; //����ͼƬ
	private String info;  //�¶�����
	private int temptrue; //�¶�
	private String date; //����
	
	public int getWeather() {
		return weatherStype;
	}
	public void setWeather(int weather) {
		this.weatherStype = weather;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getTemptrue() {
		return temptrue;
	}
	public void setTemptrue(int temptrue) {
		this.temptrue = temptrue;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public String getDate(){
		return date;
	}
	
	
}

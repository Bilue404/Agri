package com.bilue.bean;

public class Mysensor {
	private String time;
	private int value;
	public Mysensor(String time, int value) {
		super();
		this.time = time;
		this.value = value;
	}
	public Mysensor(){
		
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
}

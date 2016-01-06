package com.bilue.agri.view;

import java.util.ArrayList;

import com.bilue.bean.MyDate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class DateActionView extends View{

	private int width;
	private int height;
	private ArrayList<MyDate> myDates;
	private Paint paint;
	public DateActionView(Context context,ArrayList<MyDate> myDates) {
		super(context);
		width = getWidth();
		height = getHeight();
		this.myDates = myDates;
		paint = new Paint();
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for(int i=0; i<myDates.size(); i++){
			MyDate myDate = myDates.get(i);
			canvas.drawLine(20, 20, 20, height-20, paint);
			canvas.drawPoint(20, 20+i*10, paint);
		}
	}
	

	
	
	
	
	
	
	
	
	
	
}

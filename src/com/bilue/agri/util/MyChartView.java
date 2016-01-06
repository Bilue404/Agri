package com.bilue.agri.util;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.bilue.bean.Mysensor;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

public class MyChartView {
	private XYMultipleSeriesRenderer renderer;
	private XYMultipleSeriesDataset dataset;
	private XYSeries series;
	private XYSeriesRenderer seriesRenderer;
	private DefaultRenderer defaultRenderer;
	private SimpleSeriesRenderer ssr;
	private CategorySeries categorySeries;
	
	private Context context;
	private ArrayList<Mysensor> mySensors;
	private String title;
	
	
	public MyChartView(Context context, String title) {
		this.context = context;
		this.title = title;
	}

	
	public MyChartView(Context context,String title,ArrayList<Mysensor> mySensors){
		this.context = context;
		this.title = title;
		this.mySensors = mySensors;
		initHistoryLineView();
		initHistoryPieView();
		
	}
	
	public View getRealTimeView(){
		initRealTimeRenderer();
		return ChartFactory.getLineChartView(context, dataset, renderer);
	}
	
	public View getHistoryLineView(){
		return ChartFactory.getLineChartView(context, dataset, renderer);
	}
	public View getHistoryBarView(){
		return ChartFactory.getBarChartView(context, dataset, renderer, Type.DEFAULT);
	}
	public View getHistoryPieView(){
		return ChartFactory.getPieChartView(context, categorySeries, defaultRenderer);
	}
	
	

	private void initRealTimeRenderer(){
		renderer = new XYMultipleSeriesRenderer();
		seriesRenderer = new XYSeriesRenderer();
		
		seriesRenderer.setColor(Color.GREEN);
		seriesRenderer.setLineWidth(10);
		seriesRenderer.setPointStyle(PointStyle.CIRCLE);
		seriesRenderer.setPointStrokeWidth(20);
		seriesRenderer.setDisplayChartValues(true);
		
		seriesRenderer.setChartValuesTextSize(20);
		seriesRenderer.setChartValuesSpacing(30);
		seriesRenderer.setDisplayChartValuesDistance(30);
		
		renderer.setXAxisMax(11);
		renderer.setXAxisMin(0);
		renderer.setYAxisMin(0);
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.TRANSPARENT);
		renderer.setChartTitle(title);
		renderer.setChartTitleTextSize(20);
		renderer.setMarginsColor(Color.argb(0, 0xff, 0, 0));
		
		renderer.addSeriesRenderer(seriesRenderer);
		dataset = new XYMultipleSeriesDataset();
		series = new XYSeries(title);
		dataset.addSeries(series);
	}
	
	private void initHistoryLineView(){
		renderer = new XYMultipleSeriesRenderer();
		seriesRenderer = new XYSeriesRenderer();
		
		seriesRenderer.setColor(Color.GREEN);
		seriesRenderer.setLineWidth(10);
		seriesRenderer.setPointStyle(PointStyle.CIRCLE);
		seriesRenderer.setPointStrokeWidth(20);
		seriesRenderer.setDisplayChartValues(true);
		
		seriesRenderer.setChartValuesTextSize(20);
		seriesRenderer.setChartValuesSpacing(30);
		seriesRenderer.setDisplayChartValuesDistance(30);
		
		renderer.setXAxisMax(11);
		renderer.setXAxisMin(0);
		renderer.setYAxisMin(0);
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.TRANSPARENT);
		renderer.setChartTitle(title);
		renderer.setChartTitleTextSize(20);
		renderer.setMarginsColor(Color.argb(0, 0xff, 0, 0));
		
		renderer.addSeriesRenderer(seriesRenderer);
		dataset = new XYMultipleSeriesDataset();
		series = new XYSeries(title);
		
		
		for(int i=0; i<mySensors.size(); i++){
			Mysensor mySen = mySensors.get(i);
			series.add(i, mySen.getValue());
			renderer.addXTextLabel(i, mySen.getTime());
		}
			
		dataset.addSeries(series);
	}
	
	private void initHistoryPieView(){
		defaultRenderer = new XYMultipleSeriesRenderer();
		categorySeries = new CategorySeries(title);
		for(int i=0; i<mySensors.size(); i++){
			Mysensor mySen = mySensors.get(i);
			categorySeries.add(mySen.getTime(), mySen.getValue());
			ssr = new SimpleSeriesRenderer();
			if(mySen.getValue()>100){
				ssr.setColor(Color.RED);
			}
			else{
				ssr.setColor(Color.GREEN);
			}
			
			
			defaultRenderer.addSeriesRenderer(ssr);
				
		}
	}
	
	
	public void update(Mysensor mySensor){
		if(mySensors==null){
			mySensors = new ArrayList<Mysensor>();
		}
		if(mySensors.size()==10){
			mySensors.remove(0);
		}
		
		mySensors.add(mySensor);
		series.clear();
		for(int i=0; i<mySensors.size(); i++){
			Mysensor mySen = mySensors.get(i);
			series.add(i, mySen.getValue());
			renderer.addXTextLabel(i, mySen.getTime());
		}
		
		
		
	}
	
	
	
	
}

package com.bilue.agri.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.bilue.agri.MyApplication;
import com.bilue.agri.request.GetSensorRequest;
import com.bilue.agri.thread.HttpRequest;
import com.bilue.bean.Sensor;
import com.bilue.database.Mydatabase;
import com.bilue.database.SensorConfig;

public class GetSensorService extends Service {

	public static final String TAG = "GetSensorService";
	public static final int sensor_reuslt = 2;

	private MyTimerTask mTimTask;
	private Timer timer;
	private MyApplication mAp;

	private GetSensorRequest request;
	private SimpleDateFormat format;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		Log.i(TAG, "GetSensorService start....");

		timer = new Timer();
		mTimTask = new MyTimerTask();

		mAp = MyApplication.getInstance();

		request = new GetSensorRequest();
		format = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());

		timer.schedule(mTimTask, 1000, 1000);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		Log.i(TAG, "��ȡ������ֵ����ʼ����...");
		return super.onStartCommand(intent, flags, startId);
	}

	private class MyTimerTask extends TimerTask {

		Message msg = null;
		String resultStr = "";
		Sensor sensor;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String time = format.format(new Date());
			resultStr = HttpRequest.start(request.getUrl(),
					request.requestJson());

			 Log.i(TAG, "���󷵻ش�����ֵ��" + resultStr);

			if (TextUtils.isEmpty(resultStr)) {
				Log.e(TAG, "��ȡ����ֵ������Ϊnull...");
				return;
			}

			sensor = (Sensor) request.responseJson(resultStr);
			sensor.setTime(time);
			saveToDataBase(sensor);

			msg = new Message();
			msg.what = sensor_reuslt;
			msg.obj = sensor;
			if (MyApplication.handler != null) {
				MyApplication.handler.sendMessage(msg);
			} else {
				Log.w(TAG, "��ǰ������Application��Χ��HandlerΪnull...");
			}

		}
	}

	/*
	 * ���洫����ֵ����ݿ���(��������)
	 */
	public void saveToDataBase(Sensor sensor) {
		// Log.d("����", "���������ɹ�");
		Mydatabase db = new Mydatabase(mAp);
		db.insert(SensorConfig.Air_humidity, sensor.getAirHumid(),
				sensor.getTime());
		db.insert(SensorConfig.Air_temp, sensor.getAirTemp(), sensor.getTime());
		db.insert(SensorConfig.Soil_humidity, sensor.getSoilHumid(),
				sensor.getTime());
		db.insert(SensorConfig.Soil_temp, sensor.getSoilTemp(),
				sensor.getTime());
		db.insert(SensorConfig.Co2, sensor.getCo2(), sensor.getTime());
		db.insert(SensorConfig.light, sensor.getLight(), sensor.getTime());

	}

}

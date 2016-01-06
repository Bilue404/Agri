package com.bilue.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bilue.bean.Mysensor;
import com.bilue.bean.SensorMsg;

import android.R.integer;
import android.R.string;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Mydatabase {
	MyOpenHelper myOpenHelper;

	ArrayList<Integer> airtempList = new ArrayList<Integer>();
	ArrayList<Integer> airhumidityList = new ArrayList<Integer>();
	ArrayList<Integer> soiltempList = new ArrayList<Integer>();
	ArrayList<Integer> soilhumidityList = new ArrayList<Integer>();
	ArrayList<Integer> co2List = new ArrayList<Integer>();
	ArrayList<Integer> lightList = new ArrayList<Integer>();

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");

	private String getTime() {
		return simpleDateFormat.format(new Date());
	}

	public Mydatabase(Context context) {
		myOpenHelper = new MyOpenHelper(context, DatabaseConfig.databasename,
				null, 1);

	}

	// ��ѯ60��
	public ArrayList<Mysensor> query60(String sensorname) {
		ArrayList<Mysensor> list = new ArrayList<Mysensor>();
		SQLiteDatabase db = myOpenHelper.getReadableDatabase();
		String sql = "SELECT * FROM " + DatabaseConfig.tablename60 + " WHERE "
				+ DatabaseConfig.sensorname + "=?";
		Cursor cursor = db.rawQuery(sql, new String[] { sensorname });
		if (cursor.moveToFirst()) {
			int tol = cursor.getCount();
			for (int i = 0; i < tol; i++) {
				Mysensor mysensor = new Mysensor();
				mysensor.setValue(cursor.getInt(cursor
						.getColumnIndex(DatabaseConfig.values)));
				mysensor.setTime(cursor.getString(cursor
						.getColumnIndex(DatabaseConfig.time)));
				list.add(mysensor);
			}
		}
		db.close();

		return list;

	}

	// ��ѯ5����
	public ArrayList<Mysensor> query5(String sensorname) {
		ArrayList<Mysensor> list = new ArrayList<Mysensor>();
		SQLiteDatabase db = myOpenHelper.getReadableDatabase();
		String sql = "SELECT * FROM " + DatabaseConfig.tablename5 + " WHERE "
				+ DatabaseConfig.sensorname + "=?";
		Cursor cursor = db.rawQuery(sql, new String[] { sensorname });
		if (cursor.moveToFirst()) {
			int tol = cursor.getCount();
			for (int i = 0; i < tol; i++) {
				Mysensor mysensor = new Mysensor();
				mysensor.setValue(cursor.getInt(cursor
						.getColumnIndex(DatabaseConfig.values)));
				mysensor.setTime(cursor.getString(cursor
						.getColumnIndex(DatabaseConfig.time)));
				list.add(mysensor);
			}
		}
		db.close();

		return list;

	}

	// ���ȥ��ʮ��ı?�ж��Ƿ��ϲ���5���ӵı�
	public void insert(String sensorname, int values, String time) {
		SQLiteDatabase db = myOpenHelper.getWritableDatabase();
		String sqlinsert = "INSERT INTO " + DatabaseConfig.tablename60 + " ("
				+ DatabaseConfig.sensorname + "," + DatabaseConfig.values + ","
				+ DatabaseConfig.time + ")" + " VALUES(?,?,?)";
		db.execSQL(sqlinsert,
				new String[] { sensorname, values + "", getTime() });
		if (SensorConfig.Air_humidity.equals(sensorname)) {
			airhumidityList.add(values);
			if (airhumidityList.size() >= 60) {
				int tol = 0;
				for (int i = 0; i < 60; i++) {
					tol = tol + airhumidityList.get(i);
				}

				insertTo5Table(db, tol / 60, sensorname);
			}
		} else if (SensorConfig.Air_temp.equals(sensorname)) {
			airtempList.add(values);
			if (airtempList.size() >= 60) {
				int tol = 0;
				for (int i = 0; i < 60; i++) {
					tol = tol + airtempList.get(i);
				}

				insertTo5Table(db, tol / 60, sensorname);
			}
		} else if (SensorConfig.Soil_humidity.equals(sensorname)) {
			soilhumidityList.add(values);
			if (soilhumidityList.size() >= 60) {
				int tol = 0;
				for (int i = 0; i < 60; i++) {
					tol = tol + soilhumidityList.get(i);
				}

				insertTo5Table(db, tol / 60, sensorname);
			}
		} else if (SensorConfig.Soil_temp.equals(sensorname)) {
			soiltempList.add(values);
			if (soiltempList.size() >= 60) {
				int tol = 0;
				for (int i = 0; i < 60; i++) {
					tol = tol + soiltempList.get(i);
				}

				insertTo5Table(db, tol / 60, sensorname);
			}
		} else if (SensorConfig.Co2.equals(sensorname)) {
			co2List.add(values);
			if (airhumidityList.size() >= 60) {
				int tol = 0;
				for (int i = 0; i < 60; i++) {
					tol = tol + airhumidityList.get(i);
				}

				insertTo5Table(db, tol / 60, sensorname);
			}
		} else if (SensorConfig.light.equals(sensorname)) {
			lightList.add(values);
			if (lightList.size() >= 60) {
				int tol = 0;
				for (int i = 0; i < 60; i++) {
					tol = tol + lightList.get(i);
				}

				insertTo5Table(db, tol / 60, sensorname);
			}
		}

		String sqlIsOver60 = "SELECT * FROM " + DatabaseConfig.tablename60
				+ " WHERE " + DatabaseConfig.sensorname + "=?" + " ORDER BY id";
		Cursor cursor = db.rawQuery(sqlIsOver60, new String[] { sensorname });
		if (cursor.moveToFirst()) {
			if (cursor.getCount() > 60) {

				String sqldele = "DELETE FROM " + DatabaseConfig.tablename60
						+ " WHERE id=?";
				db.execSQL(sqldele, new String[] { cursor.getString(cursor
						.getColumnIndex("id")) });
			}
		}
		db.close();
	}

	private void insertTo5Table(SQLiteDatabase db, int values, String sensorname) {
		String sqlinsert = "INSERT INTO " + DatabaseConfig.tablename5 + " ("
				+ DatabaseConfig.sensorname + "," + DatabaseConfig.values + ","
				+ DatabaseConfig.time + ")" + " VALUES(?,?,?)";
		db.execSQL(sqlinsert,
				new String[] { sensorname, values + "", getTime() });
		String query = "SELECT * FROM " + DatabaseConfig.tablename5 + " WHERE "
				+ DatabaseConfig.sensorname + "=?" + " ORDER BY id";
		Cursor cursor = db.rawQuery(query, new String[] { sensorname });
		if (cursor.moveToFirst()) {
			if (cursor.getCount() > 5) {
				String sqldelete = "DELETE FROM " + DatabaseConfig.tablename5
						+ " WHERE=?";
				db.execSQL(sqldelete, new String[] { cursor.getString(cursor
						.getColumnIndex("id")) });
			}
		}
	}

}

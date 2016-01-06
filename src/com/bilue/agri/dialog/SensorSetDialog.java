package com.bilue.agri.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bilue.agri.MyApplication;
import com.bilue.agri.request.SetConfigRequest;
import com.bilue.agri.thread.RequestThread;
import com.bilue.bean.Config;
import com.bilue.agri.R;

public class SensorSetDialog extends Dialog {

	private String title;
	private int minValue;
	private int maxValue;
	private int minHvalue;
	private int maxHvalue;

	private TextView titleTextView;
	private EditText minvalueEdit;
	private EditText maxvaluEdit;
	private EditText minvalueHEdit;
	private EditText maxvalueHEdit;
	private Button cancelButo;
	private Button confirmButo;

	private Config con;

	private MyHandler mHandler;
	private ProgressDialog sesorDialog;

	private boolean isShow = true;

	public SensorSetDialog(Context context, String title, int minValue,
			int maxValue) {
		this(context, title, minValue, maxValue, -1, -1);
	}

	public SensorSetDialog(Context context, String title, int minValue,
			int maxValue, int minHvalue, int maxHvalue) {
		// TODO Auto-generated constructor stub
		super(context, R.style.dialog_style);
		setCanceledOnTouchOutside(false);
		// TODO Auto-generated constructor stub
		this.title = title;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.minHvalue = minHvalue;
		this.maxHvalue = maxHvalue;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dialog_sensor);

		mHandler = new MyHandler();
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		titleTextView = (TextView) this.findViewById(R.id.dialog_sensor_title);
		titleTextView.setText(title + "��ֵ����");

		minvalueEdit = (EditText) this
				.findViewById(R.id.dialog_sensor_minvalue);
		minvalueEdit.setHint("��Сֵ��" + minValue);

		maxvaluEdit = (EditText) this.findViewById(R.id.dialog_sensor_maxvalue);
		maxvaluEdit.setHint("���ֵ��" + maxValue);

		minvalueHEdit = (EditText) this
				.findViewById(R.id.dialog_sensor_h_minvalue);
		if (minHvalue == -1) {
			minvalueHEdit.setVisibility(EditText.GONE);
			isShow = false;
		} else {

			minvalueHEdit.setHint("ʪ����СֵΪ��" + minHvalue);
		}

		maxvalueHEdit = (EditText) this
				.findViewById(R.id.dialog_sensor_h_maxvalue);
		if (maxHvalue == -1) {
			maxvalueHEdit.setVisibility(EditText.GONE);
			isShow = false;
		} else {

			maxvalueHEdit.setHint("ʪ�����ֵΪ��" + maxHvalue);
		}

		cancelButo = (Button) this.findViewById(R.id.dialog_sensor_cancel);
		cancelButo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cancel();
			}
		});

		confirmButo = (Button) this.findViewById(R.id.dialog_sensor_confirm);
		confirmButo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String newMinValue = minvalueEdit.getText().toString().trim();
				String newMaxValue = maxvaluEdit.getText().toString().trim();

				if (isShow) {
					String newMinHvalue = minvalueHEdit.getText().toString()
							.trim();
					String newMaxHvalue = maxvalueHEdit.getText().toString()
							.trim();

					if (TextUtils.isEmpty(newMaxHvalue)
							&& TextUtils.isEmpty(newMinHvalue)
							&& TextUtils.isEmpty(newMaxValue)
							&& TextUtils.isEmpty(newMinValue)) {
						Toast.makeText(getContext(), "��û���޸��κ�ֵ��",
								Toast.LENGTH_SHORT).show();
						cancel();
						return;
					}
					if (TextUtils.isEmpty(newMaxHvalue)
							|| TextUtils.isEmpty(newMinHvalue)
							|| TextUtils.isEmpty(newMaxValue)
							|| TextUtils.isEmpty(newMinValue)) {
						Toast.makeText(getContext(), "�벹ȫ����ֵ��",
								Toast.LENGTH_SHORT).show();
						return;
					}

					if (TextUtils.equals(title, "����")) {

						// MyApplication.share.setMinAirTmp(Integer
						// .valueOf(newMinValue));
						// MyApplication.share.setMaxAirTmp(Integer
						// .valueOf(newMaxValue));
						// MyApplication.share.setMinAirHum(Integer
						// .valueOf(newMinHvalue));
						// MyApplication.share.setMaxAirHum(Integer
						// .valueOf(newMaxHvalue));

						con = new Config();
						con.setMinAirTemp(Integer.valueOf(newMinValue));
						con.setMaxAirTemp(Integer.valueOf(newMaxValue));
						con.setMinAirHum(Integer.valueOf(newMinHvalue));
						con.setMaxAirHum(Integer.valueOf(newMaxHvalue));

					} else if (TextUtils.equals(title, "����")) {

						// MyApplication.share.setMinSoilTmp(Integer
						// .valueOf(newMinValue));
						// MyApplication.share.setMaxSoilTmp(Integer
						// .valueOf(newMaxValue));
						// MyApplication.share.setMinSoilHum(Integer
						// .valueOf(newMinHvalue));
						// MyApplication.share.setMaxSoilHum(Integer
						// .valueOf(newMaxHvalue));

						con = new Config();
						con.setMinSoilTemp(Integer.valueOf(newMinValue));
						con.setMaxSoilTemp(Integer.valueOf(newMaxValue));
						con.setMinSoilHum(Integer.valueOf(newMinHvalue));
						con.setMaxSoilHum(Integer.valueOf(newMaxHvalue));
					}

				} else {

					if (TextUtils.isEmpty(newMinValue)
							&& TextUtils.isEmpty(newMaxValue)) {
						Toast.makeText(getContext(), "��û���޸��κ�ֵ��",
								Toast.LENGTH_SHORT).show();
						cancel();
						return;
					}
					if (TextUtils.isEmpty(newMinValue)
							|| TextUtils.isEmpty(newMaxValue)) {
						Toast.makeText(getContext(), "�벹ȫ����ֵ��",
								Toast.LENGTH_SHORT).show();
						return;
					}

					if (TextUtils.equals(title, "����")) {
						//
						// MyApplication.share.setMinLight(Integer
						// .valueOf(newMinValue));
						// MyApplication.share.setMaxLight(Integer
						// .valueOf(newMaxValue));

						con = new Config();
						con.setMinLight(Integer.valueOf(newMinValue));
						con.setMaxLight(Integer.valueOf(newMaxValue));
					} else if (TextUtils.equals(title, "CO2")) {

						// MyApplication.share.setMinCo2(Integer
						// .valueOf(newMinValue));
						// MyApplication.share.setMaxCo2(Integer
						// .valueOf(newMaxValue));

						con = new Config();
						con.setMinCo2(Integer.valueOf(newMinValue));
						con.setMaxCo2(Integer.valueOf(newMaxValue));
					}
				}

				sesorDialog = new ProgressDialog(getContext());
				sesorDialog.setMessage("�����޸�...");
				sesorDialog.show();

				if (MyApplication.handler == null) {
					MyApplication.handler = mHandler;
				} else {
					if (MyApplication.handler != mHandler) {
						MyApplication.handler = null;
						MyApplication.handler = mHandler;
					}
				}

				SetConfigRequest request = new SetConfigRequest(con);
				RequestThread thread = new RequestThread(request);
				thread.start();
			}
		});
	}

	@SuppressLint("HandlerLeak")
	private class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == RequestThread.other_result) {
				if (msg.obj != null) {
					if ((Boolean) msg.obj) {
						Toast.makeText(getContext(), "�޸ĳɹ���",
								Toast.LENGTH_SHORT).show();
						savaConfig(con); // ���浽SharePreference��

					} else {
						Toast.makeText(getContext(), "�޸�ʧ�ܣ�",
								Toast.LENGTH_SHORT).show();
					}
					sesorDialog.cancel();
					cancel();
				}
			}
		}
	}

	private void savaConfig(Config con) {
		if (con.getAutoControl() != -1) {
			MyApplication.share.setControAuto(con.getAutoControl());
		}
		if (con.getMaxAirHum() != -1) {
			MyApplication.share.setMaxAirHum(con.getMaxAirHum());
		}
		if (con.getMaxAirTemp() != -1) {
			MyApplication.share.setMaxAirTmp(con.getMaxAirTemp());
		}
		if (con.getMaxCo2() != -1) {
			MyApplication.share.setMaxCo2(con.getMaxCo2());
		}
		if (con.getMaxLight() != -1) {
			MyApplication.share.setMaxLight(con.getMaxLight());
		}

		if (con.getMaxSoilHum() != -1) {
			MyApplication.share.setMaxSoilHum(con.getMaxSoilHum());
		}
		if (con.getMaxSoilTemp() != -1) {
			MyApplication.share.setMaxSoilTmp(con.getMaxSoilTemp());
		}
		if (con.getMinAirHum() != -1) {
			MyApplication.share.setMinAirHum(con.getMinAirHum());
		}
		if (con.getMinAirTemp() != -1) {
			MyApplication.share.setMinAirTmp(con.getMinAirTemp());
		}
		if (con.getMinCo2() != -1) {
			MyApplication.share.setMinCo2(con.getMinCo2());
		}
		if (con.getMinLight() != -1) {
			MyApplication.share.setMinLight(con.getMinLight());
		}
		if (con.getMinSoilHum() != -1) {
			MyApplication.share.setMinSoilHum(con.getMinSoilHum());
		}
		if (con.getMinSoilTemp() != -1) {
			MyApplication.share.setMinSoilTmp(con.getMinSoilTemp());
		}
	}
}

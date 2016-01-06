package com.bilue.agri.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bilue.agri.MyApplication;
import com.bilue.agri.dialog.FindPwdDialog;
import com.bilue.agri.dialog.RegisterDialog;
import com.bilue.agri.dialog.SetIpDialog;
import com.bilue.agri.request.GetConfigRequest;
import com.bilue.agri.request.LoginRequest;
import com.bilue.bean.Config;
import com.bilue.agri.R;

public class LoginActivity extends BaseActivity {

	private TextView setipTextView; // ����IP
	private EditText nameEdit;
	private EditText passEdit;

	private TextView registtTextView; // ����ע��
	private TextView forgetPass; // �������
	private CheckBox remPass; // ��ס����

	private Button loginButton;

	private ProgressDialog loginDialog;

	private boolean isCheck = true;

	private String name;
	private String pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub

		nameEdit = (EditText) this.findViewById(R.id.login_name);
		nameEdit.setText(MyApplication.share.getName());

		passEdit = (EditText) this.findViewById(R.id.login_pass);
		passEdit.setText(MyApplication.share.getPass());

		registtTextView = (TextView) this.findViewById(R.id.login_register);
		registtTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new RegisterDialog(LoginActivity.this).show();
			}
		});

		forgetPass = (TextView) this.findViewById(R.id.login_forgetpass);
		forgetPass.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new FindPwdDialog(LoginActivity.this).show();
			}
		});

		remPass = (CheckBox) this.findViewById(R.id.login_remberpass);
		remPass.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (!isChecked) {
					isCheck = false;
				} else {
					isCheck = true;
				}
			}
		});

		loginButton = (Button) this.findViewById(R.id.login_login);
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				login();
			}
		});

		setipTextView = (TextView) this.findViewById(R.id.login_setip);
		setipTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new SetIpDialog(LoginActivity.this).show();
			}
		});
	}

	/**
	 * ��¼
	 * 
	 * @param v
	 */
	private void login() {

		name = nameEdit.getText().toString().trim();
		pass = passEdit.getText().toString().trim();

		if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
			Toast.makeText(getApplicationContext(), "�Բ����벹ȫ��¼��Ϣ��",
					Toast.LENGTH_SHORT).show();
			return;
		}

		/**
		 * ��ʼ��¼
		 */
		loginDialog = new ProgressDialog(LoginActivity.this);
		loginDialog.setMessage("���ڵ�¼...");
		loginDialog.show();

		LoginRequest request = new LoginRequest(name, pass);
		startRequest(request);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub

		GetConfigRequest getConfigRequest = new GetConfigRequest();
		startRequest(getConfigRequest);

		super.onDestroy();
	}

	@Override
	protected void update(Object ob) {
		// TODO Auto-generated method stub
		if (ob instanceof Boolean) {
			if ((Boolean) ob) {

				// ��¼�ɹ��������û�������
				mAp.setUsername(name);
				if (isCheck) {
					MyApplication.share.setPass(pass);
				} else {
					MyApplication.share.setPass("");
				}

				loginDialog.cancel();
				Toast.makeText(mAp, "��¼�ɹ���", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(getApplicationContext(),
						MainActivity.class));
				finish();
			} else {
				Toast.makeText(mAp, "��¼ʧ�ܣ�", Toast.LENGTH_SHORT).show();
				loginDialog.cancel();
			}
		} else if (ob instanceof Config) {
			savaConfig((Config) ob);
		}
	}

	private void savaConfig(Config con) {

		Log.i(TAG, "��ʼ���淧ֵ����...");
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

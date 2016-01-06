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
import com.bilue.agri.request.RegisterRequest;
import com.bilue.agri.thread.RequestThread;
import com.bilue.agri.R;

public class RegisterDialog extends Dialog {

	private TextView closeTextView; // �ر�
	private EditText nameEdit, passEdit, emailEdit;
	private Button registerButton;

	private ProgressDialog regitDialog;

	private MyHandler handler;

	public RegisterDialog(Context context) {
		super(context, R.style.dialog_style);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_register);
		setCanceledOnTouchOutside(false);

		handler = new MyHandler();

		initView();
	}

	/**
	 * ��ʼ��Dialog����
	 */
	private void initView() {
		// TODO Auto-generated method stub

		nameEdit = (EditText) this.findViewById(R.id.register_name);

		passEdit = (EditText) this.findViewById(R.id.register_pass);

		emailEdit = (EditText) this.findViewById(R.id.register_email);

		registerButton = (Button) this.findViewById(R.id.register_register);
		registerButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				register();
			}
		});

		closeTextView = (TextView) this.findViewById(R.id.register_close);
		closeTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cancel();
			}
		});
	}

	/**
	 * ע�ᰴť
	 * 
	 * @param v
	 */
	private void register() {

		String name = nameEdit.getText().toString().trim();
		String pass = passEdit.getText().toString().trim();
		String email = emailEdit.getText().toString().trim();

		if (TextUtils.isEmpty(name)) {
			Toast.makeText(getContext(), "�û�����Ϊ�գ�", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(pass)) {
			Toast.makeText(getContext(), "���벻��Ϊ�գ�", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(email)) {
			Toast.makeText(getContext(), "�����ַ����Ϊ�գ�", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		regitDialog = new ProgressDialog(getContext());
		regitDialog.setMessage("����ע��...");
		regitDialog.show();

		if (MyApplication.handler == null) {
			MyApplication.handler = handler;
		} else {
			if (MyApplication.handler != handler) {
				MyApplication.handler = null;
				MyApplication.handler = handler;
			}
		}

		RegisterRequest request = new RegisterRequest(name, pass, email);
		RequestThread thread = new RequestThread(request);
		thread.start();

	}

	@SuppressLint("HandlerLeak")
	private class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.obj != null) {
				if (msg.what == RequestThread.other_result) {
					if ((Boolean) msg.obj) {
						Toast.makeText(getContext(), "ע��ɹ���",
								Toast.LENGTH_SHORT).show();

					} else {
						Toast.makeText(getContext(), "ע��ʧ�ܣ�",
								Toast.LENGTH_SHORT).show();
					}
					regitDialog.cancel();
					cancel();
				}
			}
		}
	}
}

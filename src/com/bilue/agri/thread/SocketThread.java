package com.bilue.agri.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.bilue.agri.MyApplication;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

public class SocketThread extends Thread {

	public static final int PORT = 8892;

	public static final String TAG = "SocketThread";

	private Socket socket;
	private Context context;

	private boolean isStart = true;

	public SocketThread(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {

			BufferedReader in = null;
			PrintWriter out = null;
			String result = "";
			while (isStart) {
				socket = new Socket();
				socket.connect(
						new InetSocketAddress(MyApplication.share.getIp(), PORT),
						5 * 1000);
				while (!socket.isClosed()) {
					in = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
					out = new PrintWriter(socket.getOutputStream());

					out.println("getNotification");
					out.flush();

					result = in.readLine();
					if (!TextUtils.isEmpty(result)) {
						accessResult(new String(Base64.decode(
								result.getBytes(), Base64.DEFAULT)));
					} else {
						Log.w(TAG, "Socket ���ؽ��Ϊ null...");
					}

					try {
						sleep(3 * 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			socket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ���?�ؽ���Թ㲥��ʽ���ͳ�ȥ
	 * 
	 * @param string
	 */
	private void accessResult(String string) {
		// TODO Auto-generated method stub

		Log.i(TAG, "Socket return��" + string);

		Intent intent = new Intent("com.lenove.agri.notify");
		intent.putExtra("message", string);
		context.sendBroadcast(intent);
	}
}

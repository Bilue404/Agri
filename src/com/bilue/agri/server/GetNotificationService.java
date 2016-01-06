package com.bilue.agri.server;

import org.json.JSONException;
import org.json.JSONObject;

import com.bilue.agri.thread.SocketThread;
import com.bilue.agri.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

public class GetNotificationService extends Service {

	public static final String TAG = "GetNotificationService";

	private SocketThread thread;
	private MyBraodCaseReceive receive;

	private NotificationManager manager;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		receive = new MyBraodCaseReceive();
		IntentFilter filter = new IntentFilter("com.lenove.agri.notify");
		registerReceiver(receive, filter);

		manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);

		thread = new SocketThread(getApplicationContext());
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, "֪ͨ����ʼ����..");
		thread.start();
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * �㲥����
	 * 
	 * @author Administrator
	 *
	 */
	private class MyBraodCaseReceive extends BroadcastReceiver {

		JSONObject json = null;

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			try {
				String msgResutl = intent.getStringExtra("msg");

				int notifId = 0;
				String notifMsg = "";
				if (!TextUtils.isEmpty(msgResutl)) {
					json = new JSONObject(msgResutl);
					if (json.has("notifiId")) {
						notifId = json.getInt("notifiId");
					}
					if (json.has("notifiMsg")) {
						notifMsg = json.getString("notifiMsg");
					}
				}

				if (notifId > 0) {
					accessNotify(notifMsg);
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * ����֪ͨ��Ϣ
	 * 
	 * @param notifMsg
	 */
	public void accessNotify(String notifMsg) {
		// TODO Auto-generated method stub

	}

	public void showNotification(String title, String content, int id) {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				getApplicationContext())
				.setSmallIcon(R.drawable.dialog_close)
				.setLargeIcon(
						BitmapFactory.decodeResource(getResources(),
								R.drawable.dialog_close)).setTicker("������Ϣ")
				.setContentTitle(title + "����").setContentText(content)
				.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
				.setOnlyAlertOnce(true);
		Notification notification = builder.build();
		manager.notify(id, notification);
	}

}

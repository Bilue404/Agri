package com.bilue.agri.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.bilue.agri.MyApplication;
import com.bilue.agri.request.BaseRequest;
import com.bilue.agri.request.ContorlRequest;
import com.bilue.agri.request.GetCoStatusRequest;
import com.bilue.agri.thread.RequestThread;
import com.bilue.bean.Controller;
import com.bilue.hint.MyAnimation;
import com.bilue.hint.PlayMusic;
import com.bilue.agri.R;

public class ControlFragment extends Fragment {

	PlayMusic playMusic;
	MyAnimation myAnimation;
	ImageView imgfan;
	ImageView imglight;
	ImageView imgbuzzer;
	ImageView imgwater;

	private static ControlFragment instance = null;
	List<View> list = new ArrayList<View>();

	private ContorlRequest request;
	private GetCoStatusRequest coRequest;
	private List<Switch> switchs;

	private MyHandler mHandler;

	private ControlFragment() {

	}

	public static ControlFragment getInstance() {
		if (instance == null) {
			instance = new ControlFragment();
		}
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		switchs = new ArrayList<Switch>();
		mHandler = new MyHandler();

		playMusic = new PlayMusic(getActivity());
		myAnimation = new MyAnimation(getActivity());

	}

	public void updateStatus() {
		coRequest = new GetCoStatusRequest();
		startRequest(coRequest); // ��ʼ���������״̬
	}

	/**
	 * ��ʼ��������
	 * 
	 * @param bsrequest
	 */
	private void startRequest(BaseRequest bsrequest) {
		if (MyApplication.handler == null) {
			MyApplication.handler = mHandler;
		} else {
			if (MyApplication.handler != mHandler) {
				MyApplication.handler = null;
				MyApplication.handler = mHandler;
			}
		}
		RequestThread thread = new RequestThread(bsrequest);
		thread.start();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_control, null);
		ViewPager viewPager = (ViewPager) view
				.findViewById(R.id.ControlFragment_viewpager);
		View controlLightView = inflater.inflate(
				R.layout.adapter_viewpager_controllight, null);
		imglight = (ImageView) controlLightView.findViewById(R.id.imagelight);
		switchs.add((Switch) controlLightView
				.findViewById(R.id.control_light_switch));

		View controlwaterView = inflater.inflate(
				R.layout.adapter_viewpager_controlwaterbump, null);
		imgwater = (ImageView) controlwaterView.findViewById(R.id.imagewater);
		switchs.add((Switch) controlwaterView
				.findViewById(R.id.control_waterbump_switch));

		View controlbuzzerView = inflater.inflate(
				R.layout.adapter_viewpager_controlbuzzer, null);
		imgbuzzer = (ImageView) controlbuzzerView
				.findViewById(R.id.imagebuzzer);
		switchs.add((Switch) controlbuzzerView
				.findViewById(R.id.control_buzzer_switch));

		View controlfanView = inflater.inflate(
				R.layout.adapter_viewpager_controlfan, null);
		imgfan = (ImageView) controlfanView.findViewById(R.id.imagefan);
		switchs.add((Switch) controlfanView
				.findViewById(R.id.control_fan_switch));

		MySwitchChangeListener listener = new MySwitchChangeListener();
		for (Switch s : switchs) {
			s.setOnCheckedChangeListener(listener);
		}

		list.add(controlLightView);
		list.add(controlbuzzerView);
		list.add(controlfanView);
		list.add(controlwaterView);
		viewPager.setAdapter(new MyPagerAdapter());
		return view;
	}

	/**
	 * Switch �ı�����¼�
	 * 
	 * @author Administrator
	 * 
	 */
	private class MySwitchChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			int i = 0;
			if (isChecked) {
				i = 1;
			}
			// �� ˮ ���� ����
			Controller cont = new Controller();
			switch (buttonView.getId()) {
			case R.id.control_light_switch:
				cont.setRoadlamp(i);
				if (!isChecked) {
					myAnimation.playLightC(imglight);
				} else {
					myAnimation.playLightO(imglight);
				}

				break;

			case R.id.control_waterbump_switch:
				cont.setWaterpump(i);
				if (!isChecked) {
					myAnimation.playWaterC(imgwater);
				} else {
					myAnimation.playWaterO(imgwater);
				}

				break;

			case R.id.control_buzzer_switch:
				cont.setBuzzer(i);
				if (!isChecked) {
					myAnimation.playBuzzerC(imgbuzzer);
				} else {
					myAnimation.playBuzzerO(imgbuzzer);
				}

				break;

			case R.id.control_fan_switch:
				cont.setBlower(i);
				if (!isChecked) {
					myAnimation.playFanC(imgfan);
				} else {
					myAnimation.playFanO(imgfan);
				}

				break;
			}

			request = new ContorlRequest(cont);
			startRequest(request);

			playMusic.play();
		}

	}

	class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager) container).addView(list.get(position));
			return list.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(list.get(position));

		}

	}

	@SuppressLint("HandlerLeak")
	private class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.obj != null) {
				if (msg.what == RequestThread.other_result) {
					update(msg.obj);
				}
			}
		}
	}

	/**
	 * ���󷵻�ֵ����
	 * 
	 * @param obj
	 */
	public void update(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Controller) {
			Controller control = (Controller) obj;
			updateControl(control); // ����Switch״̬

		} else if (obj instanceof Boolean) {
			if ((Boolean) obj) {
				Toast.makeText(getActivity(), "�����ɹ���", Toast.LENGTH_SHORT)
						.show();
			} else {
				Toast.makeText(getActivity(), "����ʧ�ܣ�", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	/**
	 * ����Switch��״̬
	 * 
	 * @param co
	 */
	private void updateControl(Controller co) {
		if (co.getRoadlamp() == 1) {
			switchs.get(0).setChecked(true);
			imglight.setImageResource(R.raw.light4);
		} else {
			imglight.setImageResource(R.raw.light0);
		}
		if (co.getWaterpump() == 1) {
			switchs.get(1).setChecked(true);
			imgwater.setImageResource(R.raw.waterpump4);
		} else {
			imgwater.setImageResource(R.raw.waterpump0);
		}
		if (co.getBuzzer() == 1) {
			switchs.get(2).setChecked(true);
			imgbuzzer.setImageResource(R.raw.buzzer4);
		} else {
			imgbuzzer.setImageResource(R.raw.buzzer0);
		}
		if (co.getBlower() == 1) {
			switchs.get(3).setChecked(true);
			imgfan.setImageResource(R.raw.fan4);
		} else {
			imgfan.setImageResource(R.raw.fan0);
		}
	}
}

package com.bilue.hint;

import com.bilue.agri.R;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class MyAnimation {
	AnimationDrawable fanO;
	AnimationDrawable fanC;
	AnimationDrawable lightO;
	AnimationDrawable lightC;
	AnimationDrawable waterpumpO;
	AnimationDrawable waterpumpC;
	AnimationDrawable buzzerO;
	AnimationDrawable buzzerC;

	public MyAnimation(Context context) {
		fanO = new AnimationDrawable();
		fanO.addFrame(context.getResources().getDrawable(R.raw.fan0), 50);
		fanO.addFrame(context.getResources().getDrawable(R.raw.fan1), 50);
		fanO.addFrame(context.getResources().getDrawable(R.raw.fan2), 50);
		fanO.addFrame(context.getResources().getDrawable(R.raw.fan3), 50);
		fanO.addFrame(context.getResources().getDrawable(R.raw.fan4), 50);
		fanC = new AnimationDrawable();
		fanC.addFrame(context.getResources().getDrawable(R.raw.fan4), 50);
		fanC.addFrame(context.getResources().getDrawable(R.raw.fan3), 50);
		fanC.addFrame(context.getResources().getDrawable(R.raw.fan2), 50);
		fanC.addFrame(context.getResources().getDrawable(R.raw.fan1), 50);
		fanC.addFrame(context.getResources().getDrawable(R.raw.fan0), 50);

		lightO = new AnimationDrawable();
		lightO.addFrame(context.getResources().getDrawable(R.raw.light0), 50);
		lightO.addFrame(context.getResources().getDrawable(R.raw.light1), 50);
		lightO.addFrame(context.getResources().getDrawable(R.raw.light2), 50);
		lightO.addFrame(context.getResources().getDrawable(R.raw.light3), 50);
		lightO.addFrame(context.getResources().getDrawable(R.raw.light4), 50);
		lightC = new AnimationDrawable();
		lightC.addFrame(context.getResources().getDrawable(R.raw.light4), 50);
		lightC.addFrame(context.getResources().getDrawable(R.raw.light3), 50);
		lightC.addFrame(context.getResources().getDrawable(R.raw.light2), 50);
		lightC.addFrame(context.getResources().getDrawable(R.raw.light1), 50);
		lightC.addFrame(context.getResources().getDrawable(R.raw.light0), 50);

		waterpumpO = new AnimationDrawable();
		waterpumpO.addFrame(context.getResources()
				.getDrawable(R.raw.waterpump0), 50);
		waterpumpO.addFrame(context.getResources()
				.getDrawable(R.raw.waterpump1), 50);
		waterpumpO.addFrame(context.getResources()
				.getDrawable(R.raw.waterpump2), 50);
		waterpumpO.addFrame(context.getResources()
				.getDrawable(R.raw.waterpump3), 50);
		waterpumpO.addFrame(context.getResources()
				.getDrawable(R.raw.waterpump4), 50);

		waterpumpC = new AnimationDrawable();
		waterpumpC.addFrame(context.getResources()
				.getDrawable(R.raw.waterpump4), 50);
		waterpumpC.addFrame(context.getResources()
				.getDrawable(R.raw.waterpump3), 50);
		waterpumpC.addFrame(context.getResources()
				.getDrawable(R.raw.waterpump2), 50);
		waterpumpC.addFrame(context.getResources()
				.getDrawable(R.raw.waterpump1), 50);
		waterpumpC.addFrame(context.getResources()
				.getDrawable(R.raw.waterpump0), 50);

		buzzerO = new AnimationDrawable();
		buzzerO.addFrame(context.getResources().getDrawable(R.raw.buzzer0), 50);
		buzzerO.addFrame(context.getResources().getDrawable(R.raw.buzzer1), 50);
		buzzerO.addFrame(context.getResources().getDrawable(R.raw.buzzer2), 50);
		buzzerO.addFrame(context.getResources().getDrawable(R.raw.buzzer3), 50);
		buzzerO.addFrame(context.getResources().getDrawable(R.raw.buzzer4), 50);

		buzzerC = new AnimationDrawable();
		buzzerC.addFrame(context.getResources().getDrawable(R.raw.buzzer4), 50);
		buzzerC.addFrame(context.getResources().getDrawable(R.raw.buzzer3), 50);
		buzzerC.addFrame(context.getResources().getDrawable(R.raw.buzzer2), 50);
		buzzerC.addFrame(context.getResources().getDrawable(R.raw.buzzer1), 50);
		buzzerC.addFrame(context.getResources().getDrawable(R.raw.buzzer0), 50);

		fanO.setOneShot(true);
		fanC.setOneShot(true);
		lightO.setOneShot(true);
		lightC.setOneShot(true);
		waterpumpO.setOneShot(true);
		waterpumpC.setOneShot(true);
		buzzerO.setOneShot(true);
		buzzerC.setOneShot(true);

	}

	public void playFanO(ImageView imageView) {

		imageView.setImageDrawable(fanO);
		fanO.start();

	}

	public void playFanC(ImageView imageView) {
		imageView.setImageDrawable(fanC);
		fanC.start();
	}

	public void playLightO(ImageView imageView) {
		imageView.setImageDrawable(lightO);
		lightO.start();
	}

	public void playLightC(ImageView imageView) {
		imageView.setImageDrawable(lightC);
		lightC.start();
	}

	public void playBuzzerC(ImageView imageView) {
		imageView.setImageDrawable(buzzerC);
		buzzerC.start();
	}

	public void playBuzzerO(ImageView imageView) {
		imageView.setImageDrawable(buzzerO);
		buzzerO.start();
	}

	public void playWaterC(ImageView imageView) {
		imageView.setImageDrawable(waterpumpC);
		waterpumpC.start();
	}

	public void playWaterO(ImageView imageView) {
		imageView.setImageDrawable(waterpumpO);
		waterpumpO.start();
	}

}

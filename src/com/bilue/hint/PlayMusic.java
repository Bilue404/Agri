package com.bilue.hint;

import com.bilue.agri.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class PlayMusic {
	SoundPool soundPool;
	int id;

	public PlayMusic(Context context) {
		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		id = soundPool.load(context, R.raw.music, 0);

	}

	public void play() {
		soundPool.play(id, 1, 1, 0, 0, 1);
	}

}

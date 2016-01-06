package com.bilue.hint;

import java.io.File;

import com.bilue.agri.activity.VideoActivity;
import com.bilue.agri.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class PlayVideo {
	SurfaceView surfaceView;
	MediaPlayer mediaPlayer;
	Context context;

	Uri uri;

	public PlayVideo(SurfaceView surfaceView, Context context, Uri uri) {
		this.surfaceView = surfaceView;
		this.context = context;
		surfaceView.getHolder().addCallback(callback);
		this.uri = uri;
	}

	public void play() {

		try {

			mediaPlayer = new MediaPlayer();

			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setDataSource(context, uri);
			mediaPlayer.setDisplay(surfaceView.getHolder());

			mediaPlayer.prepareAsync();
			mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer mp) {
					mediaPlayer.start();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		mediaPlayer.stop();
		mediaPlayer.release();
	}

	private Callback callback = new Callback() {

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			stop();

		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			Log.d("oncreat", "�����ɹ�");
			play();
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub

		}
	};

}

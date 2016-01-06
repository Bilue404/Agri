package com.bilue.agri.activity;

import com.bilue.hint.PlayVideo;
import com.bilue.agri.R;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceView;

public class VideoActivity extends Activity {
	SurfaceView surfaceView;
	PlayVideo playVideo;

	// Uri uri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
		// uri = Uri.parse("android.resource://" + getPackageName() + "video");
	//	playVideo = new PlayVideo(surfaceView, VideoActivity.this);
		
	}

}

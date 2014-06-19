package com.HackUnlocked.firybrowser;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {
	MediaPlayer ourSong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ourSong = MediaPlayer.create(this, R.raw.msuic);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		ourSong.start();
		Thread t1 = new Thread() {
			public void run() {
				try {
					sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					Intent t = new Intent(
							"com.HackUnlocked.firybrowser.browser");
					startActivity(t);
				}
			}
		};
		t1.start();

	}

	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSong.release();
		finish();
	}

}

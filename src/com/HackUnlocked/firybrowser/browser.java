package com.HackUnlocked.firybrowser;

import java.util.Locale;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class browser extends Activity implements OnClickListener {

	Button b1, b2, b3, b4;
	WebView ourBrow = null;
	String theweb = "http://www.google.com";
	String FILENAME = "browser";
	TextToSpeech tts;
	String value;
	LinearLayout ll;

	@SuppressLint("SetJavaScriptEnabled")
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	//	AdView ad = (AdView) findViewById(R.id.ad);
		//ad.loadAd(new AdRequest());
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.browser);
		init();
		ourBrow = (WebView) findViewById(R.id.webView1);
		ourBrow.setWebChromeClient(new WebChromeClient() {
		});
		ourBrow.getSettings().setJavaScriptEnabled(true);
		ourBrow.getSettings().setBuiltInZoomControls(true);
		ourBrow.getSettings().setLoadWithOverviewMode(true);
		ourBrow.getSettings().setUseWideViewPort(true);
		if (Build.VERSION.SDK_INT < 8) {
			ourBrow.getSettings().setPluginsEnabled(true);
		} else {
			ourBrow.getSettings().setPluginState(PluginState.ON);
		}
		try {
			ourBrow.loadUrl("http://www.google.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		ourBrow.setWebViewClient(new WebViewClient());
		
		
		//tts = new TextToSpeech(browser.this, new TextToSpeech.OnInitListener() {

		//	@Override
			//public void onInit(int status) {
				// TODO Auto-generated method stub
				//if (status != TextToSpeech.ERROR) {
					//tts.setLanguage(Locale.UK);
			//	}
			//}
	//	});

	}

	private void init() {
		// TODO Auto-generated method stub
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b3 = (Button) findViewById(R.id.button3);
		b4 = (Button) findViewById(R.id.button4);
		
		//ll = (LinearLayout)findViewById(R.id.linLayout);
		//AdView ad = new AdView(browser.this,AdSize.BANNER,"a152e426cc633d3");
		//ll.addView(ad);
		//ad.loadAd(new AdRequest());
		
		
		
		
		
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// TODOAuto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blow = getMenuInflater();
		blow.inflate(R.menu.browser_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.item1:
			final AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Love to Hear from you about this app");
			alert.setMessage("If you have any idea which can make this better SAY IT HERE");
			final EditText input = new EditText(this);
			input.setText(ourBrow.getUrl());
			alert.setView(input);
			alert.setPositiveButton("Send",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String EmailAddress[] = { "hi2saif@gmail.com" };
							Intent emailIntent = new Intent(
									android.content.Intent.ACTION_SEND);
							emailIntent.putExtra(
									android.content.Intent.EXTRA_EMAIL,
									EmailAddress);
							emailIntent.putExtra(
									android.content.Intent.EXTRA_SUBJECT,
									"My Email Making");
							emailIntent.setType("plain/text");
							startActivity(emailIntent);
						}
					});
			alert.show();
			break;
		case R.id.item2:
			Intent i = new Intent("com.HackUnlocked.firybrowser.About");
			startActivity(i);
			break;
		case R.id.item3:
			this.finish();
			break;
		}
		return false;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.button1:
			new loadsomestuff().execute(FILENAME);
			if (ourBrow.canGoBack()) {
				ourBrow.goBack();
			}

			break;
		case R.id.button2:
			new loadsomestuff().execute(FILENAME);
			if (ourBrow.canGoForward()) {
				ourBrow.goForward();
			}
			break;
		case R.id.button3:
			new loadsomestuff().execute(FILENAME);
			ourBrow.reload();
			break;
		case R.id.button4:
			final AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle(getResources().getString(R.string.alertHttpTitle));
			alert.setMessage(getResources()
					.getString(R.string.alertHttpSummary));
			final EditText input = new EditText(this);
			input.setText(ourBrow.getUrl());
			alert.setView(input);
			alert.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							value = input.getText().toString().trim();
							new loadsomestuff().execute(FILENAME);
							//tts.speak("You have typed " + value,
								//	TextToSpeech.QUEUE_FLUSH, null);
							if (value.startsWith("http"))
								ourBrow.loadUrl(value);
							else
								ourBrow.loadUrl("http://www.google.com/search?q="
										+ value);
						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							dialog.cancel();
						}
					});
			alert.show();
			break;
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && ourBrow.canGoBack()) {
			ourBrow.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public boolean onLongClick(View arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(browser.this, "In the next version", Toast.LENGTH_SHORT);
		return true;
	}

	public class loadsomestuff extends AsyncTask<String, Integer, String> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			dialog = new ProgressDialog(browser.this);
			dialog.setProgress(ProgressDialog.STYLE_SPINNER);
			dialog.setMax(100);
			dialog.setMessage("Web Page is Loading");
			dialog.setTitle("Please Wait");
			dialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			for (int i = 0; i < 20; i++) {
				publishProgress(5);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dialog.dismiss();
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			dialog.incrementProgressBy(values[0]);
		}

	}

	
	
}

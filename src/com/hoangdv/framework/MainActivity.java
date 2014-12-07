package com.hoangdv.framework;

import com.hoangdv.framework.utils.Utilities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends Activity {
	Utilities utilities;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		utilities = new Utilities(this);
		if (utilities.isConnectingToInternet()) {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Intent intent = new Intent(MainActivity.this,
							SlideActivity.class);
					startActivity(intent);
					finish();
				}
			}, 3000);
		} else {
			Toast.makeText(this, "Device is not connect to network",
					Toast.LENGTH_SHORT).show();
		}
	}
}

package com.hoangdv.framework;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.NetworkImageView;
import com.hoangdv.framework.app.AppController;
import com.hoangdv.framework.util.Utilities;

public class MainActivity extends Activity {

	public static String url = "http://photo.depvd.com/13/295/16/ph_lNgZSnqXz8_ybK6Jt5a_no.jpg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				Log.d("TaG-------","Runing");
//				NetworkImageView ni = (NetworkImageView) findViewById(R.id.imageView1);
//				 ImageLoader loader = AppController.getInstance().getImageLoader();
//				 ni.setImageUrl(url, loader);
//			}
//		}.run();
		getActionBar().hide();
		
		final ImageView img3 = (ImageView) findViewById(R.id.imageView3);
		ImageLoader imageLoader = AppController.getInstance().getImageLoader();
		imageLoader.get(url, new ImageListener() {
			
			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onResponse(ImageContainer arg0, boolean arg1) {
				// TODO Auto-generated method stub
				//img2.setImageBitmap(arg0.getBitmap());
			}
		});
		
		new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap arg0) {
						// TODO Auto-generated method stub
						img3.setImageBitmap(arg0);
					}
				}, 300, 500, null, null);
				AppController.getInstance().addToReqestQueue(request);
			}
		}.run();
		
		(new Utilities(this)).adjustImageAspect(300, 500, img3);
	}
}

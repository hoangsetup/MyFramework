package com.hoangdv.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint.Join;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hoangdv.api.app.AppController;
import com.hoangdv.api.model.Category;
import com.hoangdv.api.model.MyVar;
import com.hoangdv.api.model.NavDrawerItem;
import com.hoangdv.api.util.Utilities;

public class MainActivity extends Activity {
	private Utilities utilities;
	Button loginButton;
	EditText userText, passwordText;
	ProgressDialog dialog;
	private static Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		context = this;
		utilities = new Utilities(context);
		if (!utilities.isConnectingToInternet()) {
			utilities.showAlertDialog("Error", "Lost internet connect", 0);
			return;
		}
		getControlWidget();
		dialog = new ProgressDialog(context);
		dialog.setTitle("Message");
		dialog.setMessage("Please wait authentication...");
		dialog.setCancelable(false);
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final String username = userText.getText().toString();
				final String password = passwordText.getText().toString();
				if (username.isEmpty() || password.isEmpty()) {
					utilities
							.showAlertDialog("Message", "Field is not null", 0);
					return;
				}
				dialog.show();

				StringRequest request = new StringRequest(Method.POST,
						MyVar.URL_LOGIN, new Listener<String>() {
							@Override
							public void onResponse(String response) {
								// TODO Auto-generated method stub

								Log.d("JSON", response);
								int success;
								try {
									JSONObject jsonObject = new JSONObject(
											response);
									success = jsonObject.getInt("success");
									if (success == 1) {
										// Intent intent = new Intent(context,
										// SlideActivity.class);
										// startActivity(intent);
										// finish();
										getCategoryAndRedirectToMainPage();

									} else
										Toast.makeText(
												context,
												"Login fail, username or password wrong!",
												Toast.LENGTH_LONG).show();
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									Toast.makeText(context, "Login Error",
											Toast.LENGTH_LONG).show();
									e.printStackTrace();
								}

							}
						}, new ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError arg0) {
								// TODO Auto-generated method stub
								Log.d("JSONError", arg0.toString());
								if (dialog != null)
									dialog.dismiss();
							}
						}) {
					@Override
					protected Map<String, String> getParams()
							throws AuthFailureError {
						// TODO Auto-generated method stub
						Map<String, String> params = new HashMap<String, String>();
						params.put("username", username);
						params.put("password", password);
						return params;
					}
				};
				AppController.getInstance().addToReqestQueue(request);
			}
		});
	}

	public void getControlWidget() {
		loginButton = (Button) findViewById(R.id.button_login);
		userText = (EditText) findViewById(R.id.editText_user);
		passwordText = (EditText) findViewById(R.id.editText_password);
	}

	public void getCategoryAndRedirectToMainPage() {
		dialog.setMessage("Get category");
		StringRequest request = new StringRequest(Method.POST,
				MyVar.URL_CATEGORY, new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub
						if (dialog != null)
							dialog.dismiss();
						try {
							JSONObject jsonObject = new JSONObject(arg0);
							if (jsonObject.getInt("success") == 1) {
								JSONArray jsonArray = jsonObject
										.getJSONArray("Categorys");

								Log.d("Json cate: ", arg0.toString());

								for (int i = 0; i < jsonArray.length(); i++) {
									JSONObject object = jsonArray
											.getJSONObject(i);
									Log.d("Json cate object: ",
											object.toString());
									Log.d("name", object.getString("Name"));

									Category category = new Category();
									category.setPK_iCategoryId(object
											.getInt("PK_iCategoryId"));
									category.setsName(object.getString("Name"));
									MyVar.categories.add(category);

									MyVar.navDrawerItems
											.add(new NavDrawerItem(object
													.getString("Name"),
													R.drawable.ic_home, true,
													"10"));
									Log.d("Vector",MyVar.navDrawerItems.get(i).getTitle());

								}

								//
								Intent intent = new Intent(context,
										SlideActivity.class);
								startActivity(intent);
								finish();
							} else {
								Toast.makeText(context, "Error get category!",
										Toast.LENGTH_SHORT).show();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub

					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String, String> params = new HashMap<String, String>();
				params.put("PK_iCategoryId", "0");
				return params;
			}
		};
		AppController.getInstance().addToReqestQueue(request);
	}
}

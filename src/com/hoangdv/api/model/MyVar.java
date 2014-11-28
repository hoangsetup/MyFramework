package com.hoangdv.api.model;

import java.util.Vector;

public class MyVar {
	public static String SERVER = "http://10.0.3.2";
	public static String URL_LOGIN = SERVER + "/API/Login.aspx?action=login";
	public static String URL_CATEGORY = SERVER + "/API/Category.aspx";
	
	public static Vector<NavDrawerItem> navDrawerItems = new Vector<NavDrawerItem>();

	public static Vector<Category> categories = new Vector<Category>();
}

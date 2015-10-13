package com.shephertz.app42.ma.sample;

import android.provider.Settings;

import com.shephertz.app42.iam.App42CampaignAPI;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42Log;
import com.shephertz.app42.paas.sdk.android.app.App42Application;

public class MyApp extends App42Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		//My app Keys
		 App42API.initialize(
		   		 this,
		   		 "Your API Key ",
		   		 "Your Secret Key");
		   		App42Log.setDebug(true);
		   		String userId=Settings.Secure.getString(
					getContentResolver(), "android_id");
		   		App42API.setLoggedInUser(userId);
		   		App42CampaignAPI.enable(true);
		   		//Config cache time in minutes
		   		App42CampaignAPI.setConfigCacheTime(1); 
		   		//To track AppSession as well event
		   		App42API.enableAppStateEventTracking(true);	 
	}
	
	
}
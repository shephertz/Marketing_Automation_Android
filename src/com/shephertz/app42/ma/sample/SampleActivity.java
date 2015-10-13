package com.shephertz.app42.ma.sample;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shephertz.app42.ma.sample.App42Event.App42EventListener;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42Response;

public class SampleActivity extends Activity implements App42EventListener {

	private TextView resultTv;
	private EditText editEventNAme, editPropEventKey, editPropEventValue,
			editUserPropsKey, editUserPropsValue,editUserId;
	private JSONObject userProperties;
	private JSONObject eventproperties;

	private App42Event app42Event;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
		setContentView(R.layout.event);
		resultTv = (TextView) findViewById(R.id.result);
		userProperties = new JSONObject();
		eventproperties = new JSONObject();
		intializeViews();
		app42Event = new App42Event(this);
	}

	/**
	 * 
	 */
	private void intializeViews() {
		editUserPropsKey = (EditText) findViewById(R.id.editKey);
		editUserPropsValue = (EditText) findViewById(R.id.editValue);
		editEventNAme = (EditText) findViewById(R.id.editActivtyEventName);
		editPropEventKey = (EditText) findViewById(R.id.editEventKey);
		editPropEventValue = (EditText) findViewById(R.id.editEventValue);
		editUserId=(EditText) findViewById(R.id.editUserId);
	}

	/**
	 * @param view
	 */
	public void onTrackEvent(View view) {
		App42API.setLoggedInUser(editUserId.getText().toString());
		resultTv.setText("Tracking Event....");
		String eventName = editEventNAme.getText().toString();
		if (eventName == null || eventName.equals(""))
			Toast.makeText(this, "Event Name Should not be blank",
					Toast.LENGTH_SHORT).show();
		else
			app42Event.trackEvent(eventName,eventproperties);
	}

	/**
	 * @param view
	 */
	public void onAddEventProps(View view) {
      String key=editPropEventKey.getText().toString();
      String value=editPropEventValue.getText().toString();
  	if (key == null || key.equals("")||value == null || value.equals(""))
		Toast.makeText(this, "Event properties can't be empty",
				Toast.LENGTH_SHORT).show();
  	try {
		eventproperties.put(key, value);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	/**
	 * @param view
	 */
	public void onAddUserProps(View view) {
	    String key=editUserPropsKey.getText().toString();
	      String value=editUserPropsValue.getText().toString();
	  	if (key == null || key.equals("")||value == null || value.equals(""))
			Toast.makeText(this, "User properties can't be empty",
					Toast.LENGTH_SHORT).show();
	  	try {
			userProperties.put(key, value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param view
	 */
	public void onStartActivity(View view) {
		App42API.setLoggedInUser(editUserId.getText().toString());
		resultTv.setText("Tracking Start Event....");
		String eventName = editEventNAme.getText().toString();
		if (eventName == null || eventName.equals(""))
			Toast.makeText(this, "Event Name Should not be blank",
					Toast.LENGTH_SHORT).show();
		else
			app42Event.startActivityEvent(eventName,eventproperties);
	}

	/**
	 * @param view
	 */
	public void onSetProperties(View view) {
		App42API.setLoggedInUser(editUserId.getText().toString());
		resultTv.setText("Setting User Properties....");
		app42Event.setUserProps(userProperties);
	}

	/**
	 * @param view
	 */
	public void onUpdateProperties(View view) {
		resultTv.setText("Updating User Properties....");
		App42API.setLoggedInUser(editUserId.getText().toString());
		app42Event.updateUserProps(userProperties);
	}

	/**
	 * @param view
	 */
	public void onEndActivity(View view) {
		App42API.setLoggedInUser(editUserId.getText().toString());
		resultTv.setText("Tracking End Event....");
		String eventName = editEventNAme.getText().toString();
		if (eventName == null || eventName.equals(""))
			Toast.makeText(this, "Event Name Should not be blank",
					Toast.LENGTH_SHORT).show();
		else
			app42Event.endActivityEvent(eventName,eventproperties);
	}

	/**
	 * @param response
	 */
	public void onResult(final App42Response response) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// System.out.println(response.toString());
				resultTv.setText("App42Response : " + response);
			}
		});

	}

	/**
	 * @param e
	 */
	public void onError(final Exception e) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				resultTv.setText(e.getMessage());
			}
		});
	}
}

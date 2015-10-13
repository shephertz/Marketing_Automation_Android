/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.shephertz.app42.ma.sample;

import org.json.JSONObject;

import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42CallBack;
import com.shephertz.app42.paas.sdk.android.App42Response;

/**
 * @author Vishnu Garg
 */
public class App42Event {

	private App42EventListener eventListener;
	
	public App42Event(App42EventListener eventListener) {
		super();
		this.eventListener = eventListener;  
	}

//	/**
//	 * @return
//	 */
//	private JSONObject getLevelProps() {
//		JSONObject levelProps = new JSONObject();
//		try {
//			levelProps.put("displayName", "Tiwariji");
//			levelProps.put("country", "in");
//			levelProps.put("longtitude", 28.123);
//			levelProps.put("lattitude", -76.123);
//			levelProps.put("levelPoints", 100);
//			levelProps.put("levelRewardPoints", 50);
//			levelProps.put("levelHigScore", 1000);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return levelProps;
//	}

	/**
	 * @param activtyEventName
	 */
	public void startActivityEvent(final String activtyEventName,JSONObject properties) {
		App42API.buildEventService().startActivity(activtyEventName,
				properties, new App42CallBack() {

					@Override
					public void onSuccess(Object response) {
						// TODO Auto-generated method stub
						eventListener.onResult((App42Response) response);
						
					}

					@Override
					public void onException(Exception ex) {
						// TODO Auto-generated method stub
						eventListener.onError(ex);
					}
				});
	}

//	/**
//	 * @return
//	 */
//	private JSONObject getEventDefJSON() {
//		JSONObject eventProps = new JSONObject();
//		try {
//			eventProps.put("hurdleType", "Rock");
//			eventProps.put("noOfHurdlesCleared", 8);
//			eventProps.put("noOfEnemykilled", 10);
//			eventProps.put("lifeCosumed", 5);
//			eventProps.put("lifeRemaining", 5);
//			eventProps.put("bonusGet", 50);
//			eventProps.put("levelCompleted", "45%");
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return eventProps;
//	}

	public void trackEvent(final String trackEventName,JSONObject properties) {
		App42API.buildEventService().trackEvent(trackEventName,
				properties, new App42CallBack() {

					@Override
					public void onSuccess(Object response) {
						// TODO Auto-generated method stub
						eventListener.onResult((App42Response) response);
					
					}

					@Override
					public void onException(Exception ex) {
						// TODO Auto-generated method stub
						eventListener.onError(ex);
					}
				});
	}

	/**
	 * @param view
	 */
	public void endActivityEvent(final String activtyEventName,JSONObject properties) {

		App42API.buildEventService().endActivity(activtyEventName,
				properties, new App42CallBack() {

					@Override
					public void onSuccess(Object response) {
						// TODO Auto-generated method stub
						eventListener.onResult((App42Response) response);
					}

					@Override
					public void onException(Exception ex) {
						// TODO Auto-generated method stub
						eventListener.onError(ex);
					}
				});
	}

	public void updateUserProps(JSONObject newProperties) {
		App42API.buildEventService().updateLoggedInUserProperties(
				newProperties, new App42CallBack() {
					@Override
					public void onSuccess(Object response) {

						eventListener.onResult((App42Response) response);
					}

					@Override
					public void onException(Exception ex) {
						// TODO Auto-generated method stub
						eventListener.onError(ex);
					}
				});
	}

	public void setUserProps(JSONObject newProperties) {
		App42API.buildEventService().setLoggedInUserProperties(newProperties,
				new App42CallBack() {
					@Override
					public void onSuccess(Object response) {

						eventListener.onResult((App42Response) response);
					}

					@Override
					public void onException(Exception ex) {
						// TODO Auto-generated method stub
						eventListener.onError(ex);
					}
				});
	}

	public interface App42EventListener {
		public void onResult(App42Response response);

		public void onError(Exception e);
	}
}

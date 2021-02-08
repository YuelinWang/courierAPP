package com.example.administrator.kuaidi.jpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.kuaidi.Activity.Main2Activity;
import com.example.administrator.kuaidi.ModelBean.MsgBean;
import com.example.administrator.kuaidi.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "++";//"JIGUANG-Example";

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Bundle bundle = intent.getExtras();
            Log.e(TAG, "onReceive: " + intent.getAction()  );
			Logger.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

			if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
				String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
				Logger.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
				//send the Registration Id to your server...

			} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
				Log.e(TAG, "onReceive: " + JPushInterface.ACTION_MESSAGE_RECEIVED + "  " + bundle.toString());
				//MsgBean msgBean = GsonUtils.GsonToBean(bundle.toString(),MsgBean.class);
				//Log.e(TAG, "onReceive: " + JPushInterface.ACTION_MESSAGE_RECEIVED + "  " + msgBean.getTitle()+ "  " + msgBean.getUid() + " " +msgBean.getContent() );
				Log.e(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
				//processCustomMessage(context, bundle);

			} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知");
				int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
				Log.e(TAG, "onReceive: " + JPushInterface.ACTION_NOTIFICATION_RECEIVED + "  " + bundle.toString());
				//processCustomMessage(context, bundle);

			} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
				//Logger.d(TAG, "[MyReceiver] 用户点击打开了通知");
				Log.e(TAG, "onReceive: " + JPushInterface.ACTION_NOTIFICATION_OPENED+ "  " + bundle.toString());
				//打开自定义的Activity
			/*	Intent i = new Intent(context, TestActivity.class);
				i.putExtras(bundle);
				//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
				context.startActivity(i);*/

			} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
				//在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

			} else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
				boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
				Logger.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
			} else {
				Logger.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
			}
		} catch (Exception e){

		}

	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
					Logger.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));

					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next();
						sb.append("\nkey:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
					Logger.e(TAG, "Get message extra JSON error!");
				}

			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.get(key));
			}
		}
		return sb.toString();
	}
	
	//send msg to ChatMsgActivity
	private void processCustomMessage(Context context, Bundle bundle) {
        //Vibrator vibrator = (Vibrator)context.getSystemService(Service.VIBRATOR_SERVICE);
       // long[] patter = {500, 500};
       // vibrator.vibrate(patter,-1);
        MsgBean msgbean = new MsgBean();
        for (String key : bundle.keySet()){
			Log.e(TAG, "processCustomMessage: " + key );
			if (key.equals(JPushInterface.EXTRA_ALERT) ) {
				if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_ALERT))) {
					Logger.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_ALERT));
					String jsonstr = json.toString();
					Gson gson = new Gson();
					msgbean = gson.fromJson(jsonstr, MsgBean.class);
					Log.e(TAG, "printBundle: " +jsonstr + "uid:"+msgbean.getUid() + "  title:"+ msgbean.getTitle() + "  " + msgbean.getContent()  );

				} catch (JSONException e) {
					Logger.e(TAG, "Get message extra JSON error!");
				}
			}
            /*if (key.equals(JPushInterface.EXTRA_MESSAGE) )) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_MESSAGE))) {
                    Logger.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_MESSAGE));
                    String jsonstr = json.toString();
                    Gson gson = new Gson();
                    msgbean = gson.fromJson(jsonstr, MsgBean.class);
                    Log.e(TAG, "printBundle: " +jsonstr + "uid:"+msgbean.getUid() + "  title:"+ msgbean.getTitle() + "  " + msgbean.getContent()  );

                } catch (JSONException e) {
                    Logger.e(TAG, "Get message extra JSON error!");
                }
            }*/
        }
		/*if (ChatMsgActivity.isForeground()) {
			Log.e(TAG, "processCustomMessage: " + "send broadcast" );
			//String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
			//String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
			Intent msgIntent = new Intent(ChatMsgActivity.MESSAGE_RECEIVED_ACTION);
			//msgIntent.putExtra(ChatMsgActivity.KEY_MESSAGE, message);
			Bundle bundle1 = new Bundle();
			bundle1.putSerializable("chat", msgbean);
			msgIntent.putExtras(bundle1);
			LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
		}else{
			//Log.e(TAG, "processCustomMessage: " + "not chat ui" );
			CreateNotification(context, msgbean);
		}*/
		Log.e(TAG, "processCustomMessage: " + msgbean.getTitle() + "  " +msgbean.getContent() + "  " + msgbean.getUid() );
		CreateNotification(context, msgbean);
	}

	private void CreateNotification(Context context, MsgBean msgBean){
	    if(msgBean == null){
	        //return;
        }
		Intent intent = new Intent(context, Main2Activity.class);
		PendingIntent pi = PendingIntent.getActivity(context,0,intent,0);
		NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new NotificationCompat.Builder(context)
				.setContentTitle(msgBean.getTitle())
				.setContentText(msgBean.getContent())
				.setSmallIcon(R.mipmap.msg)
				.setDefaults(NotificationCompat.DEFAULT_ALL)
				.setContentIntent(pi)
				.setAutoCancel(true)
				.build();
		manager.notify(1,notification);
	}
}

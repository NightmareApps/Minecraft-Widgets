package com.Nightmare.MinecraftWidgets;

import java.util.Date;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.widget.RemoteViews;
import android.widget.Toast;

public final class BatteryReceiver extends Service {

	public double batterypercentage;

	public void toastify(String toDisplay, Context context) {
		Toast.makeText(context, toDisplay, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		buildUpdate();

		return super.onStartCommand(intent, flags, startId);
	}

	private void buildUpdate() {
		String lastUpdated = DateFormat.format("MMMM dd, yyyy h:mmaa",
				new Date()).toString();

		RemoteViews views = new RemoteViews(getPackageName(),
				R.layout.battery_layout);

		// Push update for this widget to the home screen
		ComponentName thisWidget = new ComponentName(this,
				BatteryWidgetProvider.class);

		batteryPercentage();
//		toastify(batteryPercentage() + "", this.getApplicationContext());
		if (batterypercentage < 10) {
			views.setImageViewResource(R.id.batteryvalueimage, R.drawable.heart0);
//			toastify("<10", this.getApplicationContext());
		} else if (batterypercentage < 20) {
			views.setImageViewResource(R.id.batteryvalueimage, R.drawable.heart1);
//			toastify("<20", this.getApplicationContext());
		} else if (batterypercentage < 30) {
			views.setImageViewResource(R.id.batteryvalueimage, R.drawable.heart2);
//			toastify("<30", this.getApplicationContext());
		} else if (batterypercentage < 40) {
			views.setImageViewResource(R.id.batteryvalueimage, R.drawable.heart3);
//			toastify("<40", this.getApplicationContext());
		} else if (batterypercentage < 50) {
			views.setImageViewResource(R.id.batteryvalueimage, R.drawable.heart4);
//			toastify("<50", this.getApplicationContext());
		} else if (batterypercentage < 60) {
			views.setImageViewResource(R.id.batteryvalueimage, R.drawable.heart5);
//			toastify("<60", this.getApplicationContext());
		} else if (batterypercentage < 70) {
			views.setImageViewResource(R.id.batteryvalueimage, R.drawable.heart6);
//			toastify("<70", this.getApplicationContext());
		} else if (batterypercentage < 80) {
			views.setImageViewResource(R.id.batteryvalueimage, R.drawable.heart7);
//			toastify("<80", this.getApplicationContext());
		} else if (batterypercentage < 90) {
			views.setImageViewResource(R.id.batteryvalueimage, R.drawable.heart8);
//			toastify("<90", this.getApplicationContext());
		} else if (batterypercentage < 100) {
			views.setImageViewResource(R.id.batteryvalueimage, R.drawable.heart9);
//			toastify("<100", this.getApplicationContext());
		} else if (batterypercentage >= 100) {
			views.setImageViewResource(R.id.batteryvalueimage, R.drawable.heart10);
//			toastify(">=100", this.getApplicationContext());
		}
		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(getBaseContext());
		appWidgetManager.updateAppWidget(new ComponentName(getBaseContext(),
				BatteryWidgetProvider.class), views);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	public double batteryPercentage() {
		Intent batteryIntent = this.getApplicationContext().registerReceiver(
				null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		int rawlevel = batteryIntent.getIntExtra("level", -1);

		double scale = batteryIntent.getIntExtra("scale", -1);
		double level = -1;
		if (rawlevel >= 0 && scale > 0) {
			level = (rawlevel / scale) * 100;
			batterypercentage = (int) level;
		}
		return batterypercentage;
	}

}

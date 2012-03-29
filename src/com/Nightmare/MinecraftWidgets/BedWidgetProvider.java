package com.Nightmare.MinecraftWidgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class BedWidgetProvider extends AppWidgetProvider {

	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		final int N = appWidgetIds.length;

		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];

			Intent intent = new Intent(context, BedReceiver.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
					0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.bed_layout);

			views.setOnClickPendingIntent(R.id.bed_button, pendingIntent);
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}

	public void toastify(String toDisplay, Context context) {
		Toast.makeText(context, toDisplay, Toast.LENGTH_LONG).show();
	}

}

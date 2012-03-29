package com.Nightmare.MinecraftWidgets;

//GITHUB COMMIT

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.Nightmare.MinecraftWidgets.R;

public class TorchReceiver extends BroadcastReceiver {

	private static Camera camera;
	private static boolean isLightOn = false;

	@Override
	public void onReceive(Context context, Intent intent) {
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.torch_layout);

		if (isLightOn) {
			views.setImageViewResource(R.id.torch_button, R.drawable.off);
		} else {
			views.setImageViewResource(R.id.torch_button, R.drawable.on);
		}

		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(context);
		appWidgetManager.updateAppWidget(new ComponentName(context,
				TorchWidgetProvider.class), views);
		if (isLightOn) {
			if (camera != null) {
				camera.stopPreview();
				camera.release();
				camera = null;
			}
			isLightOn = false;
		} else {
			// Open the default i.e. the first rear facing camera.
			camera = Camera.open();

			if (camera == null) {
				Toast.makeText(context, R.string.no_camera, Toast.LENGTH_SHORT)
						.show();
			} else {
				// Set the torch flash mode
				Parameters param = camera.getParameters();
				param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
				try {
					camera.setParameters(param);
					camera.startPreview();
					isLightOn = true;
				} catch (Exception e) {
					Toast.makeText(context, R.string.no_flash,
							Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

	public void toastify(String toDisplay, Context context) {
		Toast.makeText(context, toDisplay, Toast.LENGTH_LONG).show();
	}

}

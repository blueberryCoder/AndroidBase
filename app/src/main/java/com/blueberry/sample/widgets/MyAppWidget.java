package com.blueberry.sample.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.blueberry.sample.R;

/**
 * Created by blueberry on 2016/8/5.
 * 桌面小部件
 */
public class MyAppWidget extends AppWidgetProvider {

    private static final String TAG = "MyAppWidget";

    public static final String CLICK_ACTION = "com.blueberry.sample.appwidget_CLICK";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        super.onReceive(context, intent);

        Log.i(TAG, "onReceiver: action = " + intent.getAction());

        if (intent.getAction().equals(CLICK_ACTION)) {
            Toast.makeText(context, "click it", Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap srcBitmap = BitmapFactory.decodeResource(context.getResources(),
                            R.mipmap.ic_launcher);
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    for (int i = 0; i < 37; i++) {
                        float degree = (i * 10) % 360;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                                R.layout.app_widget);
                        remoteViews.setImageViewBitmap(R.id.iv,
                                rotateBitmap(srcBitmap, degree));
                        Intent intentClick = new Intent();
                        intentClick.setAction(CLICK_ACTION);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        remoteViews.setOnClickPendingIntent(R.id.iv, pendingIntent);
                        appWidgetManager.updateAppWidget(new ComponentName(context,
                                MyAppWidget.class), remoteViews);
                        SystemClock.sleep(30);
                    }
                }
            }).start();

        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(TAG, "onUpdate.");
        final int counter = appWidgetIds.length;
        Log.i(TAG, "counter: " + counter);
        for (int i = 0; i < counter; i++) {
            int appWidgetId = appWidgetIds[i];
            onWidgetUpdate(context, appWidgetManager, appWidgetId);
        }
    }

    private void onWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Log.i(TAG, "appWidgetId= " + appWidgetId);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        Intent intentClick = new Intent();
        intentClick.setAction(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0);
        remoteViews.setOnClickPendingIntent(R.id.iv, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    private static Bitmap rotateBitmap(Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

}

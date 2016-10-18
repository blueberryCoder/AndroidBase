package com.blueberry.sample.module.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RemoteViews;

import com.blueberry.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by blueberry on 2016/8/5.
 */
public class NotifyFragment extends Fragment {

    @BindView(R.id.btn_send_notification)
    Button btnSendNotification;

    public static NotifyFragment newInstance() {

        Bundle args = new Bundle();

        NotifyFragment fragment = new NotifyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fg_notify, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @OnClick(R.id.btn_send_notification)
    public void onClick() {
        NotificationManager notificationManager = (NotificationManager) getContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            notification = new Notification(R.mipmap.ic_launcher, "tickerText",
                    System.currentTimeMillis());
            RemoteViews remoteViews = new RemoteViews(getContext().getPackageName()
                    ,R.layout.app_widget);
            notification.contentView =remoteViews;
            notification.defaults = Notification.DEFAULT_ALL;
            notification.vibrate =new long[]{200, 200};
            notification.ledARGB = Color.argb(255, 255, 0, 0);
        } else {
            notification = new Notification.Builder(getContext())
                    .setContentText("ContextText")
                    .setTicker("ticker")
                    .setSubText("subText")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setLights(Color.argb(255, 255, 0, 0), 200, 200)
                    .setDefaults(Notification.FLAG_SHOW_LIGHTS | Notification.DEFAULT_VIBRATE)
                    .setVibrate(new long[]{200, 200}).build();
        }
        notificationManager.notify(0, notification);
    }
}

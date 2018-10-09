package com.sample.lifecycleobserver.observers;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import com.sample.lifecycleobserver.util.BaseClass;

public class NetworkChangeListener extends BaseClass implements LifecycleObserver {
    private  Context context;
    private VideoView videoView;
    private TextView textView;
    private final IntentFilter networkIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    private final BroadcastReceiver networkBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                if (checkNetworkConnection(context)) {
                    videoView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                } else {
                    videoView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                    videoView.start();
                }
            }
        }
    };

    private NetworkChangeListener(){
        //To avoid instance creation of non arguement constructor.
    }

    public NetworkChangeListener(Context context,VideoView videoView, TextView textView) {
        this.context = context;
        this.videoView = videoView;
        this.textView = textView;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        context.registerReceiver(networkBroadcastReceiver, networkIntentFilter);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        context.unregisterReceiver(networkBroadcastReceiver);
    }
}
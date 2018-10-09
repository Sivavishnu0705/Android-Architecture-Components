package com.sample.lifecycleobserver.observers;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import com.sample.lifecycleobserver.R;
import com.sample.lifecycleobserver.util.BaseClass;

public class VideoPlayer extends BaseClass implements LifecycleObserver {
    private  Context context;
    private VideoView videoView;
    private int videoPlayedSeconds;

    private VideoPlayer(){
        //To avoid instance creation of non arguement constructor.
    }

    public VideoPlayer(Context context, VideoView videoView) {
        this.context = context;
        this.videoView = videoView;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        final MediaController mediacontroller = new MediaController(context);
        mediacontroller.setAnchorView(videoView);
        videoView.setMediaController(mediacontroller);
        videoView.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.android_lifecycle_aware_components_android_architecture));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        if (!checkNetworkConnection(context)) {
            videoView.start();
            videoView.seekTo(videoPlayedSeconds);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        videoPlayedSeconds = videoView.getCurrentPosition();
    }
}
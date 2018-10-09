package com.sample.lifecycleobserver.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.VideoView;

import com.sample.lifecycleobserver.observers.NetworkChangeListener;
import com.sample.lifecycleobserver.R;
import com.sample.lifecycleobserver.observers.VideoPlayer;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView videoView = findViewById(R.id.vv_video);
        getLifecycle().addObserver(new VideoPlayer(this, videoView));
        getLifecycle().addObserver(new NetworkChangeListener(this, videoView,(TextView) findViewById(R.id.tv_text)));
    }
}
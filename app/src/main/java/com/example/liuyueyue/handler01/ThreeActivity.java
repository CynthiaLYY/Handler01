package com.example.liuyueyue.handler01;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by liuyueyue on 2017/8/26.
 */

public class ThreeActivity extends AppCompatActivity {
    private TextView text;
    private HandlerThread thread;
    private Handler handler;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = new TextView(this);
        text.setText("handler Thread");
        setContentView(text);
        thread = new HandlerThread("handler Thread");
        thread.start();
        handler = new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.print("currentThread:"+Thread.currentThread());

            }
        };
      handler.sendEmptyMessage(1);
    }
}

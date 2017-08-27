package com.example.liuyueyue.handler01;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by liuyueyue on 2017/8/27.
 */

public class FiveActivity extends AppCompatActivity {

    private TextView tv1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           tv1.setText("ok2");
        }
    };
    private void handle1(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv1.setText("ok1");
            }
        });
    }
    private void handler2(){
        handler.sendEmptyMessage(1);
    }
    private void updateUI(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv1.setText("ok3");
            }
        });
    }
    private void viewUI(){
        tv1.post(new Runnable() {
            @Override
            public void run() {
                tv1.setText("ok4");
            }
        });
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        tv1 = (TextView) findViewById(R.id.tv1);
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    //handle1();
                   // handler2();
                    //updateUI();
                    viewUI();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

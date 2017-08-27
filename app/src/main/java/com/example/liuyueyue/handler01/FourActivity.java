package com.example.liuyueyue.handler01;

import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.button;

public class FourActivity extends AppCompatActivity implements View.OnClickListener {
//创建主线程的handler
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Message message = new Message();
            System.out.print("main handler");
            //向子线程发送消息
            threadHandler.sendMessageDelayed(message,1000);
        }
    };
    private Handler threadHandler;
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);

        HandlerThread thread = new HandlerThread("handler thread");
        thread.start();
        //创建子线程的handler
        threadHandler = new Handler(thread.getLooper()){
            public void handleMessage(Message msg) {
                Message message = new Message();
                System.out.print("Thread handler");
                //向主线程发送消息
               handler.sendMessageDelayed(message,1000);
            }
        };
    }
    public void onClick(View  view) {
        switch (view.getId()){
            case R.id.button:
                handler.sendEmptyMessage(1);
                break;

            case R.id.button2:
                handler.removeMessages(1);
                break;

            default:
                break;
        }
    }
}

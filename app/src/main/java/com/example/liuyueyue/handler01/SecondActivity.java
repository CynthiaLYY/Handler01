package com.example.liuyueyue.handler01;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            System.out.print("UI-->"+Thread.currentThread());
        }
    };
    //handler负责消息的发送，就是向MessageQueue队列发送消息
    // Looper负责接收Handler发送的消息，并直接把消息发送给handler自己
    //MessageQueue就是一个存储消息的容器，就是一个纤细队列，可以添加消息，并处理消息

    class MyThread extends Thread{
        public Handler hander;
        @Override
        public void run() {
            Looper.prepare();
            hander = new Handler(){
                public void handleMessage(Message msg){
                    System.out.print("currentThread:"+Thread.currentThread());
                }
            };
            Looper.loop();
        }
    }
    private MyThread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        thread =new MyThread();
        thread.start();
        try {
            thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.hander.sendEmptyMessage(1);
        handler.sendEmptyMessage(1);
    }
}

package com.example.liuyueyue.handler01;

import android.app.Notification;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            Toast.makeText(getApplicationContext(), ""+1 ,Toast.LENGTH_SHORT).show();

            return true;
        }
    }) {
        public void handleMessage(Message msg){
            Toast.makeText(getApplicationContext(), ""+2 ,Toast.LENGTH_SHORT).show();

            //textView.setText(""+msg.obj);
            //textView.setText("吴亦凡"+msg.arg1+"么么哒"+msg.arg2);
        }
    };
    private ImageView imageView;
    private int images[] = {R.drawable.wyf,R.drawable.wyf2,R.drawable.wyf3,
            R.drawable.wyf4,R.drawable.wyf5,R.drawable.wyf6};
    private int index;
    private MyRunnable myRunnable = new MyRunnable();
    private Button button;

    @Override
    public void onClick(View view) {
        //handler.removeCallbacks(myRunnable);
        handler.sendEmptyMessage(1);
    }

    class Person{
        public int age;
        public String name;

        @Override
        public String toString() {
            return "name="+name+";"+"age+"+age;
        }
    }

    class MyRunnable implements Runnable{
        @Override
        public void run() {
            index++;
            index = index%6;
            imageView.setImageResource(images[index]);
            handler.postDelayed(myRunnable,1000);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.btn1);
        button.setOnClickListener(this);
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    /*Message message = new Message();
                    message.arg1 = 666;
                    message.arg2 =999;*/
                    Message message = handler.obtainMessage();
                    Person person = new Person();
                    person.age=23;
                    person.name="nate";
                    message.obj=person;
                    message.sendToTarget();
                    //handler.sendMessage(message);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        handler.postDelayed(myRunnable,1000);

        /*new Thread(){
            public void run(){
                try {
                    Thread.sleep(1000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("update thread");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();*/
    }
}

package com.sheygam.java_18_12_03_18;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar myProgress, horProgress;
    private Button startBtn;
    private Handler handler;
    private TextView countTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myProgress = findViewById(R.id.my_progress);
        horProgress = findViewById(R.id.hor_progress);
        startBtn = findViewById(R.id.start_btn);
        countTxt = findViewById(R.id.count_txt);
        startBtn.setOnClickListener(this);
//        handler = new Handler(callback);
        handler = new Handler();
    }

    private Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    myProgress.setVisibility(View.VISIBLE);
                    startBtn.setEnabled(false);
                    break;
                case 2:
                    myProgress.setVisibility(View.INVISIBLE);
                    startBtn.setEnabled(true);
                    break;
                case 3:
                    countTxt.setText(String.valueOf(msg.arg1));
                    break;
            }
            return true;
        }
    };

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.start_btn){
//            new MyWorker(handler).start();

            startWorker();

//            myProgress.setVisibility(View.VISIBLE);
//            startBtn.setEnabled(false);
//            startWorkThread();
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    private void startWorkThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myProgress.setVisibility(View.INVISIBLE);
                startBtn.setEnabled(true);
            }
        }).start();
    }

    private void startWorker(){
        new Thread(new Runnable() {
            int i = 0;
            @Override
            public void run() {

                for (; i < 100; i++) {
                    Log.d("MY_TAG", "run: " + i);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            horProgress.setProgress(i+1);
                        }
                    });

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

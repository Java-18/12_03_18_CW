package com.sheygam.java_18_12_03_18;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

/**
 * Created by gregorysheygam on 12/03/2018.
 */

public class MyWorker extends Thread {
    private Handler handler;

    public MyWorker(@NonNull Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        handler.sendEmptyMessage(1);
        for (int i = 0; i < 100; i++) {
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message msg = handler.obtainMessage(3,i,-1);
            handler.sendMessage(msg);
        }

        handler.sendEmptyMessage(2);
    }
}

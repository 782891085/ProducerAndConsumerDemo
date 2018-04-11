package com.zjx.producerandconsumerdemo;

import android.util.Log;

import com.zjx.producerandconsumerdemo.Repertory;

public class Producer implements Runnable {
    private static final String TAG = "Producer";
    private Repertory mRepertory;

    Producer(Repertory repertory) {
        mRepertory = repertory;
    }

    @Override
    public void run() {
        Object product = new Object();
        mRepertory.produce(product);
    }
}

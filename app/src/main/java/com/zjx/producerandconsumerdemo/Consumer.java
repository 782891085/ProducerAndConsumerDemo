package com.zjx.producerandconsumerdemo;

import android.util.Log;

import com.zjx.producerandconsumerdemo.Repertory;

public class Consumer implements Runnable {
    private static final String TAG = "Consumer";
    private Repertory mRepertory;

    Consumer(Repertory repertory) {
        mRepertory = repertory;
    }

    @Override
    public void run() {
        mRepertory.consume();
    }
}

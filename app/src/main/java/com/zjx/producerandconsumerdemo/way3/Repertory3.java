package com.zjx.producerandconsumerdemo.way3;

import android.util.Log;

import com.zjx.producerandconsumerdemo.Repertory;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Repertory3 implements Repertory {
    private static final String TAG = "Repertory3";
    private static final int MAX_SIZE = 100;
    private BlockingDeque<Object> list = new LinkedBlockingDeque<>(MAX_SIZE);

    @Override
    public void produce(Object product) {
        try {
            list.put(product);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TAG, Thread.currentThread().getName() + "生产了: " + product);
    }

    @Override
    public void consume() {
        Object product = null;
        try {
            product = list.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TAG, Thread.currentThread().getName() + "消费了: " + product);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean isFull() {
        return size() == MAX_SIZE;
    }

    public int size() {
        return list.size();
    }


}

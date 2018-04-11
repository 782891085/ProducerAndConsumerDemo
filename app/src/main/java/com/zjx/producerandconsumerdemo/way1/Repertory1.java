package com.zjx.producerandconsumerdemo.way1;

import android.util.Log;

import com.zjx.producerandconsumerdemo.Repertory;

import java.util.LinkedList;

public class Repertory1 implements Repertory {
    private static final String TAG = "Repertory1";
    private static final int MAX_SIZE = 100;
    private LinkedList<Object> list = new LinkedList<>();

    @Override
    public synchronized void produce(Object product) {
        while (isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(product);
        Log.e(TAG, Thread.currentThread().getName() + "生产了: " + product + "，还剩余：" + size());
        notifyAll();
    }

    @Override
    public synchronized void consume() {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object product = list.remove();
        Log.e(TAG, Thread.currentThread().getName() + "消费了: " + product + "，还剩余：" + size());
        notifyAll();
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

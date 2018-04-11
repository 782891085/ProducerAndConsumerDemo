package com.zjx.producerandconsumerdemo.way2;

import android.util.Log;

import com.zjx.producerandconsumerdemo.Repertory;

import java.util.LinkedList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Repertory2 implements Repertory {
    private static final String TAG = "Repertory2";
    private static final int MAX_SIZE = 100;
    private LinkedList<Object> list = new LinkedList<>();

    private final Lock mLock = new ReentrantLock();
    private final Condition mEmptyCondition = mLock.newCondition();
    private final Condition mFullCondition = mLock.newCondition();

    @Override
    public void produce(Object product) {
        mLock.lock();
        try {
            while (isFull()) {
                try {
                    mFullCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(product);
            Log.e(TAG, Thread.currentThread().getName() + "生产了: " + product + "，还剩余：" + size());
            mEmptyCondition.signalAll();
        } finally {
            mLock.unlock();
        }
    }

    @Override
    public void consume() {
        mLock.lock();
        try {
            while (isEmpty()) {
                try {
                    mEmptyCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Object product = list.remove();
            Log.e(TAG, Thread.currentThread().getName() + "消费了: " + product + "，还剩余：" + size());
            mFullCondition.signalAll();
        } finally {
            mLock.unlock();
        }
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

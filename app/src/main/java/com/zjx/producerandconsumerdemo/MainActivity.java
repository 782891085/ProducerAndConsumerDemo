package com.zjx.producerandconsumerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zjx.producerandconsumerdemo.way1.Repertory1;
import com.zjx.producerandconsumerdemo.way2.Repertory2;
import com.zjx.producerandconsumerdemo.way3.Repertory3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void way1(View view) {
        Repertory repertory = new Repertory1();
        execute(repertory);
    }

    public void way2(View view) {
        Repertory repertory = new Repertory2();
        execute(repertory);
    }

    public void way3(View view) {
        Repertory repertory = new Repertory3();
        execute(repertory);
    }

    private void execute(Repertory repertory) {
        Producer producer1 = new Producer(repertory);
        Producer producer2 = new Producer(repertory);
        Producer producer3 = new Producer(repertory);
        Producer producer4 = new Producer(repertory);
        Producer producer5 = new Producer(repertory);
        Consumer consumer1 = new Consumer(repertory);
        Consumer consumer2 = new Consumer(repertory);
        Consumer consumer3 = new Consumer(repertory);
        Consumer consumer4 = new Consumer(repertory);
        Consumer consumer5 = new Consumer(repertory);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);
        service.execute(consumer4);
        service.execute(consumer5);
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(producer4);
        service.execute(producer5);
    }
}

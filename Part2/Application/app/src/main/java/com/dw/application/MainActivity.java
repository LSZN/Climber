package com.dw.application;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.io.StringBufferInputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //剩余时间输出
        final TextView tv = findViewById(R.id.timeOut);

        int[] var = TimeGet.getInstance().getTime();
        int hours = var[0];
        int minute = var[1];


        //已用时间输出
        CountDownTimer timer = new CountDownTimer((hours * 60 + minute) * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long sUntilFinished = millisUntilFinished / 1000;
                long mUntilFinished = sUntilFinished / 60;
                sUntilFinished = sUntilFinished % 60;
                long hUntilFinished = mUntilFinished / 60;
                mUntilFinished = mUntilFinished % 60;

                String[] timeToOut = TimePut.timeOutPut(hUntilFinished, mUntilFinished, sUntilFinished);
                tv.setText(timeToOut[0] + ":" + timeToOut[1] + ":" + timeToOut[2]);
            }

            @Override
            public void onFinish() {
                tv.setText("到达");
                TextView tv2 = findViewById(R.id.outPrompt);
                tv2.setText("");
            }
        }.start();
        final Chronometer usedChronometer = findViewById(R.id.lastTime);
        usedChronometer.setBase(SystemClock.elapsedRealtime() + 1);
        usedChronometer.start();
        Toast.makeText(MainActivity.this, "坚持！", Toast.LENGTH_LONG).show();


//有问题
//        Thread stopThread = new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        if(false) {
//                            Toast.makeText(MainActivity.this, "停止计时", Toast.LENGTH_LONG).show();
//                            usedChronometer.stop();
//                        }
//                    }
//                });
//        stopThread.start();
//        try {
//            stopThread.wait((hours * 60 + minute) * 60 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        Button bButton = findViewById(R.id.button2);
        bButton.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           ((Chronometer) findViewById(R.id.lastTime)).stop();

                                           Toast tot = Toast.makeText(
                                                   MainActivity.this,
                                                   "停止计时",
                                                   Toast.LENGTH_LONG);
                                           tot.show();
                                       }
                                   }
        );
    }

}
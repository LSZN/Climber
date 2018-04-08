package com.dw.application;

import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        final int hours = var[0];
        final int minute = var[1];


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
        usedChronometer.setBase(SystemClock.elapsedRealtime() - 1000);
        usedChronometer.start();
        Toast.makeText(MainActivity.this, "坚持！", Toast.LENGTH_LONG).show();


        //处理倒计时结束后Chronometer暂停。
        final Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        usedChronometer.stop();
                        Log.e("a", "true");
                        break;
                    case 2:
                        Log.e("abc", "pass");
                        break;
                }
            }

        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep((hours * 60 + minute) * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain(handler);
                message.what = 1;
                handler.handleMessage(message);
            }
        }).start();


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
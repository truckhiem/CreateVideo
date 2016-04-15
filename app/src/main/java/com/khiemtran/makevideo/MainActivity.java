package com.khiemtran.makevideo;

import android.graphics.Point;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private int stepStart = 0;
    private int stepStop = 1000;
    private int ScreenSize = 1000;
    private ScrollView mScollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScollView = (ScrollView) findViewById(R.id.scrollView);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        ScreenSize = size.y;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jump();
            }
        },3000);
    }

    private void jump(){
        autoScroll();
    }

    private void autoScroll(){
        new CountDownTimer(3000, 10) {
            int step = 10;
            int start = stepStart;

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                if(start < stepStop) {
                    mScollView.scrollTo(start, start += step);
                }else {
                    return;
                }
            }

            @Override
            public void onFinish() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stepStart += ScreenSize;
                        stepStop += ScreenSize;
                        jump();
                    }
                },3000);
                return;
            }
        }.start();
    }
}

package com.nearur.review;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        h.sendEmptyMessageDelayed(101,2000);
    }
    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==101){
                Intent i=new Intent(Splash.this,Review.class);
                startActivity(i);
                finish();
            }
        }
    };
}

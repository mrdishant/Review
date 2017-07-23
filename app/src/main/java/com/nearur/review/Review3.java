package com.nearur.review;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Review3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review3);
    }
    public void cl(View v){
        Intent i=new Intent(this,Splash.class);
        startActivity(i);
        finish();
    }
}

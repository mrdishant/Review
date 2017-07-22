 package com.nearur.review;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Review2 extends AppCompatActivity implements View.OnClickListener {

    ContentResolver resolver;

    String name="";
    String clas="";
    String roll="";
    String sub="";
    String tec="";
    ArrayAdapter<String > adapter;

    Data d=new Data();

    @InjectView(R.id.radioButton1)
    RadioButton c1;
    @InjectView(R.id.radioButton2)
    RadioButton c2;
    @InjectView(R.id.radioButton3)
    RadioButton v1;
    @InjectView(R.id.radioButton4)
    RadioButton v2;
    @InjectView(R.id.radioButton5)
    RadioButton a1;
    @InjectView(R.id.radioButton6)
    RadioButton a2;

    @InjectView(R.id.spinner)
    Spinner pace;

    @InjectView(R.id.button2)
    Button finish;

    @InjectView(R.id.editText)
    EditText query;

    @InjectView(R.id.ratingBar)
    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review2);

        ButterKnife.inject(this);
        resolver=getContentResolver();
        Intent rcv=getIntent();
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);
        name=rcv.getStringExtra("name");
        clas=rcv.getStringExtra("class");
        roll=rcv.getStringExtra("roll");
        sub=rcv.getStringExtra("subject");
        tec=rcv.getStringExtra("teacher");
        finish.setOnClickListener(this);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        v1.setOnClickListener(this);
        v2.setOnClickListener(this);
        a1.setOnClickListener(this);
        a2.setOnClickListener(this);
        adapter.add("--Select Pace--");
        adapter.add("Fine");

        adapter.add("Fast");
        adapter.add("Very Fast");

        pace.setAdapter(adapter);


    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch (id){
            case R.id.button2:
               if((c1.isChecked()||c2.isChecked())&&(v1.isChecked()||v2.isChecked())&&(a1.isChecked()||a2.isChecked())&&pace.getSelectedItemPosition()!=0){
                d.name=name;
                d.clas=clas;
                d.roll=roll;
                d.sub=sub;
                d.teacher=tec;
                d.pace=(String) pace.getSelectedItem();
                d.query=query.getText().toString().trim();
                d.ratings=String.valueOf( ratingBar.getRating());

                insert();
                Intent i=new Intent(this,Review3.class);
                startActivity(i);
                finish();}
                else{
                   Toast.makeText(this,"Please Fill all details",Toast.LENGTH_LONG).show();
               }

                break;
            case R.id.radioButton1:
                d.clear="Yes";
                break;


            case R.id.radioButton2:
                d.clear="No";
                break;


            case R.id.radioButton3:
                d.visible="Yes";
                break;


            case R.id.radioButton4:
                d.visible="No";
                break;



            case R.id.radioButton5:
                d.audio="Yes";
                break;


            case R.id.radioButton6:
                d.audio="No";
                break;

        }
    }
    private void insert(){
        ContentValues values=new ContentValues();

        values.put(Util.student,d.name);
        values.put(Util.clas,d.clas);
        values.put(Util.roll,d.roll);
        values.put(Util.sub,d.sub);
        values.put(Util.tec,d.teacher);
        values.put(Util.clear,d.clear);
        values.put(Util.visible,d.visible);
        values.put(Util.audio,d.audio);
        values.put(Util.pace,d.pace);
        values.put(Util.query,d.query);
        values.put(Util.rating,d.ratings);

        Uri x=resolver.insert(Util.u,values);
        Toast.makeText(this,"Record Inserted "+x.getLastPathSegment(),Toast.LENGTH_LONG).show();
    }
}

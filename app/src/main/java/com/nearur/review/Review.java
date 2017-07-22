package com.nearur.review;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Review extends AppCompatActivity {

    @InjectView(R.id.editTextName)
    EditText ename;

    @InjectView(R.id.editTextClass)
    EditText eclass;

    @InjectView(R.id.editTextRoll)
    EditText eroll;

    @InjectView(R.id.spinnerSubject)
    Spinner sSubject;

    @InjectView(R.id.spinnerTeacher)
    Spinner sTeacher;

    ArrayAdapter<String > adapter1,adapter2;

    @InjectView(R.id.button1)
    Button next1;
    ContentResolver resolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.inject(this);
        resolver=getContentResolver();

        boolean mode=getIntent().hasExtra("data");

        if(mode){
            final Data d=(Data)getIntent().getSerializableExtra("data");
           ename.setText(d.name);
            eclass.setText(d.clas);
            eroll.setText(d.roll);
            next1.setText("Update");

            next1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String w=Util.roll+" = "+d.roll;
                    ContentValues values=new ContentValues();

                    d.name=ename.getText().toString().trim();
                    d.roll=eroll.getText().toString().trim();
                    d.clas=eclass.getText().toString().trim();
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

                    resolver.update(Util.u,values,w,null);
                    Intent data=new Intent();
                    data.putExtra("data",d);
                    setResult(-101,data);
                    finish();

                }
            });
        }else {


            next1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Review.this, Review2.class);
                    if (ename.getText().toString().trim().length() == 0 || eclass.getText().toString().trim().length() == 0 || eroll.getText().toString().trim().length() == 0 || sSubject.getSelectedItemPosition() == 0 || sTeacher.getSelectedItemPosition() == 0) {
                        Toast.makeText(Review.this, "Please Enter Details", Toast.LENGTH_LONG).show();
                    } else {
                        i.putExtra("name", ename.getText().toString().trim());
                        i.putExtra("class", eclass.getText().toString().trim());
                        i.putExtra("roll", eroll.getText().toString().trim());
                        i.putExtra("subject", (String) sSubject.getSelectedItem());
                        i.putExtra("teacher", (String) sTeacher.getSelectedItem());
                        startActivity(i);
                        finish();
                    }
                }
            });

            adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);

            adapter1.add("--Select Subject--");

            adapter1.add("OOPS");
            adapter1.add("Java");
            adapter1.add("DSA");
            adapter1.add("DCLD");
            adapter1.add("CN");

            sSubject.setAdapter(adapter1);


            adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);

            adapter2.add("--Select Teacher--");

            adapter2.add("Kamal Deep Kaur");
            adapter2.add("Priyanka Arora");
            adapter2.add("Mandeep Kaur");
            adapter2.add("Vivek Thapar");
            adapter2.add("Blossom");

            sTeacher.setAdapter(adapter2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.review:
                Intent i=new Intent(this,AllReviews.class);
                startActivity(i);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

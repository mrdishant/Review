package com.nearur.review;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AllReviews extends AppCompatActivity {
    ListView listView;
    ArrayList<Data> a;
    MyAdapter adapter;
    Data data;
    int pos;
    ContentResolver resolver;
    EditText edt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reviews);
        listView=(ListView)findViewById(R.id.listview1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               pos=i;
                data=a.get(i);
                selected();
            }
        });
        edt=(EditText)findViewById(R.id.edit);

        a=new ArrayList<>();
        get();

    }

    void get(){

        resolver=getContentResolver();

        String[] p={"Name","Class","RollNumber","Subject","Teacher","Clear","Visible","Audio","Pace","Query","Rating"};
        Cursor c=resolver.query(Util.u,p,null,null,null);

        if(c!=null){
            while (c.moveToNext()){
                a.add(new Data(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8),c.getString(9),c.getString(10)));
            }
            class compare implements Comparator<Data>{

                @Override
                public int compare(Data data, Data t1) {
                    return t1.roll.compareTo(data.roll);
                }
            }

            Collections.sort(a, new compare());
            adapter=new MyAdapter(this,R.layout.item,a);
            listView.setAdapter(adapter);
            edt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        adapter.filter(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

    }



    void selected(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Select What to Do");
        String[] i={"View","Delete","Update"};
        builder.setItems(i, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               switch (i){

                   case 0:
                        viewreview();
                       break;
                   case 1:
                       delete();
                       break;

                   case 2:
                        update();
                       break;
               }
            }
        }).create().show();


    }
    void viewreview(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Review");
        builder.setMessage(data.toString());
        builder.setNeutralButton("Done",null);
        builder.create().show();
    }

    void delete(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete "+data.name);
        builder.setMessage("Are You Sure?");
        builder.setNegativeButton("Cancel",null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String w=Util.roll+" = "+data.roll;
                int c=resolver.delete(Util.u,w,null);
                if(c>0){
                    Toast.makeText(AllReviews.this,data.name+" deleted",Toast.LENGTH_LONG).show();
                    a.remove(pos);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(AllReviews.this,data.name+" not deleted",Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.create().show();
    }

    void update(){
        Intent intent=new Intent(this,Review.class);
        intent.putExtra("data",data);
        startActivityForResult(intent,101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==101&&resultCode==-101){
            a.remove(pos);
            a.add(pos,(Data)data.getSerializableExtra("data"));
            adapter.notifyDataSetChanged();
        }
    }
}

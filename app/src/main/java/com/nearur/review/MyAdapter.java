package com.nearur.review;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrdis on 7/21/2017.
 */

public class MyAdapter extends ArrayAdapter<Data> {

    Context context; int resource;ArrayList<Data> objects,temp;
    TextView t1,t2;
    String ch=null;

    public MyAdapter(Context context, int resource, ArrayList<Data> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
        temp=new ArrayList<>();
        temp.addAll(objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= LayoutInflater.from(context).inflate(resource,parent,false);

        t1=(TextView)v.findViewById(R.id.textViewname);
        t2=(TextView)v.findViewById(R.id.textViewclass);

        Data d=objects.get(position);
        set(d,ch);

        return v;
    }

    void filter(String s){

        objects.clear();

        if(s.length()==0){
            objects.addAll(temp);
            ch=null;
        }else {
            ch=s;
            for (Data d : temp) {
                if (d.name.toLowerCase().contains(s.toLowerCase())) {
                    objects.add(d);
                }
            }
        }
        notifyDataSetChanged();
    }

    void set(Data d,String s){


        t1.setText(d.roll+" - "+d.name);
        t2.setText(d.clas);

        if(s!=null) {

            SpannableString string = new SpannableString(t1.getText().toString());
            string.setSpan(new ForegroundColorSpan(Color.GREEN), t1.getText().toString().toLowerCase().indexOf(s.toLowerCase()),t1.getText().toString().toLowerCase().indexOf(s.toLowerCase())+s.length(), SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
            t1.setText(string);
        }
    }
}

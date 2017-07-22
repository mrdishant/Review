package com.nearur.review;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrdis on 7/21/2017.
 */

public class MyAdapter extends ArrayAdapter<Data> {

    Context context; int resource;ArrayList<Data> objects;

    public MyAdapter(Context context, int resource, ArrayList<Data> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= LayoutInflater.from(context).inflate(resource,parent,false);

        TextView t1=(TextView)v.findViewById(R.id.textViewname);
        TextView t2=(TextView)v.findViewById(R.id.textViewclass);

        Data d=objects.get(position);

        t1.setText(d.roll+" - "+d.name);
        t2.setText(d.clas);

        return v;
    }
}

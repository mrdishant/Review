package com.nearur.review;

import java.io.Serializable;

/**
 * Created by mrdis on 7/20/2017.
 */

public class Data implements Serializable{

    String name,clas,roll,sub,teacher,clear,visible,audio,pace,query,ratings;

    public Data(String name, String clas, String roll, String sub, String teacher, String clear, String visible, String audio, String pace, String query, String ratings) {
        this.name = name;
        this.clas = clas;
        this.roll = roll;
        this.sub = sub;
        this.teacher = teacher;
        this.clear = clear;
        this.visible = visible;
        this.audio = audio;
        this.pace = pace;
        this.query = query;
        this.ratings = ratings;
    }

    Data(){

    }

    @Override
    public String toString() {
        return
                "\n\nName : " + name +
                "\n\nClass : " + clas +
                "\n\n Roll : " + roll +
                "\n\n Subject : " + sub +
                "\n\n Teacher : " + teacher +
                "\n\n Clear : " + clear +
                "\n\n Visible : " + visible +
                "\n\n Audio : " + audio +
                "\n\n Pace : " + pace +
                "\n\n Query : " + query +
                "\n\n Ratings : " + ratings  ;
    }
}

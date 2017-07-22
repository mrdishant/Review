package com.nearur.review;

import android.net.Uri;

/**
 * Created by mrdis on 7/20/2017.
 */

public class Util {

    public static final int version=1;
    public static final String dbname="Gndec";
    public static final String tabname="Cse";

    public static final String student="Name";
    public static final String clas="Class";
    public static final String roll="RollNumber";
    public static final String sub="Subject";
    public static final String tec="Teacher";
    public static final String clear="Clear";
    public static final String visible="Visible";
    public static final String audio="Audio";
    public static final String pace="Pace";
    public static final String query="Query";
    public static final String rating="Rating";


    public static final String create= "create table Cse(" +
            "Name text," +
            "Class text," +
            "RollNumber text primary key," +
            "Subject text," +
            "Teacher text," +
            "Clear text," +
            "Visible text," +
            "Audio text," +
            "Pace text," +
            "Query text," +
            "Rating text" +
            ")";

    public static final Uri u=Uri.parse("content://com.nearur.Review.cse/"+tabname);

}

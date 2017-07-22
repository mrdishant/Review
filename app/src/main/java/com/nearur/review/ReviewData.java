package com.nearur.review;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class ReviewData extends ContentProvider {

    Db helper;
    SQLiteDatabase f;
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
          return   f.delete(uri.getLastPathSegment(),selection,selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String table=uri.getLastPathSegment();
        long id=f.insert(table,null,values);
        return Uri.parse("dummy/"+id);
    }

    @Override
    public boolean onCreate() {
       helper=new Db(getContext(),Util.dbname,null,Util.version);
        f=helper.getWritableDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
    return f.query(uri.getLastPathSegment(),projection,selection,selectionArgs,null,null,sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return f.update(uri.getLastPathSegment(),values,selection,selectionArgs);

    }

    class Db extends SQLiteOpenHelper{

        public Db(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(Util.create);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}

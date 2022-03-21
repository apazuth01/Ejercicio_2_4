package com.example.activity_firma_digital.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ConexionSQLite {
    List<Firmas> list = new ArrayList<>();
    SQLiteDatabase db;
    Firmas firmas=new Firmas();
    DataBaseHelper helper;

    public ConexionSQLite(Context context) {
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public List<Firmas> getData(){
        String columns[] = {"_id", "descripcion", "imagen"};
        Cursor cursor = db.query("firmadigital", columns,null, null,null, null, null, null);
        while(cursor.moveToNext()){

            int id=cursor.getColumnIndex("_id");

            int descrip= cursor.getColumnIndex("descripcion");
            String descripcion=cursor.getString(descrip);

            int img = cursor.getColumnIndex("imagen");
            byte [] imagen=cursor.getBlob(img);

            Firmas firmas = new Firmas(cursor.getInt(id),descripcion, imagen);
            list.add(firmas);
        }
        return list;
    }

    public boolean insertSQL(String descripcion,byte[] imagen){
        Log.i("mensaje", "insertSQL: "+descripcion);

        if (db != null) {
            ContentValues cv = new ContentValues();
            cv.put("descripcion",descripcion);
            cv.put("imagen",imagen);
            db.insert("firmadigital", null, cv);
            return true;
        }else{
            return false;
        }

    }

    private static class DataBaseHelper extends SQLiteOpenHelper {
        private static final String CREATE_TABLE = "CREATE TABLE firmadigital(_id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT, imagen Blob)";
        private static final String database_name = "firmasqlite.sqlite";
        private static final String DROP_DATABASE="DROP TABLE IF EXISTS firmadigital";
        private static final int version_database = 1;


        public DataBaseHelper(Context context) {
            super(context, database_name, null, version_database);
        }




        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            //sqLiteDatabase.execSQL(DROP_DATABASE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}

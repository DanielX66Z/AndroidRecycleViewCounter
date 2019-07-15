package com.sv.pruebalista.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sv.pruebalista.entidad.Producto;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static int database_version = 1;
    private static String database_name = "fire";
    private static String table_name = "ejemplo";
    private static String coloumnId = "codigo";
    private static String coloumnEstilo = "estilo";
    private static String coloumnCantidad = "cantidad";
    private static String coloumnPrecio = "precio";

    //constructor
    public DatabaseHelper(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + table_name + "(" + coloumnId + " INTEGER PRIMARY KEY ,"
                + coloumnEstilo + " TEXT ," +coloumnCantidad + " INTEGER,"
                + coloumnPrecio + " DOUBLE"
                + ")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(sqLiteDatabase);
    }


    public long insertData(Producto producto){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(coloumnId,producto.getCodigo() );
        cv.put(coloumnEstilo,producto.getEstilo() );
        cv.put(coloumnCantidad,producto.getCantidad() );
        cv.put(coloumnPrecio,producto.getPrecio() );
        long id = db.insert(table_name,null, cv);
        db.close();
        return id;
    }

    public int updateData(Producto producto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(coloumnId,producto.getCodigo() );
        cv.put(coloumnEstilo,producto.getEstilo() );
        cv.put(coloumnCantidad,producto.getCantidad() );
        cv.put(coloumnPrecio,producto.getPrecio() );

        // updating row
        return db.update(table_name, cv, coloumnId+" = ?",
                new String[]{String.valueOf(producto.getCodigo())});
    }
    public Producto getData(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(table_name,
                new String[]{coloumnId, coloumnEstilo, coloumnCantidad,coloumnPrecio},
                coloumnId + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Producto data = new Producto(cursor.getString(cursor.getColumnIndex(coloumnEstilo)),
                cursor.getInt(cursor.getColumnIndex(coloumnCantidad)),
                cursor.getDouble(cursor.getColumnIndex(coloumnPrecio)),
                cursor.getInt(cursor.getColumnIndex(coloumnId)));

        // close the db connection
        cursor.close();

        return data;
    }

    public List<Producto> getAllDatas() {
        List<Producto> Datas = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + table_name ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Producto data = new Producto();
                data.setCodigo(cursor.getInt(cursor.getColumnIndex(coloumnId)));
                data.setEstilo(cursor.getString(cursor.getColumnIndex(coloumnEstilo)));
                data.setCantidad(cursor.getInt(cursor.getColumnIndex(coloumnCantidad)));
                data.setPrecio(cursor.getDouble(cursor.getColumnIndex(coloumnPrecio)));
                Datas.add(data);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();
        // return notes list
        return Datas;
    }

    public int countItems(){
        int resultado=0;
        String selectQuery = "SELECT  sum(cantidad*precio) FROM " + table_name ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            resultado=cursor.getInt(0);
        }
        cursor.close();
        return resultado;
    }


    public int getDataCount() {
        String countQuery = "SELECT  * FROM " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public void deleteData(Producto data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name, coloumnId + " = ?",
                new String[]{String.valueOf(data.getCodigo())});
        db.close();
    }
}

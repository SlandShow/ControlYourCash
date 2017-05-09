package com.test_apps.slandshow.controlyourcash.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 02.05.2017.
 */

public class CashDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "productDB.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_INCOME = "income";
    public static final String COLUMN_COAST = "coast";
    private static final String l = new String("1234");


    public CashDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_products_table = "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PRODUCTNAME + " TEXT,"
                + COLUMN_COAST + " INTEGER, " + COLUMN_INCOME + " INTEGER)";
        db.execSQL(create_products_table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCTS, new String[]{COLUMN_ID, COLUMN_PRODUCTNAME,
                COLUMN_COAST, COLUMN_INCOME}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor;
        } else {
            return null;
        }
    }

    public void addProduct(Cash product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getName());
        values.put(COLUMN_COAST, product.getCoast());
        values.put(COLUMN_INCOME, product.getIncome());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public Cash findProduct(String productname) {
        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME +
                " = \"" + productname + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Cash p = new Cash();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            p.setId(Integer.parseInt(cursor.getString(0)));
            p.setName(cursor.getString(1));
            p.setIncome(Integer.parseInt(cursor.getString(2)));
            p.setCoast(Integer.parseInt(cursor.getString(3)));
            cursor.close();
        } else {
            p = null;
        }
        db.close();
        return p;
    }

    public boolean deleteProduct(String productname) {
        boolean result = false;
        String q = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME
                + " = \"" + productname + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(q, null);
        Cash p = new Cash();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            p.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(p.getId())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateProduct(Cash p) {
        boolean result = false;
        String q = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + " = " + p.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(q, null);
        if (c.moveToFirst()) {
            String q2 = "UPDATE " + TABLE_PRODUCTS + " SET " + COLUMN_PRODUCTNAME + " = \""
                    + p.getName() + "\", " + COLUMN_COAST + " = " + p.getCoast() +
                    "\", " + COLUMN_INCOME + " = " + p.getIncome()
                    + " WHERE " + COLUMN_ID + " = " + p.getId();
            db.execSQL(q2);
            result = true;
        }
        db.close();
        return result;
    }


    public void deleteAllProducts() {
        String q = "DELETE FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(q);
        db.close();
    }

    public HashMap<Long, String> getHashMap(String exec) {
        HashMap<Long, String> map = new HashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCTS, new String[]{COLUMN_ID, COLUMN_PRODUCTNAME,
                COLUMN_COAST, COLUMN_INCOME}, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                long value = cursor.getLong(cursor.getColumnIndexOrThrow(exec));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCTNAME));
                map.put(value, name);
            } while (cursor.moveToNext());
        }
        return map;
    }


}

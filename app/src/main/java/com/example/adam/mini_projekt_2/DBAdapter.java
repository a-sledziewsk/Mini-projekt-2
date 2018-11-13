package com.example.adam.mini_projekt_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

    private static final int DATABASE_VERSION = 1;
    private static final String MY_DATABASE_NAME = "My database";
    private static final String TABLE_NAME = "items_tables";
    private static final String PRODUCT_COLUMN = "Product_name";
    private static final String PRICE_COLUMN = "Price";
    private static final String QUANTITY_COLUMN = "Quantity";
    private static final String BOUGHT_COLUMN = "Bought";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + PRODUCT_COLUMN + " TEXT, " + QUANTITY_COLUMN + " INT, " +
                    PRICE_COLUMN + " REAL, " +
                    BOUGHT_COLUMN + " INT);";

    private static final  String[] ALL_KEYS = new String[]{PRODUCT_COLUMN,
            QUANTITY_COLUMN, PRICE_COLUMN, BOUGHT_COLUMN};


    private final Context context;
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;


    public DBAdapter(Context ctx) {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }

    //Open the db connection
    public DBAdapter open(){
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    //Close the db connection
    public void close(){
        myDBHelper.close();
    }

    // Add a bew set of values to be inserted into db
    public long insertRow(String product_name, int quantity, float price, boolean bought){
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRICE_COLUMN, price);
        contentValues.put(PRODUCT_COLUMN, product_name);
        contentValues.put(QUANTITY_COLUMN, quantity);

        if (!bought)
            contentValues.put(BOUGHT_COLUMN, 0);
        else
            contentValues.put(BOUGHT_COLUMN, 1);

        return db.insert(TABLE_NAME,null, contentValues);
    }

    public boolean deleteRow(String prod_name){
        String where =  DBAdapter.PRODUCT_COLUMN + "=" + "'" + prod_name + "'";
        return db.delete(TABLE_NAME, where, null) != 0;
    }



    // Return all date in db
    public Cursor getAllRows() {
        Cursor c = db.query(false,TABLE_NAME, ALL_KEYS, null, null, null, null,
                null, null, null);
        if (c!=null){
            c.moveToFirst();
        }
        return c;
    }

    // Get row
    public Cursor getRow(String prod_name){
        String where =  DBAdapter.PRODUCT_COLUMN + "'" + prod_name + "'";
        Cursor c = db.query(false, TABLE_NAME, ALL_KEYS, where, null, null,
                null, null, null);

        if (c!=null){
            c.moveToFirst();
        }

        return c;
    }

    public static String getMyDatabaseName() {
        return MY_DATABASE_NAME;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getProductColumn() {
        return PRODUCT_COLUMN;
    }

    public static String getPriceColumn() {
        return PRICE_COLUMN;
    }

    public static String getQuantityColumn() {
        return QUANTITY_COLUMN;
    }

    public static String getBoughtColumn() {
        return BOUGHT_COLUMN;
    }

    // Change existing row
    public boolean updateRow(String prod_name, String product_name, int quantity, float price, boolean bought) {
        String where =  DBAdapter.PRODUCT_COLUMN + "=" + "'" + prod_name + "'";
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_COLUMN, product_name);
        contentValues.put(QUANTITY_COLUMN, quantity);
        contentValues.put(PRICE_COLUMN, price);

        if (!bought)
            contentValues.put(BOUGHT_COLUMN, 0);
        else
            contentValues.put(BOUGHT_COLUMN, 1);

        return db.update(TABLE_NAME, contentValues, where, null) != 0;
    }

    public void changeCheckbox(String prod_name, boolean statusCheckbox){
        int boolIntCheckbox = 0;
        if (statusCheckbox)
            boolIntCheckbox=1;

        String query = "UPDATE " + DBAdapter.TABLE_NAME + " SET " + DBAdapter.getBoughtColumn() +
                "=" + Integer.toString(boolIntCheckbox) + " WHERE " + DBAdapter.getProductColumn() +
                "="+ "'" + prod_name + "'"  ;
        db.execSQL(query);
    }



    private static  class DatabaseHelper extends SQLiteOpenHelper{

        DatabaseHelper(Context context){
            super(context, MY_DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

            // Recreate new database:
            onCreate(_db);
        }
    }
}
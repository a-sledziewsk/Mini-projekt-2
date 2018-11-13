package com.example.adam.mini_projekt_2;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AddActivity extends Activity {

    private Button add_to_list_button;
    private EditText add_product_name;
    private EditText add_quantity;
    private EditText add_price;
    private CheckBox bought;

    private TextView productNameTextView;
    private TextView quantityTextView;
    private TextView priceTextView;
    DBAdapter myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        add_product_name = (EditText) findViewById(R.id.add_product_name_input);
        add_quantity = (EditText) findViewById(R.id.add_quantity_input);
        add_price = (EditText) findViewById(R.id.add_price_input);
        bought = (CheckBox)findViewById((R.id.add_product_checkbox));
        add_to_list_button = findViewById(R.id.add_to_list_button);


        productNameTextView = findViewById(R.id.add_product_name_label);
        quantityTextView = findViewById(R.id.add_quantity_label);
        priceTextView = findViewById(R.id.add_price_label);

        openDB();
    }

    private void openDB(){
        myDB = new DBAdapter(this);
        myDB.open();
    }

    public void addProductDetailsClick(View view){
        String productName = add_product_name.getText().toString();
        String quantity = add_quantity.getText().toString();
        String price = add_price.getText().toString();
        boolean boughtBoolean = bought.isChecked();

        if (productName.length()>0 &&  price.length()>0&& quantity.length()>0) {
            myDB.insertRow(productName, Integer.parseInt(quantity), Float.parseFloat(price), boughtBoolean);
            Intent returnToListActivity = new Intent(this, ListActivity.class);

            NotificationHelper notify = new NotificationHelper(this);
            notify.createNotification("New product added!","Tttut");

            startActivity(returnToListActivity);


        }
        else{
            Toast.makeText(this, "Fill all text fields!",
                    Toast.LENGTH_LONG).show();
        }

    }


}

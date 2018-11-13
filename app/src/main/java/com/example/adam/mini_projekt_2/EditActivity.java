package com.example.adam.mini_projekt_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends Activity {

    private Button add_to_list_button;
    private EditText add_product_name;
    private EditText add_quantity;
    private EditText add_price;
    private CheckBox bought;

    private TextView productNameTextView;
    private TextView quantityTextView;
    private TextView priceTextView;

    DBAdapter myDB;
    Intent intentDelivery = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        add_product_name = (EditText) findViewById(R.id.edit_product_name_input);
        add_quantity = (EditText) findViewById(R.id.edit_quantity_input);
        add_price = (EditText) findViewById(R.id.edit_price_input);
        bought = (CheckBox)findViewById((R.id.edit_product_checkbox));
        add_to_list_button = findViewById(R.id.edit_to_list_button);

        this.intentDelivery = getIntent();

        productNameTextView = findViewById(R.id.edit_product_name_label);
        quantityTextView = findViewById(R.id.edit_quantity_label);
        priceTextView = findViewById(R.id.edit_price_label);

        openDB();
    }


    private void openDB(){
        myDB = new DBAdapter(this);
        myDB.open();
    }

    public void editProductDetailsClick(View view){
        String productName = add_product_name.getText().toString();
        String quantity = add_quantity.getText().toString();
        String price = add_price.getText().toString();
        boolean boughtBoolean = bought.isChecked();
        String prod_name_id = this.intentDelivery.getStringExtra("key");

        if (productName.length()>0 && price.length()>0&& quantity.length()>0) {
            myDB.updateRow(prod_name_id, productName, Integer.parseInt(quantity), Float.parseFloat(price), boughtBoolean);
            Intent returnToListActivity = new Intent(this, ListActivity.class);
            startActivity(returnToListActivity);
        }
        else{
            Toast.makeText(this, "Fill all text fields!",
                    Toast.LENGTH_LONG).show();
        }
    }
}
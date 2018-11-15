package com.example.adam.mini_projekt_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String productName = intent.getStringExtra("Product name");
        String quantity = intent.getStringExtra("Quantity");
        String price = intent.getStringExtra("Price");

        String msg = "Product name: " + productName + "\tQuantity: " + quantity +"\tPrice: " + price
                +"$";

        NotificationHelper notify = new NotificationHelper(context);
        notify.createNotification("New product added!",msg);
    }
}

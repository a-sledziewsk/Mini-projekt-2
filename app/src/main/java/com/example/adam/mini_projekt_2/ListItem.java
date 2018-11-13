package com.example.adam.mini_projekt_2;

public class ListItem {
    private String productName;
    private float price;
    private int quantity;
    private boolean checked;

    ListItem(String productName, float price, int quantity, boolean checked){
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.checked = checked;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
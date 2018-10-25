package com.example.android.exercise_recipe_one;

public class Product {
    String name;
    int quantity;
    boolean isSelected;


    Product(String product_name, int quantity_product, boolean _box) {
        name = product_name;
        quantity = quantity_product;
        isSelected = _box;
    }
}


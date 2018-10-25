package com.example.android.exercise_recipe_one;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BoxAdapter extends BaseAdapter {

    Context context;
    LayoutInflater lInflater; // это класс, который умеет из содержимого layout-файла создать view-элемент (методом inflate)
    ArrayList<Product> allIngredientList;

    BoxAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        allIngredientList = products; //список товаров
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return allIngredientList.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return allIngredientList.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View item, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = item;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        Product product = getProductByPosition(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.text_view_ingredient_name)).setText(product.name);
        ((TextView) view.findViewById(R.id.text_view_ingredient_quantity)).setText(product.quantity + "");

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cbBox);
        // присваиваем чекбоксу обработчик
        checkBox.setOnCheckedChangeListener(myCheckChangeList);
        // пишем позицию
        checkBox.setTag(position);
        // заполняем данными из товаров: в корзине или нет
        checkBox.setChecked(product.isSelected);
        return view;
    }

    // товар по позиции
    Product getProductByPosition(int position) {
        return ((Product) getItem(position));
    }

    // содержимое корзины
    ArrayList<Product> getSelectedProducts() {
        ArrayList<Product> listOfSelectedProducts = new ArrayList<Product>();
        for (Product p : allIngredientList) {
            // если в корзине
            if (p.isSelected)
                listOfSelectedProducts.add(p);
        }
        return listOfSelectedProducts;
    }

    // обработчик для чекбоксов
    OnCheckedChangeListener myCheckChangeList = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // меняем данные товара (в корзине или нет)
            getProductByPosition((Integer) buttonView.getTag()).isSelected = isChecked;
        }
    };

}

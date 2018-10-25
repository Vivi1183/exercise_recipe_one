package com.example.android.exercise_recipe_one;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList<Product>();
    BoxAdapter boxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // создаем адаптер
        fillData();
        boxAdapter = new BoxAdapter(this, products);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);//получаем ссылку на список
        lvMain.setAdapter(boxAdapter);
    }

    // генерируем данные для адаптера
    void fillData() {
        //заполняем массив тестовыми данными
//        for (int i = 1; i <= 30; i++) {
//            products.add(new Product("Product " + i, i * 1000, false));
//        }

        products.add(new Product("мука, гр.", 400, false));
        products.add(new Product("разрыхлитель, ч.л.", 1, false));
        products.add(new Product("яйца, шт", 3, false));
        products.add(new Product("корица, 1/3 ч.л.", 1, false));
        products.add(new Product("сахар, гр", 170, false));
        products.add(new Product("ванильный сахар / ван.эссенция, ч.л.", 1, false));
        products.add(new Product("сушеная клюква, горсть", 1, false));
        products.add(new Product("фундук, горсть", 1, false));
    }

    // выводим информацию о корзине
    public void showResult(View v) {
        String result = "Товары в корзине:";
        for (Product p : boxAdapter.getSelectedProducts()) {
            if (p.isSelected)
                result += "\n" + p.name;
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}

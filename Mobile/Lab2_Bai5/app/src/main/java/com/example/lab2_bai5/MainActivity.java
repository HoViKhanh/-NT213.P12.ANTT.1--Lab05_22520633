package com.example.lab2_bai5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etDishName;
    private Spinner spinnerThumbnail;
    private CheckBox cbPromotion;
    private Button btnAddDish;
    private GridView gvDishes;

    private List<Dish> dishes;
    private DishAdapter dishAdapter;

    private int[] thumbnails = {R.drawable.thumbnail1, R.drawable.thumbnail2, R.drawable.thumbnail3};
    private String[] thumbnailNames = {"Thumbnail 1", "Thumbnail 2", "Thumbnail 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDishName = findViewById(R.id.et_dish_name);
        spinnerThumbnail = findViewById(R.id.spinner_thumbnail);
        cbPromotion = findViewById(R.id.cb_promotion);
        btnAddDish = findViewById(R.id.btn_add_dish);
        gvDishes = findViewById(R.id.gv_dishes);

        dishes = new ArrayList<>();
        dishAdapter = new DishAdapter(this, dishes);
        gvDishes.setAdapter(dishAdapter);

        // Set Spinner Adapter
        ThumbnailAdapter thumbnailAdapter = new ThumbnailAdapter(this, thumbnails, thumbnailNames);
        spinnerThumbnail.setAdapter(thumbnailAdapter);

        // Add Dish
        btnAddDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etDishName.getText().toString();
                int thumbnail = thumbnails[spinnerThumbnail.getSelectedItemPosition()];
                boolean hasPromotion = cbPromotion.isChecked();

                dishes.add(new Dish(name, thumbnail, hasPromotion));
                dishAdapter.notifyDataSetChanged();

                // Reset các trường nhập
                etDishName.setText("");
                cbPromotion.setChecked(false);
            }
        });
    }
}


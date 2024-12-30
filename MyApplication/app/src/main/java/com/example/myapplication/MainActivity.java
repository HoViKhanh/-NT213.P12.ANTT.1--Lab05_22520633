package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llNameContainer, llAddressContainer, llParentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createNameContainer();
        createAddressContainer();
        createParentContainer();

        // Gán LinearLayout cha làm giao diện chính
        setContentView(llParentContainer);
    }

    private void createNameContainer() {
        llNameContainer = new LinearLayout(this);
        llNameContainer.setOrientation(LinearLayout.HORIZONTAL);
        llNameContainer.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        llNameContainer.setBackgroundColor(Color.RED); // Thêm màu nền kiểm tra

        TextView tvName = new TextView(this);
        tvName.setText("Name: ");
        llNameContainer.addView(tvName);

        TextView tvNameValue = new TextView(this);
        tvNameValue.setText("John Doe");
        llNameContainer.addView(tvNameValue);
    }

    private void createAddressContainer() {
        llAddressContainer = new LinearLayout(this);
        llAddressContainer.setOrientation(LinearLayout.HORIZONTAL);
        llAddressContainer.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        llAddressContainer.setBackgroundColor(Color.BLUE); // Thêm màu nền kiểm tra

        TextView tvAddress = new TextView(this);
        tvAddress.setText("Address: ");
        llAddressContainer.addView(tvAddress);

        TextView tvAddressValue = new TextView(this);
        tvAddressValue.setText("911 Hollywood Blvd");
        llAddressContainer.addView(tvAddressValue);
    }

    private void createParentContainer() {
        llParentContainer = new LinearLayout(this);
        llParentContainer.setOrientation(LinearLayout.VERTICAL);
        llParentContainer.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        llParentContainer.setBackgroundColor(Color.LTGRAY); // Thêm màu nền kiểm tra

        llParentContainer.addView(llNameContainer);
        llParentContainer.addView(llAddressContainer);
    }
}

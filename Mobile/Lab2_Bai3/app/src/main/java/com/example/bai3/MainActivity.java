package com.example.bai3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Khai báo các thành phần giao diện
    private EditText editTextMaNV, editTextTenNV;
    private RadioButton radioFullTime, radioPartTime;
    private Button btnSubmit;
    private ListView listView;
    private TextView textViewInfo;

    private ArrayList<Employee> employeeList;
    private ArrayAdapter<Employee> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Khởi tạo các thành phần giao diện
        editTextMaNV = findViewById(R.id.editTextMaNV);
        editTextTenNV = findViewById(R.id.editTextTenNV);
        radioFullTime = findViewById(R.id.radioFullTime);
        radioPartTime = findViewById(R.id.radioPartTime);
        btnSubmit = findViewById(R.id.btnSubmit);
        listView = findViewById(R.id.listView);
        textViewInfo = findViewById(R.id.textViewInfo);

        // Khởi tạo ArrayList và ArrayAdapter
        employeeList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeeList);
        listView.setAdapter(adapter);

        // Xử lý sự kiện nhấn nút Nhập
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maNV = editTextMaNV.getText().toString();
                String tenNV = editTextTenNV.getText().toString();
                Employee employee;

                if (radioFullTime.isChecked()) {
                    employee = new EmployeeFullTime(maNV, tenNV, 500); // Lương mặc định cho FullTime
                } else {
                    employee = new EmployeePartTime(maNV, tenNV, 150); // Lương mặc định cho PartTime
                }

                employeeList.add(employee);
                adapter.notifyDataSetChanged();
                editTextMaNV.setText(""); // Clear input
                editTextTenNV.setText(""); // Clear input
            }
        });

        // Xử lý sự kiện chọn phần tử trong ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee selectedEmployee = employeeList.get(position);
                textViewInfo.setText(selectedEmployee.toString());
            }
        });

        // Xử lý sự kiện long click để xóa phần tử
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                employeeList.remove(position);
                adapter.notifyDataSetChanged();
                textViewInfo.setText(""); // Clear info TextView
                return true;
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
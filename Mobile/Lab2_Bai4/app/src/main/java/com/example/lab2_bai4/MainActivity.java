package com.example.lab2_bai4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etId;
    private EditText etFullName;
    private CheckBox cbIsManager;
    private Button btnAdd;
    private ListView lvEmployees;

    private ArrayList<Employee> employees;
    private EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.et_id);
        etFullName = findViewById(R.id.et_fullname);
        cbIsManager = findViewById(R.id.cb_is_manager);
        btnAdd = findViewById(R.id.btn_add);
        lvEmployees = findViewById(R.id.lv_employees);

        employees = new ArrayList<>();
        employeeAdapter = new EmployeeAdapter(this, R.layout.item_employee, employees);
        lvEmployees.setAdapter(employeeAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                String fullName = etFullName.getText().toString();
                boolean isManager = cbIsManager.isChecked();

                Employee newEmployee = new Employee(fullName, isManager);
                employees.add(newEmployee);
                employeeAdapter.notifyDataSetChanged();

                etId.setText("");
                etFullName.setText("");
                cbIsManager.setChecked(false);
            }
        });
    }
}

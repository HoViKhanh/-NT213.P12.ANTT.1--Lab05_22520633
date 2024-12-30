package com.example.bai2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> names;
    ArrayAdapter<String> adapter;
    ListView lvPerson;
    EditText editText;
    Button btnSubmit;
    TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Khởi tạo các thành phần giao diện
        editText = findViewById(R.id.editText);
        btnSubmit = findViewById(R.id.btnSubmit);
        lvPerson = findViewById(R.id.lvPerson);
        textViewInfo = findViewById(R.id.textViewInfo);

        // 2. Tạo ArrayList và Adapter
        names = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        lvPerson.setAdapter(adapter);

        // 3. Xử lý sự kiện nhấn nút Nhập
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String newName = editText.getText().toString();
                if (!newName.isEmpty()) {
                    // Thêm dữ liệu mới vào ArrayList
                    names.add(newName);
                    // Cập nhật dữ liệu mới lên ListView
                    adapter.notifyDataSetChanged();
                    // Xóa nội dung trong EditText sau khi thêm
                    editText.setText("");
                }
            }
        });

        // 4. Xử lý sự kiện click vào một phần tử trong ListView
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Hiển thị vị trí và giá trị của phần tử đó
                String selectedItem = names.get(position);
                textViewInfo.setText("Position: " + position + " - Value: " + selectedItem);
            }
        });

        // 5. Xử lý sự kiện Long click để xóa phần tử trong ListView
        lvPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Xóa phần tử tại vị trí được chọn
                names.remove(position);
                // Cập nhật ListView sau khi xóa
                adapter.notifyDataSetChanged();
                return true; // trả về true để sự kiện long click không kích hoạt click bình thường
            }
        });
    }
}

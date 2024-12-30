package com.example.vikaapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vikaapp.SQLiteConnector;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity {
    private ListView userListView;
    private SQLiteConnector db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        // Liên kết ListView từ layout
        userListView = findViewById(R.id.userListView);
        // Nhận username của người dùng đã đăng nhập
        String loggedInUsername = getIntent().getStringExtra("username");
        // Khởi tạo SQLiteConnector
        db = new SQLiteConnector(this);
        // Kiểm tra nếu username là "admin"
        if ("admin".equals(loggedInUsername)) {
            //ArrayList<HashMap<String, String>> userList = (ArrayList<HashMap<String, String>>) db.getAllUsersWithHashPassword();
            ArrayList<HashMap<String, String>> userList =db.getAllUsersWithHashPassword();
            SimpleAdapter adapter = new SimpleAdapter(
                    this,
                    userList,
                    R.layout.user_info_list,
                    new String[]{"username", "email", "hashpassword"},
                    new int[]{R.id.textUsername, R.id.textEmail, R.id.textPasswordHash}
            );
            userListView.setAdapter(adapter);

        } else {
            // Thông báo nếu không phải admin
            Toast.makeText(this, "Bạn không có quyền xem danh sách tài khoản!", Toast.LENGTH_SHORT).show();
            finish(); // Đóng DashboardActivity
        }
    }
}

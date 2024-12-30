package com.example.vikaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vikaapp.SQLiteConnector;

public class LoginActivity extends AppCompatActivity {

    private SQLiteConnector db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Liên kết các thành phần giao diện
        EditText txtUsername = findViewById(R.id.loginUsername);
        EditText txtPassword = findViewById(R.id.loginPassword);
        Button btnLogin = findViewById(R.id.loginButton);
        TextView txtRegister = findViewById(R.id.textViewRegister);

        // Khởi tạo SQLiteConnector
        db = new SQLiteConnector(this);

        // Xử lý sự kiện khi nhấn nút Login
        btnLogin.setOnClickListener(v -> {
            String username = txtUsername.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();

            // Kiểm tra nếu username hoặc password rỗng
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Vui lòng nhập cả tên người dùng và mật khẩu!", Toast.LENGTH_SHORT).show();
            } else {
                // Kiểm tra thông tin đăng nhập trong cơ sở dữ liệu
                if (db.checkUser(username, password)) {
                    // Đăng nhập thành công
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, UserInfoActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                } else {
                    // Đăng nhập thất bại
                    Toast.makeText(LoginActivity.this, "Tên người dùng hoặc mật khẩu không hợp lệ!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện khi nhấn vào TextView Register
        txtRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}

package com.example.vikaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmailRegister, editTextUsernameRegister, editTextPasswordRegister;
    private Button buttonRegister;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Liên kết các thành phần giao diện
        editTextEmailRegister = findViewById(R.id.editTextEmailRegister);
        editTextUsernameRegister = findViewById(R.id.editTextUsernameRegister);
        editTextPasswordRegister = findViewById(R.id.editTextPasswordRegister);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Khởi tạo FirebaseAuth và Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Xử lý sự kiện khi nhấn nút Register
        buttonRegister.setOnClickListener(v -> {
            String email = editTextEmailRegister.getText().toString().trim();
            String username = editTextUsernameRegister.getText().toString().trim();
            String password = editTextPasswordRegister.getText().toString().trim();

            if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else {
                createUser(email, username, password);
            }
        });
    }

    // Phương thức tạo tài khoản
    private void createUser(String email, String username, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            storeUserData(user, username);
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Phương thức lưu thông tin người dùng vào Firestore
    private void storeUserData(FirebaseUser user, String username) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("username", username);
        userData.put("email", user.getEmail());

        db.collection("users")
                .document(user.getUid())
                .set(userData)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(RegisterActivity.this, "Lỗi khi lưu thông tin người dùng: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}

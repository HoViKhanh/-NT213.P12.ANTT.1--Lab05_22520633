package com.example.vikaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vikaapp.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;


public class SQLiteConnector extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";

    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    public SQLiteConnector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_NAME + " TEXT, "
                + COLUMN_USER_EMAIL + " TEXT, "
                + COLUMN_USER_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, hashPassword(user.getPassword()));

        long result = db.insert(TABLE_USER, null, values);
        db.close();
        return result != -1; // Trả về true nếu thêm thành công
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_EMAIL + " = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkUser(String username, String password) {
        String hashedPassword = hashPassword(password);
        if (hashedPassword == null) return false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_NAME + " = ? AND " + COLUMN_USER_PASSWORD + " = ?",
                new String[]{username, hashedPassword}
        );
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return exists;
    }
    public ArrayList<HashMap<String, String>> getAllUsersWithHashPassword() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT user_name, user_email, user_password FROM user", null);
        if (cursor.moveToFirst()) {
            int usernameIndex = cursor.getColumnIndex("user_name");
            int emailIndex = cursor.getColumnIndex("user_email");
            int passwordIndex = cursor.getColumnIndex("user_password");

            if (usernameIndex != -1 && emailIndex != -1 && passwordIndex != -1) {
                do {
                    HashMap<String, String> user = new HashMap<>();
                    user.put("username", cursor.getString(usernameIndex)); // Lấy username
                    user.put("email", cursor.getString(emailIndex)); // Lấy email
                    user.put("hashpassword", cursor.getString(passwordIndex)); // Lấy hashpassword
                    userList.add(user);
                } while (cursor.moveToNext());
            } else {
                //Log.e("DatabaseError", "Cột không tồn tại trong kết quả truy vấn");
            }
        }
        cursor.close();
        db.close();

        return userList;
    }




    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}

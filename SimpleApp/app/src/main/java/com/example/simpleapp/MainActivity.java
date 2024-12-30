package com.example.simpleapp;

// Import thư viện
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import android.os.StrictMode. ThreadPolicy.Builder;
import android.os.StrictMode;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setThreadPolicy(new Builder().permitAll().build());
        String url = "https://inseclab.uit.edu.vn/robots.txt";
        StringBuilder url_holder = new StringBuilder();
        url_holder.append(url);
        //BufferedReader buffer = null;
        try {
            // Tạo một connection
            URLConnection conn = new URL(url_holder.toString()).openConnection();
            // Thiết lập Header cho connection
            conn.setRequestProperty("Content-Type", "application/x-www-formurlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);
            // Tạo buffer để lấy dữ liệu về
            BufferedReader buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            // Sau đó đọc response trả về
            String response;
            String data_from_stream;
            for (response = new String(); true; response += data_from_stream) {
                String stream = buffer.readLine();
                data_from_stream = stream;
                if (stream == null) {
                    break;
                }
            }
            // Log ra để xem
            Log.i("InsecLab ", response);
        } catch (Exception e) {
            Log.e("Network Error", "Error fetching data", e);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("callback", "=== onStart() ===");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("callback", "=== onResume() ===");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("callback", "=== onPause() ===");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("callback", "=== onStop() ===");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("callback", "=== onDestroy() ===");
    }

}
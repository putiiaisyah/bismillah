package com.example.bismillah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginbutton;
    private Button signupbutton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this); // Inisialisasi DatabaseHelper

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginbutton = findViewById(R.id.loginButton);
        signupbutton = findViewById(R.id.signupButton);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(login.this, "Username dan password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    // Cek apakah username dan password cocok di database
                    boolean isUserExist = databaseHelper.checkUser(username, password);
                    if (isUserExist) {
                        Toast.makeText(login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        // Pindah ke halaman utama atau aktivitas lainnya
                    } else {
                        Toast.makeText(login.this, "Username atau password salah!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signUp.class);
                startActivity(intent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

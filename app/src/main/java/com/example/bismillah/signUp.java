package com.example.bismillah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class signUp extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText emailEditText; // Tidak digunakan di SQLite dalam contoh ini
    private EditText passwordEditText;
    private Button signupButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        databaseHelper = new DatabaseHelper(this); // Inisialisasi DatabaseHelper

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(signUp.this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = databaseHelper.addUser(username, password);
                    if (isInserted) {
                        Toast.makeText(signUp.this, "Pendaftaran berhasil!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(signUp.this, login.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(signUp.this, "Pendaftaran gagal!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

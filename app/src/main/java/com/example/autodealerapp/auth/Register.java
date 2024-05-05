package com.example.autodealerapp.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.autodealerapp.MainActivity;
import com.example.autodealerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// Kayıt olma ekranı oluşturulur
public class Register extends AppCompatActivity {

    // Gerekli değişkenler tanımlanır.
    TextInputEditText editTextEmail, editTextPassword;
    Button buttonRegister;
    FirebaseAuth mAuth;
    TextView textViewLogin;

    // Kullanıcı giriş yapmış mı kontrol edilir
    @Override
    public void onStart() {
        super.onStart();
        // Kullanıcı giriş yapmış mı kontrol edilir ve giriş yapmış ise ana ekrana yönlendirme yapılır
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Firebase Auth sınıfı oluşturulur
        mAuth = FirebaseAuth.getInstance();

        // Layout dosyasındaki elemanlar bağlanır
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        buttonRegister = findViewById(R.id.button_register);
        textViewLogin = findViewById(R.id.text_view_login_now);


        // Giriş yap butonuna tıklanınca yapılacak işlemler
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Giriş ekranına yönlendirme yapılır
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        // Kayıt ol butonuna tıklanınca yapılacak işlemler
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email, password;

                // Email ve şifre alınır
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                // Email ve şifre boş olamaz
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Firebase Auth sınıfı oluşturulur
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Kayıt işlemi başarılı olursa
                        if (task.isSuccessful()) {


                            // Kayıt işlemi başarılı olursa kullanıcıya mesaj gösterilir
                            Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();

                            // Ana ekrana yönlendirme yapılır
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }
        });

    }
}
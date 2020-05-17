package com.leklab.alex.lojabraga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText editTextReset;
    private Button buttonResetPassword;
    private TextView resetBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        editTextReset = findViewById(R.id.editTextReset);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);
        resetBack = findViewById(R.id.resetBack);

        resetBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPasswordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }



}

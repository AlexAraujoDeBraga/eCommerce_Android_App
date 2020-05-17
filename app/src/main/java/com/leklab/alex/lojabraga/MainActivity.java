package com.leklab.alex.lojabraga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editEmail;
    private EditText editSenha;
    private TextView esqueceuSenha;
    private ProgressDialog progressBar;
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        editEmail = findViewById(R.id.login_email);
        editSenha = findViewById(R.id.login_senha);
        esqueceuSenha = findViewById(R.id.esqueceu_senha);

        progressBar = new ProgressDialog(this);

        esqueceuSenha.setPaintFlags(esqueceuSenha.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        esqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intentReset = new Intent(MainActivity.this, ResetPasswordActivity.class);
                 startActivity(intentReset);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null){

            Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
            startActivity(intent);

            progressBar.hide();

        }
    }

    public void login(View view){
        String emailUsuario = editEmail.getText().toString().trim();
        String senhaUsuario = editSenha.getText().toString().trim();

        if (emailUsuario.equals("")){
            editEmail.setError("Preencha este campo!");
            editEmail.requestFocus();
            return;
        }

        if (senhaUsuario.equals("")){
            editSenha.setError("Preencha este campo!");
            editSenha.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(emailUsuario, senhaUsuario).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    updateUI(mAuth.getCurrentUser());
                } else{
                    Toast.makeText(MainActivity.this, "Usuário ou senha incorreto!", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });

        progressBar.setTitle("");
        progressBar.setMessage("Verificando informações inseridas.");
        progressBar.setCanceledOnTouchOutside(false);
        progressBar.show();
    }

    public void cadastro(View view){
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

}

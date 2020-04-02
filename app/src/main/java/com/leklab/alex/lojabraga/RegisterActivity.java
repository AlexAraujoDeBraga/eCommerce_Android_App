package com.leklab.alex.lojabraga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editNome;
    private EditText editPhone;
    private EditText editEmail;
    private EditText editSenha;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        editNome = findViewById(R.id.register_person);
        editPhone = findViewById(R.id.register_phone);
        editEmail = findViewById(R.id.register_email);
        editSenha = findViewById(R.id.register_senha);

        progressDialog = new ProgressDialog(this);

    }

    public void salvar(View view){
        final String nomeUsuario = editNome.getText().toString().trim();
        final String phoneUsuario = editPhone.getText().toString().trim();
        final String emailUsuario = editEmail.getText().toString().trim();
        final String senhaUsuario = editSenha.getText().toString().trim();

        if (nomeUsuario.equals("")){
            editNome.setError("Preencha este campo!");
            editNome.requestFocus();
            return;
        }

        if (phoneUsuario.equals("")){
            editPhone.setError("Preencha este campo!");
            editPhone.requestFocus();
            return;
        }

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

        progressDialog.setTitle("");
        progressDialog.setMessage("Verificando informações inseridas.");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(emailUsuario,senhaUsuario)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            FirebaseUser user = mAuth.getCurrentUser();

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference userRef = database.getReference("Users/"+user.getUid());

                            Map<String, Object> userInfos = new HashMap<>();

                            userInfos.put("Nome",nomeUsuario);
                            userInfos.put("Telefone",phoneUsuario);
                            userInfos.put("E-mail",emailUsuario);
                            userInfos.put("Senha",senhaUsuario);
                            userRef.setValue(userInfos);

                            finish();

                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e){
                                editSenha.setError("Senha fraca!");
                                editSenha.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e){
                                editEmail.setError("E-mail inválido!");
                                editEmail.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e){
                                editEmail.setError("E-mail já existe!");
                                editEmail.requestFocus();
                            } catch (Exception e){
                                Log.e("Cadastro", e.getMessage());
                            }
                        }
                    }
                });

        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);


    }

}

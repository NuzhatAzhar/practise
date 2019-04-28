package com.example.administrator.login_password;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText user_login;
    EditText user_pass;
    ImageView login_btn;
    FirebaseUser user;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if (user != null) {
            startActivity(new Intent(MainActivity.this,AddMovie.class));
        }
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=user_login.getText().toString();
                String pass=user_pass.getText().toString();
                Userauth(email,pass);

            }
        });
    }

    private void Userauth(String email, String pass) {
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    user=auth.getCurrentUser();
                    startActivity(new Intent(MainActivity.this,AddMovie.class));
                }
                else {

                }
            }
        });

    }

    private void init() {
        user_login=findViewById(R.id.edittext1);
        user_pass=findViewById(R.id.edittext2);
        auth=FirebaseAuth.getInstance();
        login_btn=findViewById(R.id.login_btn);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("Current Student");
        user=auth.getCurrentUser();
    }

    public void signup(View view) {
        startActivity(new Intent(MainActivity.this,Main2Activity.class));

    }

    public void login(View view) {
    }
}

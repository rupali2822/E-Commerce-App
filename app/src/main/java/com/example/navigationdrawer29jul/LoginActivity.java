package com.example.navigationdrawer29jul;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
TextInputEditText etMail,etPassword;
Context context;
TextView btnLogin;
TextView registerHere;
String mail,password,loginMail,loginPassword;
ProgressBar progressBar;
FirebaseAuth mAuth;
PreferenceManager pref;
    boolean islogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etMail=findViewById(R.id.loginmailId);
        etPassword=findViewById(R.id.loginpassword);
        btnLogin=findViewById(R.id.loginbtn);
        registerHere=findViewById(R.id.registerHere);
        progressBar=findViewById(R.id.progressBar);

        mAuth=FirebaseAuth.getInstance();
        pref=PreferenceManager.getINSTANCE(this);



        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

//      String user=  pref.getString("loginUser");
      if (firebaseUser!=null){
          Intent i=new Intent(LoginActivity.this,MainActivity.class);
          startActivity(i);
      }

//        islogin=false;
//        if (islogin==true){
//            Intent i=new Intent(LoginActivity.this,MainActivity.class);
//            startActivity(i);
//        }



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });

        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void loginUserAccount() {
       // progressBar.setVisibility(View.VISIBLE);
        mail=etMail.getText().toString();
        password=etPassword.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(mail)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (!mail.isEmpty() && !password.isEmpty()) {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(mail, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(),
                                                "Login successful!!",
                                                Toast.LENGTH_LONG)
                                                .show();

                                        pref.setString("loginUser",mail);
                                        pref.setString("loginPassword",password);

                                        // hide the progress bar
                                        progressBar.setVisibility(View.GONE);

                                        // if sign-in is successful
                                        // intent to home activity
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {

                                        // sign-in failed
                                        Toast.makeText(getApplicationContext(), "Login failed!!", Toast.LENGTH_LONG).show();

                                        // hide the progress bar
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplicationContext(), "Invalid credentials!!", Toast.LENGTH_LONG).show();

                                }
                            });
                        }
                    });
        }else {
            Toast.makeText(context, "Enter login credentials", Toast.LENGTH_SHORT).show();
        }
    }
}
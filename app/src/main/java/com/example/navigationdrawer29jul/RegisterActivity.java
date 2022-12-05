package com.example.navigationdrawer29jul;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends AppCompatActivity {
TextInputEditText etname,etcontact,etaddress,etpinCode,etmailId,etpassword,etconfmPass;
String name,contact,address,pincode,mailid,password,cpassword,confirmPassword;
Button registerBtn;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
PreferenceManager pref;
FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etname=findViewById(R.id.etName);
        etcontact=findViewById(R.id.etContact);
        etaddress=findViewById(R.id.etAddress);
        etpinCode=findViewById(R.id.etpin);
        etmailId=findViewById(R.id.etmailId);
        etpassword=findViewById(R.id.etpassword);
        etconfmPass=findViewById(R.id.etConfirmpassword);
        registerBtn=findViewById(R.id.btnRegister);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("User Master");
        auth=FirebaseAuth.getInstance();


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=etname.getText().toString();
                contact=etcontact.getText().toString();
                address=etaddress.getText().toString();
                pincode=etpinCode.getText().toString();
                mailid=etmailId.getText().toString();
                password=etpassword.getText().toString();
                cpassword=etconfmPass.getText().toString();

                if (password.equals(cpassword)){
                    confirmPassword=cpassword;
                }else {
                    etconfmPass.setError("Password not matched!!");
                }

                RegisterUser();


            }
        });
    }

    private void RegisterUser() {
        Log.e("","mail id: "+mailid);
        Log.e("","password: "+password);
        auth.createUserWithEmailAndPassword(mailid,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_LONG).show();
                // If sign-in fails, display a message to the user. If sign-in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                            Toast.LENGTH_LONG).show();
                    Log.e("MyTag", task.getException().toString());
                } else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Log.e(TAG, "RegisterInDatabase: "+user.toString());
                    String userId=user.getUid();

                    RegisterInDatabase(userId);
                    pref=PreferenceManager.getINSTANCE(RegisterActivity.this);
                    pref.setString("userId",userId);
                    Log.e("user","id  "+userId);
                  //  pref.setString("name",name);
//                    pref.setString("contact",contact);
//                    pref.setString("address",address);

                    Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                   // i.putExtra("userId",userId);
                    finish();
                }
            }
        });
    }

    private void RegisterInDatabase(String id) {
        String uid=id;
        Map mapData=new HashMap<>();
        mapData.put("Username",name);
        mapData.put("contact",contact);
        mapData.put("address",address);
        mapData.put("pincode",pincode);
        mapData.put("mail",mailid);
        mapData.put("password",confirmPassword);

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        Log.e(TAG, "RegisterInDatabase: "+user.toString());
//        String userId=user.getUid();
      //  String id=mailid.toString();


      // com.google.firebase.auth.internal.zzx@a847c5a
      // databaseReference.child(userId).setValue(mapData)

        try {
            databaseReference.child(uid).setValue(mapData)

                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            task.addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
            Log.e("excpt",""+e.getMessage());
        }

    }
}
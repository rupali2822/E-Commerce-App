package com.example.navigationdrawer29jul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MakeOrderActivity extends AppCompatActivity {
String loginUser,customerName,contact,address,productName,productPrice,pinCode;
TextView cName,cAddress,cContact,changeAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);

        cName=findViewById(R.id.cName);
        cAddress=findViewById(R.id.cAddress);
        cContact=findViewById(R.id.cContact);
        changeAddress=findViewById(R.id.change);

        PreferenceManager pref=PreferenceManager.getINSTANCE(this);
        String prefUser=pref.getString("loginUser");
        customerName=pref.getString("uName");
        contact=pref.getString("uContact");
        address=pref.getString("uAddress");
        pinCode=pref.getString("uPinCode");

        Intent i=getIntent();
        loginUser=i.getStringExtra("loginUser");
        productName=i.getStringExtra("productName");
        productPrice=i.getStringExtra("productPrice");
        String url=i.getStringExtra("productUrl");
//        productName= i.getStringExtra("name");
//        productPrice=  i.getStringExtra("price");
//        String producturl=i.getStringExtra("url");

        String addr=address.concat(" - ");
        String fullAddress=addr.concat(pinCode);
        Log.e("from pref loginUser",""+prefUser +addr);

        Log.e("loginUser",""+loginUser);
        Log.e("name",""+productName);
        Log.e("price",""+productPrice);
        Log.e("url",""+url);
        Log.e("name",""+customerName);
        Log.e("contact",""+contact);
        Log.e("address",""+fullAddress);
       // Log.e("loginUser",""+loginUser);
cName.setText(customerName);
cAddress.setText(fullAddress);
cContact.setText(contact);



    }
}
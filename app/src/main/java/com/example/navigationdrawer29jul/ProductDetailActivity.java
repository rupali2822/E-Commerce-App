package com.example.navigationdrawer29jul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.navigationdrawer29jul.database.ProductListAdapter;
import com.example.navigationdrawer29jul.database.ProductListModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ProductDetailActivity extends AppCompatActivity {
TextView descName,descPrice,descRam,descStorage,descColor;
ImageView descImg;
Context context;
ProgressBar progressBar;
Button btnPlaceOrder;
PreferenceManager preferenceManager;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

//        ProgressDialog pd=new ProgressDialog(ProductDetailActivity.this);
//
//        pd.show();
//        pd.dismiss();

       preferenceManager=PreferenceManager.getINSTANCE(context);
        ProductListModel model=new ProductListModel(context);
        //RecyclerData recyclerData=new RecyclerData(context);
        //recyclerData.getImgid();
        descName=findViewById(R.id.descProductName);
        descImg=findViewById(R.id.descImg);
        descPrice=findViewById(R.id.descPrice);
        descColor=findViewById(R.id.descColor);
        descRam=findViewById(R.id.descRam);
        descStorage=findViewById(R.id.descStorage);
        btnPlaceOrder=findViewById(R.id.placeOrder);

       progressBar=findViewById(R.id.progressBar1);
        progressBar.setProgress(100);
//        progressBar.setVisibility(View.VISIBLE);
       // progressBar.getProgress();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Order Master");

        Intent i=getIntent();
       String name= i.getStringExtra("name");
      String price=  i.getStringExtra("price");
      String url=i.getStringExtra("url");
        String color=i.getStringExtra("color");
        String ram=i.getStringExtra("ram");
        String storage=i.getStringExtra("storage");


        Log.e("imgUrl",""+url);
        Log.e("","name"+name);
        Log.e("price",""+price);

        try{
            new ProductListAdapter.ImageLoadClass(url,descImg).execute();
            progressBar.setVisibility(View.INVISIBLE);

        }catch (Exception e){
            Log.e("img","catch"+e.getMessage());
        }
        descName.setText(name);
        descPrice.setText(price);
        descColor.setText(color);
        descRam.setText(ram);
        descStorage.setText(storage);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String user=  preferenceManager.getString("loginUser");
              Log.e("login user",""+user);

                Intent i=new Intent(ProductDetailActivity.this, MakeOrderActivity.class);
                i.putExtra("loginUser",user);
                i.putExtra("productName",name);
                i.putExtra("productPrice",price);
                i.putExtra("productUrl",url);
                //i.putExtra()
                startActivity(i);





            }
        });

    }
}
package com.example.navigationdrawer29jul;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.navigationdrawer29jul.database.ProductListAdapter;
import com.example.navigationdrawer29jul.database.ProductListModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ProductListActivity extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<ProductListModel> productModelArrayList;
Context context;
FirebaseDatabase firebaseDatabase;
DatabaseReference myRef;
    String productImgUrl;
    Bitmap productImage;
String tag="ProductListActivity";
    String name,price,color,ram,storage,img;
    ProductListAdapter productListAdapter;
    ArrayList<String> myImageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        isNetworkAvailable();


//        ProgressDialog pd=new ProgressDialog(ProductListActivity.this);
//        pd.setTitle("Loading...");
//        pd.setMessage("please wait..");
//        pd.show();


        if (isNetworkAvailable()) {
            productModelArrayList = new ArrayList<>();
            getDataFromFirebase();


        // getDataFromFirebase();


        // pd.show();

//        ValueEventListener valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.e("inside","valueListner");
//                Map<String,String> map = new HashMap<>();
//                String name = dataSnapshot.child("name").getValue().toString();
//                String price = dataSnapshot.child("price").getValue().toString();
//                Log.e(TAG, "onDataChange: "+name+price );
//                map.put("Name",name);
//                map.put("Price",price);
//                mobilesList.add((HashMap) map);
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.d(tag, databaseError.getMessage());
//            }
//        };
//
//        Log.e(TAG, "onCreate: mobilelist"+mobilesList.toArray().length );
//        mobref.addValueEventListener(valueEventListener);
//       recyclerDataArrayList=new ArrayList<>();
//       recyclerDataArrayList.add(new RecyclerData(name,price,R.drawable.chat));
//        recyclerDataArrayList.add(new RecyclerData("Mobile","780",R.drawable.drum_set));
//        recyclerDataArrayList.add(new RecyclerData("Chat","270",R.drawable.chat));
//        recyclerDataArrayList.add(new RecyclerData("Cover","390",R.drawable.chat));
//        recyclerDataArrayList.add(new RecyclerData("HeadPhone","500",R.drawable.calendar));
//        recyclerDataArrayList.add(new RecyclerData("HeadPhone","220",R.drawable.chat));
//        recyclerDataArrayList.add(new RecyclerData("Chat","270",R.drawable.telephone));
//        recyclerDataArrayList.add(new RecyclerData("Cover","390",R.drawable.chat));
//        recyclerDataArrayList.add(new RecyclerData("HeadPhone","500",R.drawable.user));
//        recyclerDataArrayList.add(new RecyclerData("HeadPhone","500",R.drawable.calendar));
//        recyclerDataArrayList.add(new RecyclerData("HeadPhone","220",R.drawable.chat));
//        recyclerDataArrayList.add(new RecyclerData("Chat","270",R.drawable.telephone));
//        recyclerDataArrayList.add(new RecyclerData("Cover","390",R.drawable.chat));
//        recyclerDataArrayList.add(new RecyclerData("HeadPhone","500",R.drawable.user));
        recyclerView = findViewById(R.id.product_rec_view);

        //  recyclerDataArrayList.add(new RecyclerData(name,price,R.drawable.bell));

        productListAdapter = new ProductListAdapter(context, productModelArrayList);
        productListAdapter.notifyDataSetChanged();


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productListAdapter);

        // pd.dismiss();

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

                //  pd.dismiss();
//                  rv.getId();
//                  rv.getChildItemId(rv.getRootView());
                Toast.makeText(ProductListActivity.this, "rv id" + rv.getId(), Toast.LENGTH_SHORT).show();
                Toast.makeText(ProductListActivity.this, "child id" + rv.getChildItemId(recyclerView), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

//        RecyclerData data=new RecyclerData("Book","340",R.drawable.telephone);
//        data.getImgid();
        } else {
            Toast.makeText(context, "Network not available", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean isNetworkAvailable() {
        boolean status=false;
        try{
            ConnectivityManager cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=cm.getNetworkInfo(0);
            if (networkInfo!=null && networkInfo.getState()==NetworkInfo.State.CONNECTED){
                status=true;
            }else {
                networkInfo=cm.getNetworkInfo(1);
                if (networkInfo!=null && networkInfo.getState()==NetworkInfo.State.CONNECTED){
                    status=true;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return status;
    }

    private boolean getDataFromFirebase() {

        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference();
//        DatabaseReference mobRef=myRef.child("Category/Mobile");
//       DatabaseReference mobNameRef=mobRef.child("Mi");
/*
        mobRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> mob = (Map<String, Object>) snapshot.getValue();


                for (String childKey: mob.keySet()) {
                    //childKey is your "-LQka.. and so on"
                    //Your current object holds all the variables in your picture.
                    Map<String, Object> currentLubnaObject = (Map<String, Object>) mob.get(childKey);

                    Log.e("object 1: ",""+currentLubnaObject.get("color"));
                    //You can access each variable like so: String variableName = (String) currentLubnaObject.get("INSERT_VARIABLE_HERE"); //data, description, taskid, time, title
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
*/



        DatabaseReference mobref = myRef.child("Product Master").child("Category").child("mobile");
        Log.e("snapshot","value 1");

        mobref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                RecyclerData recyclerData = new RecyclerData(ProductListActivity.this);

                Log.e("snapshot","value");
                name = snapshot.child("name").getValue().toString();
                price = snapshot.child("price").getValue().toString();
                img=snapshot.child("imgUrl").getValue().toString();


                color=snapshot.child("color").getValue().toString();
                ram=snapshot.child("ram").getValue().toString();
                storage=snapshot.child("storage").getValue().toString();
//                 if (snapshot.hasChild("url")){
//                     productImgUrl=snapshot.child("url").getValue().toString();
//                     Log.e("String productImgUrl",""+productImgUrl);
//                     URL url1 = null;
//                     try {
//                         URL url = new URL(productImgUrl);
//                         productImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
////                         url1 = new URL(url);
////                         BufferMap c = ImageIO.read(url1);
////                         ImageIcon image = new ImageIcon(c);
//////                     jXImageView1.setImage(image);
//                     } catch (MalformedURLException e) {
//                         e.printStackTrace();
//                     } catch (IOException e) {
//                         e.printStackTrace();
//                     }
//
//
//                 }


                Log.e(TAG, "onDataChange: name "+name );
                Log.e(TAG, "onDataChange: color "+color );
                Log.e(TAG, "onDataChange: prize "+price );
                Log.e(TAG, "onDataChange: RAM "+ram );
                Log.e(TAG, "onDataChange: stoorage "+storage );
                Log.e(TAG, "onDataChange: img "+img );


             //   Log.e(TAG,""+color+ram+storage);
//                if (name.contains("vivo1804")){
//
//                }

                //  Drawable myDrawable = imageView.getDrawable();

                productModelArrayList.add(new ProductListModel(name,color,ram,storage,price,img));
                // recyclerDataArrayList.add(new RecyclerData(name,price,img,color,ram,storage));
                productListAdapter.notifyDataSetChanged();

//                mobilesList.add((HashMap) map);
//                recyclerDataArrayList.add(recyclerData);



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        return  true;

    }
}
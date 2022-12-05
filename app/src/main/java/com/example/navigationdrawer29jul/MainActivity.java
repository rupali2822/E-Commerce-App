package com.example.navigationdrawer29jul;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigationdrawer29jul.user.AddNewProductFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;


import static android.util.Base64.DEFAULT;

public class MainActivity extends AppCompatActivity{
Toolbar toolbar;
NavigationView navigationView;
DrawerLayout drawerLayout;
    ImageView profileImg;
    TextView profName,profId;
    PreferenceManager preferenceManager;
    ImageView bellIcon;
    Button button1;
    TextView countText;
    int count;
    CardView cardView;
    BottomNavigationView bottomNavigationView;
    String loginUser;
    ImageView logout;
    FirebaseAuth firebaseAuth;
    PreferenceManager pref;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseRef;
String uName,uMailId,uContact,uAddress,uPincode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("E Shopping");

        try {
//get User details...
            pref = PreferenceManager.getINSTANCE(MainActivity.this);
            String user = pref.getString("userId");
            // String username=pref.getString("name");
            Toast.makeText(MainActivity.this, "Login id "+user.toString(), Toast.LENGTH_SHORT).show();
            Log.e("user" + user, "");

            firebaseDatabase=FirebaseDatabase.getInstance();
            databaseRef=firebaseDatabase.getReference();
            DatabaseReference userRef=databaseRef.child("User Master");
            userRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.e("snapshot","value");
                    uName = snapshot.child("Username").getValue().toString();
                    uMailId = snapshot.child("mail").getValue().toString();
                    uContact=snapshot.child("contact").getValue().toString();
                    uAddress=snapshot.child("address").getValue().toString();
                    uPincode=snapshot.child("pincode").getValue().toString();

                    Log.e("snapshot","uName "+uName);
                    Log.e("snapshot","uMailid "+uMailId);
                    Log.e("snapshot","uContact "+uContact);
                    Log.e("snapshot","uAddress "+uAddress);
                    Log.e("snapshot","uPincode "+uPincode);

                    pref.setString("uName",uName);
                    pref.setString("uMailId",uMailId);
                    pref.setString("uContact",uContact);
                    pref.setString("uAddress",uAddress);
                    pref.setString("uPinCode",uPincode);

                    try{
                        profName.setText(uName);
                        profId.setText(uMailId);
                        Log.e("prof","name set");

                    }catch (Exception e){
                        Log.e("prof","catch exception  "+e.getMessage());

                    }


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

        }catch (Exception e){
            Log.e("MainActivity",""+e.getMessage());
        }

        bellIcon=findViewById(R.id.notificationIcon);
        countText=findViewById(R.id.notificationText);
        cardView=findViewById(R.id.notificationNumberContainer);
        bottomNavigationView=findViewById(R.id.bottom_nav_view);
        logout=findViewById(R.id.logout);



        HomeFragment homeFragment=new HomeFragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,homeFragment,"Home Fragment");
        fragmentTransaction.commit();




        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Log Out ", Toast.LENGTH_SHORT).show();

              Intent i=new Intent(MainActivity.this,LoginActivity.class);
              startActivity(i);
            }
        });
/*

        if (isAllreadyLogin==false){
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
              Intent i=getIntent();
             isAllreadyLogin= i.getBooleanExtra("isLogin",false);
             if (isAllreadyLogin==true){
                 HomeFragment homeFragment=new HomeFragment();
                 FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                 fragmentTransaction.replace(R.id.framelayout,homeFragment,"Home Fragment");
                 fragmentTransaction.commit();
             }else {
                 Toast.makeText(this, "please login..", Toast.LENGTH_SHORT).show();
             }
        }else {
            isAllreadyLogin=true;
            HomeFragment homeFragment=new HomeFragment();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout,homeFragment,"Home Fragment");
            fragmentTransaction.commit();
        }

 */

//        LoginFragment loginFragment=new LoginFragment();
//        openFragment(loginFragment);
//




        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeMenu:
                        Toast.makeText(MainActivity.this, "Home clicked", Toast.LENGTH_SHORT).show();
                        HomeFragment homeFragment=new HomeFragment();
                        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.framelayout,homeFragment,"Home Fragment");
                        fragmentTransaction.commit();
                        break;
                    case R.id.myOrder:
                        Toast.makeText(MainActivity.this, "mail clicked", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(MainActivity.this,MyOrderActivity.class);
                        startActivity(i);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "default", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        count=4;
        //ShortcutBadger.applyCount(getApplicationContext(),count);
/*
        if (count>0){
          countText.setText(String.valueOf(count));
        }else {
            cardView.setVisibility(View.INVISIBLE);
        }


        bellIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "icon clicked", Toast.LENGTH_SHORT).show();

                cardView.setVisibility(View.INVISIBLE);

            }
        });

 */

        toolbar=findViewById(R.id.main_toolbar);
        //toolbar.setTitle("Home");

        toolbar.setTitle("User Login");
       // toolbar.setSubtitle("please login to continue..");
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
         this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //change navigation default icon
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_view_headline_icon);

        navigationView=findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);

        preferenceManager=PreferenceManager.getINSTANCE(this);

        View header = navigationView.getHeaderView(0);
        profileImg=header.findViewById(R.id.profile_img);
         profName=header.findViewById(R.id.profName);
         profId=header.findViewById(R.id.prodId);



         retriveImg();

         profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "profile Clicked", Toast.LENGTH_SHORT).show();

                checkCameraPermission();
            }
        });


    }

    private void checkCameraPermission() {
       if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED &&
       ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED &&
       ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
       {
           ActivityCompat.requestPermissions(this,new String[]{
                   Manifest.permission.CAMERA,
                   Manifest.permission.WRITE_EXTERNAL_STORAGE,
                   Manifest.permission.READ_EXTERNAL_STORAGE
           },100);

       }else{
           openCamera();
       }
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivityForResult(cameraIntent, 100);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode==100){
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }else {
                    Toast.makeText(this, "Camera permission required", Toast.LENGTH_SHORT).show();
                }
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       // Toast.makeText(this, " inside Image captured.", Toast.LENGTH_SHORT).show();

        if (requestCode==100){
            Log.e("inside","get Image");

            if (requestCode == 100) {
                //set img to profile
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                //  profIileImg.setImageBitmap(photo);

                //save img
                try {
                    ByteArrayOutputStream boas = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.JPEG, 100, boas);
                    byte[] b = boas.toByteArray();

                    // String encodedImage = MediaStore.Images.Media.insertImage(getContentResolver(),photo, "myImg", null);
                    String encodedImage = Base64.encodeToString(b, DEFAULT);

                    Log.e("try", "encodedImage " + encodedImage);
                    preferenceManager.setString("img_data",encodedImage);

                    Toast.makeText(this, "Image save in sharedPreferences", Toast.LENGTH_SHORT).show();

                    retriveImg();

                    //to save in galary default location
                  String image=MediaStore.Images.Media.insertImage(getContentResolver(),photo,"demo_img","demoImg");
                Uri URI=Uri.parse(image);
                    Log.e("img", "save in galary" );
                    Toast.makeText(MainActivity.this, "Image save in galary", Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    Log.e("inside", "catch" + e.getMessage());
                }
            }else {
                Toast.makeText(this, "Could not capture image", Toast.LENGTH_SHORT).show();
            }




        }
    }

    private void retriveImg() {
        String prefImg=preferenceManager.getString("img_data");
        if (!prefImg.equalsIgnoreCase("")){
            byte b[]=Base64.decode(prefImg, DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(b,0,b.length);

            profileImg.setImageBitmap(bitmap);
            Toast.makeText(this, "Pref img set", Toast.LENGTH_SHORT).show();
        }else{
            profileImg.setImageResource(R.drawable.ic_profile);
        }

    }

    private void setupDrawerContent(NavigationView navigationView) {
           navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
               @Override
               public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                       switch (item.getItemId()){
                           case R.id.addNewProduct:
//                               Toast.makeText(MainActivity.this, "menu1 is clicked", Toast.LENGTH_SHORT).show();
                               AddNewProductFragment addNewProductFragment=new AddNewProductFragment();
                              openFragment(addNewProductFragment);
                               drawerLayout.closeDrawers();

                               break;
                           case R.id.login:
                              // Toast.makeText(MainActivity.this, "menu2 is clicked", Toast.LENGTH_SHORT).show();
//                               LoginFragment loginFragment=new LoginFragment();
//                               openFragment(loginFragment);
//                               drawerLayout.closeDrawers();

                               break;
                           default:
                               Toast.makeText(MainActivity.this, "..", Toast.LENGTH_SHORT).show();

                       }
                   return true;
               }
           });
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment,"Fragment");
        fragmentTransaction.commit();

    }

}
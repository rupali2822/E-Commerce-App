package com.example.navigationdrawer29jul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.navigationdrawer29jul.database.ImageAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class getImageActivity extends AppCompatActivity {
RecyclerView recyclerView;
ProgressBar progressBar;
ImageAdapter adapter;
Context context;
    StorageReference listRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_image);

        ArrayList<ArrayList<String>> finalList = new ArrayList<>();
        ArrayList<String> imgList = new ArrayList<>();
        ArrayList<String> oppoList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
//        adapter=new ImageAdapter(imgList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));

        // progressBar=findViewById(R.id.progress);
        // progressBar.setVisibility(View.VISIBLE);

        String vivoImg, oppoImg, name;
        vivoImg = "images/vivo1804";
        oppoImg = "vivoimages/imgOppo";
        name = "oppo";
        ArrayList<String> list = new ArrayList<>();
        list.add(vivoImg);
        list.add(oppoImg);
        list.add("mi");
        /*
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i)) {
                case "vivo":
                    listRef = FirebaseStorage.getInstance().getReference().child("images/" + vivoImg + ".jpg/");
                    Log.e("switch", "case");
                case "oppo":
                    listRef = FirebaseStorage.getInstance().getReference().child(oppoImg + ".jpg/");
                default:
                    Log.e("defult", "");
                    // listRef = FirebaseStorage.getInstance().getReference().child(oppoImg + ".jpg/");

//            if (list.get(i)=="vivo"){
//                listRef= FirebaseStorage.getInstance().getReference().child(oppoImg+".jpg/");
//
//            }

//        if (list.contains("vivo")){
//                        listRef= FirebaseStorage.getInstance().getReference().child("images/"+vivoImg+".jpg/");
//
//        }else if (list.contains("oppo")){
//                        listRef= FirebaseStorage.getInstance().getReference().child(oppoImg+".jpg/");
//
//        }else {
//            Log.e("path","not found");
//        }

//        if (name.equals("vivo")){
//            listRef= FirebaseStorage.getInstance().getReference().child("images/"+vivoImg+".jpg/");
//        }else {
//            listRef= FirebaseStorage.getInstance().getReference().child(oppoImg+".jpg/");
//
//        }
            }
        }

         */
                    listRef = FirebaseStorage.getInstance().getReference().child("images/");
                    listRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                        @Override
                        public void onSuccess(ListResult listResult) {
                            for (StorageReference file : listResult.getItems()) {
                                file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        try {
                                            Log.e("inside", " onsuccess ");
                                            String imgname;
                                            imgname = uri.toString();
                                            // imgname="https://firebasestorage.googleapis.com/v0/b/ecommerceadmin-97abf.appspot.com/o/images%2Fvivo1804.jpg%2F4e2ac562-054f-4ca3-a1e6-82cf4d988633?alt=media&token=4d4f5945-97d3-4e10-9478-9cb6173edf96";
                                            Log.e("inside", " onsuccess 2 " + imgname);

                                            if (imgname.contains("imgOppo")) {
                                                Log.e("oppo", " 1 in list ");

                                               // oppoList.add(uri.toString());
                                            } else {
                                                Log.e("oppo", " not in list ");
                                               // imgList.add(uri.toString());

                                            }
//                                if (imgname.contains("vivo")) {
//                                    Log.e("vivo", " 1 in list ");
//
//                                }else{
//                                    Log.e("vivo", " not in list ");
//
//                                }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        imgList.add(uri.toString());
                                        Log.e("imglist", " size "+imgList.size());


                                        Log.e("Itemvalue", uri.toString());
                                    }
                                }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
//                                        finalList.add(oppoList);
//                                        finalList.add(imgList);
                                        adapter = new ImageAdapter(imgList, context);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(null));
                                        recyclerView.setAdapter(adapter);
                                        //  progressBar.setVisibility(View.GONE);
                                    }
                                });
                            }
//                            Intent i=new Intent(getImageActivity.this,ProductListActivity.class);
//                            i.putExtra("imglist",imgList);
//                            startActivity(i);
                        }
                    });

        /*
        listRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for(StorageReference file:listResult.getItems()){
                    file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imgList.add(uri.toString());
                            Log.e("Itemvalue",uri.toString());
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            adapter=new ImageAdapter(imgList,context);
                            recyclerView.setLayoutManager(new LinearLayoutManager(null));
                            recyclerView.setAdapter(adapter);
                          //  progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
*/
            if (imgList.contains("vivo1804.jpg")) {
                Log.e("vivo", "in list");
            } else {
                Log.e("vivo", "not found in list");
            }


//            for (int i=0;i<imgList.size();i++){
//                Log.e("inside","for loop");
//                Log.e("imglist item",""+imgList.get(i).toString());
//                if (imgList.get(i).contains("imgOppo")){
//                    Log.e("true","");
//                }
//            }

    }
}
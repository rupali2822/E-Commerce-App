package com.example.navigationdrawer29jul.database;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer29jul.ProductDetailActivity;
import com.example.navigationdrawer29jul.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    Context mcontext;
    ProgressDialog pd;
private ArrayList<ProductListModel> ProductModelList;

    public ProductListAdapter(Context context, ArrayList<ProductListModel> productModelList) {
        this.mcontext = context;
        ProductModelList = productModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        pd=new ProgressDialog(view.getContext());
        pd.setTitle("Loading..");

        pd.show();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

           ProductListModel model=ProductModelList.get(position);
           holder.tvName.setText(model.getName());
           holder.tvPrice.setText(model.getPrice());

           String imgUrl=model.getImgUrl();
           Log.e("url",""+imgUrl);

        new ImageLoadClass(imgUrl, holder.tvImg).execute();
           /*
        InputStream inputStream;
        Bitmap bitmap;
        try {
//            inputStream = new java.net.URL(imgUrl).openStream();
//            bitmap = BitmapFactory.decodeStream(inputStream);
            //holder.tvImg.setImageBitmap(bitmap);

            URL url = new URL(imgUrl.toString());
            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.tvImg.setImageBitmap(image);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("exp","while setting img to imgview"+e.getMessage());
        }
//        Picasso.get().load(model.getImgUrl()).into(holder.tvImg);
//        Picasso.with(context);


            */
        pd.dismiss();
    }

    @Override
    public int getItemCount() {
        return ProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
ImageView tvImg;
TextView tvName,tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvImg=itemView.findViewById(R.id.img);
            tvName=itemView.findViewById(R.id.tvName);
            tvPrice=itemView.findViewById(R.id.tvPrice);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        //    Toast.makeText(v.getContext(), "item clicked: "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(v.getContext(), "item name: "+tvName.getText(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(v.getContext(), "item price: "+tvPrice.getText(), Toast.LENGTH_SHORT).show();
            //tvImg.get
            ProductListModel model=ProductModelList.get(getAdapterPosition());
            String url=model.getImgUrl();
            String name=model.getName();
            String price=model.getPrice();
            String color=model.getColor();
            String ram=model.getRam();
            String storage=model.getStorage();

           // Toast.makeText(v.getContext(), "url: "+url, Toast.LENGTH_SHORT).show();

            Log.e("selected url",""+url);

            Log.e("selected name",""+name);
            Log.e("price",""+price);



            Intent i=new Intent(v.getContext(), ProductDetailActivity.class);
            i.putExtra("name",name);
            i.putExtra("price",price);
            i.putExtra("url",url);
            i.putExtra("color",color);
            i.putExtra("ram",ram);
            i.putExtra("storage",storage);
            v.getContext().startActivity(i);
        }
    }


   public static class ImageLoadClass extends AsyncTask<Void, Void, Bitmap> {
String url;
ImageView imageView;

       public ImageLoadClass(String url, ImageView imageView) {
           this.url = url;
           this.imageView = imageView;
       }

       @Override
       protected Bitmap doInBackground(Void... voids) {
           try {
               URL urlConnection = new URL(url);
               HttpURLConnection connection = (HttpURLConnection) urlConnection
                       .openConnection();
               connection.setDoInput(true);
               connection.connect();
               InputStream input = connection.getInputStream();
               Bitmap myBitmap = BitmapFactory.decodeStream(input);
               return myBitmap;
           } catch (Exception e) {
               e.printStackTrace();
           }
           return null;
       }

       @Override
       protected void onPostExecute(Bitmap result) {
           super.onPostExecute(result);
           imageView.setImageBitmap(result);
           Log.e("img","set successfully.");
       }
   }
}

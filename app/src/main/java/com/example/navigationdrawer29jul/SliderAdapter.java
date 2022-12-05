package com.example.navigationdrawer29jul;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {
    //list for storing urls of images.
    private  int image[];
    private String text[];

    public SliderAdapter(int[] image, String[] text) {
        this.image = image;
        this.text = text;

    }


    //We are inflating the slider_layout inside on Create View Holder method.
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    //Inside on bind view holder we will set data to item of Slider View.
    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {
             viewHolder.imageViewBackground.setImageResource(image[position]);
             viewHolder.textView.setText(text[position]);

        //Glide is use to load image from url in your imageview.

    }

    //this method will return the count of our list.
    @Override
    public int getCount() {
        return image.length;
    }

    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        //Adapter class for initializing the views of our slider view.
        View itemView;
        TextView textView;
        ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.image);
            textView=itemView.findViewById(R.id.sliderText);
            this.itemView = itemView;
        }
    }


    }




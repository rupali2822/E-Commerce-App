package com.example.navigationdrawer29jul;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    BottomNavigationView bottomNavigationView;
CardView card1,card2;
SliderView sliderView;
    Button btnGo;
    Context context;
    PreferenceManager pref;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private int slider_images[];
    private String slider_text[];
    SliderAdapter sliderAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
       // btnGo=view.findViewById(R.id.btn_go);
//        bottomNavigationView=view.findViewById(R.id.bottom_nav_view);
//        bottomNavigationView.setVisibility(View.VISIBLE);



        card1=view.findViewById(R.id.card1);
        card2=view.findViewById(R.id.card2);
        sliderView=view.findViewById(R.id.slider);

         slider_images=new int[]{R.drawable.laptopimg1,R.drawable.laptopimg2,R.drawable.laptopimg3};
         slider_text=new String[]{"Available Product","..",".."};
         sliderAdapter=new SliderAdapter(slider_images,slider_text);


        //below method is used to set auto cycle direction in left to right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        //below method is used to setadapter to sliderview.
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        //below method is use to set scroll time in seconds.
        sliderView.setScrollTimeInSec(3);
        //to set it scrollable automatically we use below method.
        sliderView.setAutoCycle(true);
        //to start autocycle below method is used.
        sliderView.startAutoCycle();
        // Inflate the layout for this fragment

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),ProductListActivity.class);
                startActivity(i);

            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getActivity(),getImageActivity.class);
                startActivity(i);
            }
        });



        return view;

    }
}
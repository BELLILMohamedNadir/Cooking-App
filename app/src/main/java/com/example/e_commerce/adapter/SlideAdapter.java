package com.example.e_commerce.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.e_commerce.R;
import com.example.e_commerce.pojo.RandomMeals;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SlideAdapter extends SliderViewAdapter<SlideAdapter.SlideAdapterViewHolder> {
    ArrayList<RandomMeals.Meals> data;
    public SlideAdapter(ArrayList<RandomMeals.Meals> data){
        this.data=data;
    }
    @Override
    public SlideAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.slider,null,false);
        return new SlideAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SlideAdapterViewHolder viewHolder, int position) {
        viewHolder.onBind(data.get(position).getStrMealThumb());
    }

    @Override
    public int getCount() {
        return data.size();
    }

    class SlideAdapterViewHolder extends SliderViewAdapter.ViewHolder{
        ImageView imageView;
        public SlideAdapterViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_slider);
        }
        void onBind(String img){
            Picasso.get().load(img).into(imageView);
        }
    }
}

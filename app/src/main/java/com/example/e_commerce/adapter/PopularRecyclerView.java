package com.example.e_commerce.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.R;
import com.example.e_commerce.interfaces.OnClick;
import com.example.e_commerce.pojo.Popular;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PopularRecyclerView extends RecyclerView.Adapter<PopularRecyclerView.PopularViewHolder> {
    ArrayList<Popular.Meals> data;
    OnClick listener;

    public PopularRecyclerView(ArrayList<Popular.Meals> data,OnClick listener) {

        this.data = data;
        this.listener=listener;
    }

    public void setData(ArrayList<Popular.Meals> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.popular,null,false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private Popular.Meals popular;
        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_popular);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(popular);
                }
            });
        }
        void onBind(Popular.Meals category){
            this.popular=category;
            Picasso.get().load(category.getStrMealThumb()).into(img);
        }
    }
}

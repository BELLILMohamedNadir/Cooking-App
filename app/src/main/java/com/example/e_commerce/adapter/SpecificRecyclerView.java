package com.example.e_commerce.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.R;
import com.example.e_commerce.interfaces.OnClick;
import com.example.e_commerce.pojo.SpecificCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SpecificRecyclerView extends RecyclerView.Adapter<SpecificRecyclerView.CategoryViewHolder>{

    ArrayList<SpecificCategory.Meals> data;
    OnClick listener;

    public SpecificRecyclerView(ArrayList<SpecificCategory.Meals> data,OnClick listener) {
        this.data = data;
        this.listener=listener;
    }

    public void setData(ArrayList<SpecificCategory.Meals> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.specific_category,null,false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.onBind(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class CategoryViewHolder  extends RecyclerView.ViewHolder{
        private ImageView img;
        private SpecificCategory.Meals category;
        TextView txt;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_specific_category);
            txt=itemView.findViewById(R.id.txt_specific_category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(category);
                }
            });
        }
        void onBind(SpecificCategory.Meals category){
            this.category=category;
            Picasso.get().load(category.getStrMealThumb()).into(img);
            txt.setText(category.getStrMeal());
        }
    }

}

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
import com.example.e_commerce.pojo.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRecyclerView extends RecyclerView.Adapter<CategoryRecyclerView.CategoryViewHolder> {
    ArrayList<Category.Categories> data;
    OnClick listener;

    public CategoryRecyclerView(ArrayList<Category.Categories> data,OnClick listener) {
        this.data = data;
        this.listener=listener;
    }

    public void setData(ArrayList<Category.Categories> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryRecyclerView.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category,null,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerView.CategoryViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CategoryViewHolder  extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView txt;
        Category.Categories category;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_category);
            txt=itemView.findViewById(R.id.txt_category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(category);
                }
            });
        }
        void onBind(Category.Categories category){
            this.category=category;
            Picasso.get().load(category.getStrCategoryThumb()).into(img);
            txt.setText(category.getStrCategory());
        }
    }
}

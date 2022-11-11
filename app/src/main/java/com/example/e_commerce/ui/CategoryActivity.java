package com.example.e_commerce.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.e_commerce.adapter.SpecificRecyclerView;
import com.example.e_commerce.databinding.ActivityCategoryBinding;
import com.example.e_commerce.pojo.Category;
import com.example.e_commerce.interfaces.OnClick;
import com.example.e_commerce.pojo.Popular;
import com.example.e_commerce.pojo.SpecificCategory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    String meal;
    ActivityCategoryBinding binding;
    ArrayList<SpecificCategory.Meals> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         meal=getIntent().getStringExtra("category");

         data=new ArrayList<>();
        Call<SpecificCategory> specificCategoryCall= MainActivity.api.getSpecificCategory(meal);
        SpecificRecyclerView recyclerView=new SpecificRecyclerView(data, new OnClick() {
            @Override
            public void onClick(Category.Categories category) {

            }

            @Override
            public void onClick(SpecificCategory.Meals category) {
                Intent intent=new Intent(CategoryActivity.this, DetailsActivity.class);
                intent.putExtra("id",category.getIdMeal());
                startActivity(intent);
            }

            @Override
            public void onClick(Popular.Meals category) {

            }
        });
        specificCategoryCall.enqueue(new Callback<SpecificCategory>() {
            @Override
            public void onResponse(Call<SpecificCategory> call, Response<SpecificCategory> response) {
                data.addAll(response.body().getMeals());
                recyclerView.setData(data);
                binding.rvSpecificCategory.setAdapter(recyclerView);
                binding.rvSpecificCategory.setLayoutManager(new GridLayoutManager(getBaseContext(),2));
            }

            @Override
            public void onFailure(Call<SpecificCategory> call, Throwable t) {

            }
        });

    }
}
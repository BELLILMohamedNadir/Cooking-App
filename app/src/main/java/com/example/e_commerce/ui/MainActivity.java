package com.example.e_commerce.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.e_commerce.interfaces.Api;
import com.example.e_commerce.adapter.CategoryRecyclerView;
import com.example.e_commerce.interfaces.OnClick;
import com.example.e_commerce.adapter.PopularRecyclerView;
import com.example.e_commerce.adapter.SlideAdapter;
import com.example.e_commerce.databinding.ActivityMainBinding;
import com.example.e_commerce.pojo.Category;
import com.example.e_commerce.pojo.Popular;
import com.example.e_commerce.pojo.RandomMeals;
import com.example.e_commerce.pojo.SpecificCategory;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
ArrayList<RandomMeals.Meals> data;
ArrayList<Category.Categories> dataCategories;
ArrayList<Popular.Meals> dataMeals;
    static Api api;
public static final String baseUrl="https://www.themealdb.com/api/json/v1/1/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.facebookShimmer.startShimmer();
        data=new ArrayList<>();
        dataCategories=new ArrayList<>();
        dataMeals=new ArrayList<>();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
         api=retrofit.create(Api.class);
        Call<RandomMeals> call= api.getRandomMeal();
        Call<Category> categoryCall=api.getCategory();

        Call<Popular> popularCall=api.getPopular();
        call.enqueue(new Callback<RandomMeals>() {
            @Override
            public void onResponse(Call<RandomMeals> call, Response<RandomMeals> response) {
                data.addAll(response.body().getMeals());
                SlideAdapter slideAdapter=new SlideAdapter(data);
                binding.slidImg.setSliderAdapter(slideAdapter);
                binding.slidImg.setIndicatorAnimation(IndicatorAnimationType.WORM);
                binding.slidImg.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
                binding.slidImg.startAutoCycle();
                binding.facebookShimmer.stopShimmer();
                binding.facebookShimmer.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<RandomMeals> call, Throwable t) {

            }
        });
        CategoryRecyclerView recyclerView=new CategoryRecyclerView(dataCategories, new OnClick() {
            @Override
            public void onClick(Category.Categories category) {
                Intent intent=new Intent(MainActivity.this, CategoryActivity.class);
                intent.putExtra("category",category.getStrCategory());
                startActivity(intent);
            }

            @Override
            public void onClick(SpecificCategory.Meals category) {

            }

            @Override
            public void onClick(Popular.Meals category) {

            }
        });
        categoryCall.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                for (int i=0;i<response.body().getCategories().size();i++){
                    if (response.body().getCategories().get(i).getStrCategory().equals("Pork"))
                        continue;
                    else
                        dataCategories.add(response.body().getCategories().get(i));
                }
                recyclerView.setData(dataCategories);
                binding.rvCategory.setAdapter(recyclerView);
                binding.rvCategory.setLayoutManager(new GridLayoutManager(getBaseContext(),3));

            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
        PopularRecyclerView recyclerView1=new PopularRecyclerView(dataMeals, new OnClick() {
            @Override
            public void onClick(Category.Categories category) {

            }

            @Override
            public void onClick(SpecificCategory.Meals category) {

            }

            @Override
            public void onClick(Popular.Meals category) {
                Intent intent=new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("id",category.getIdMeal());
                startActivity(intent);
            }
        });
        popularCall.enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(Call<Popular> call, Response<Popular> response) {
                dataMeals.addAll(response.body().getMeals());
                recyclerView1.setData(dataMeals);
                binding.rvPopular.setAdapter(recyclerView1);
                binding.rvPopular.setLayoutManager(new LinearLayoutManager(getBaseContext(),RecyclerView.HORIZONTAL,false));


            }

            @Override
            public void onFailure(Call<Popular> call, Throwable t) {

            }
        });




    }

}
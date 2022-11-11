package com.example.e_commerce.interfaces;

import com.example.e_commerce.pojo.Area;
import com.example.e_commerce.pojo.Category;
import com.example.e_commerce.pojo.DetailsMeal;
import com.example.e_commerce.pojo.Popular;
import com.example.e_commerce.pojo.RandomMeals;
import com.example.e_commerce.pojo.SpecificCategory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    @GET("random.php")
    Call<RandomMeals> getRandomMeal();
    @GET("categories.php")
    Call<Category> getCategory();
    @GET("search.php?f=a")
    Call<Popular> getPopular();
    @GET("search.php")
    Call<Popular> getSpecialMeal(@Query("s") String meal);
    @GET("filter.php")
    Call<SpecificCategory> getSpecificCategory(@Query(("c") )String category);
    @GET("filter.php")
    Call<Area> getCategoryByArea(@Query(("a") )String category);
    @GET("lookup.php")
    Call<DetailsMeal> getDetailsCategory(@Query(("i") )String details);

}

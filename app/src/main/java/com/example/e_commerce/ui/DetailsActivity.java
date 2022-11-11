package com.example.e_commerce.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.e_commerce.databinding.ActivityDetailsBinding;
import com.example.e_commerce.pojo.DetailsMeal;
import com.example.e_commerce.ui.MainActivity;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
  ActivityDetailsBinding binding;
  String id,url;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        id=getIntent().getStringExtra("id");
        sp= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        edit=sp.edit();
        Call<DetailsMeal> detailsMealCall= MainActivity.api.getDetailsCategory(id);
        detailsMealCall.enqueue(new Callback<DetailsMeal>() {
            @Override
            public void onResponse(Call<DetailsMeal> call, Response<DetailsMeal> response) {
                Picasso.get().load(response.body().getMeals().get(0).getStrMealThumb()).into(binding.imgDetails);
                binding.txtDescription.setText(response.body().getMeals().get(0).getStrInstructions());
                edit.putString("url",response.body().getMeals().get(0).getStrYoutube());
                edit.apply();

            }

            @Override
            public void onFailure(Call<DetailsMeal> call, Throwable t) {

            }
        });
        url=sp.getString("url","");
        binding.fotYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });


    }


}
package com.example.e_commerce.pojo;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

public class Area implements Serializable {
  private List<? extends Meals> meals;

  public List<? extends Meals> getMeals() {
    return this.meals;
  }

  public void setMeals(List<? extends Meals> meals) {
    this.meals = meals;
  }

  public static class Meals implements Serializable {
    private String strMealThumb;

    private String idMeal;

    private String strMeal;

    public String getStrMealThumb() {
      return this.strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
      this.strMealThumb = strMealThumb;
    }

    public String getIdMeal() {
      return this.idMeal;
    }

    public void setIdMeal(String idMeal) {
      this.idMeal = idMeal;
    }

    public String getStrMeal() {
      return this.strMeal;
    }

    public void setStrMeal(String strMeal) {
      this.strMeal = strMeal;
    }
  }
}

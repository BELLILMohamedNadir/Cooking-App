package com.example.e_commerce.pojo;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

public class Category implements Serializable {
  private List<? extends Categories> categories;

  public List<? extends Categories> getCategories() {
    return this.categories;
  }

  public void setCategories(List<? extends Categories> categories) {
    this.categories = categories;
  }

  public static class Categories implements Serializable {
    private String strCategory;

    private String strCategoryDescription;

    private String idCategory;

    private String strCategoryThumb;

    public String getStrCategory() {
      return this.strCategory;
    }

    public void setStrCategory(String strCategory) {
      this.strCategory = strCategory;
    }

    public String getStrCategoryDescription() {
      return this.strCategoryDescription;
    }

    public void setStrCategoryDescription(String strCategoryDescription) {
      this.strCategoryDescription = strCategoryDescription;
    }

    public String getIdCategory() {
      return this.idCategory;
    }

    public void setIdCategory(String idCategory) {
      this.idCategory = idCategory;
    }

    public String getStrCategoryThumb() {
      return this.strCategoryThumb;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
      this.strCategoryThumb = strCategoryThumb;
    }
  }
}

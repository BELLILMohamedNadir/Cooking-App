package com.example.e_commerce.interfaces;

import com.example.e_commerce.pojo.Category;
import com.example.e_commerce.pojo.Popular;
import com.example.e_commerce.pojo.SpecificCategory;

public interface OnClick {
    void onClick(Category.Categories category);
    void onClick(SpecificCategory.Meals category);
    void onClick(Popular.Meals category);
}

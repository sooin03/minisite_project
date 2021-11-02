package com.example.minisite_project.admin.category.model;


import lombok.Data;

@Data
public class CategoryInput {
    
    long id;
    String categoryName;
    int sortValue;
    boolean usingYn;
    
}

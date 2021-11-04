package com.example.minisite_project.webtoon.model;


import lombok.Data;

@Data
public class WebtoonInput {
    long id;
    long categoryId;
    String subject;
    String author;
    String keyword;
    String summary;
    String contents;
    long price;
    long salePrice;
    String saleEndDtText;

    //삭제를 위한
    String idList;


    //ADD
    String filename;
    String urlFilename;


}

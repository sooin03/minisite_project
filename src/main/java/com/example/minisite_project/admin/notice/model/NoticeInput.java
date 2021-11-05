package com.example.minisite_project.admin.notice.model;

import lombok.Data;


@Data
public class NoticeInput {

     long id;

     String writer;
     String title;
     String contents;


    //삭제를 위한
    String idList;


}

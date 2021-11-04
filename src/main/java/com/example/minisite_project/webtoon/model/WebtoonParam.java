package com.example.minisite_project.webtoon.model;

import com.example.minisite_project.common.CommonParam;
import lombok.Data;

@Data
public class WebtoonParam extends CommonParam {

    long id;//webtoon.id
    long categoryId;

}

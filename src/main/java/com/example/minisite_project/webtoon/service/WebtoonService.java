package com.example.minisite_project.webtoon.service;

import com.example.minisite_project.webtoon.dto.WebtoonDto;
import com.example.minisite_project.webtoon.model.WebtoonInput;
import com.example.minisite_project.webtoon.model.WebtoonParam;

import java.util.List;

public interface WebtoonService {

    /**
     * 웹툰 등록
     */
    boolean add(WebtoonInput parameter);

    /**
     * 웹툰 정보 수정
     */
    boolean set(WebtoonInput parameter);

    /**
     * 웹툰 목록
     */
    List<WebtoonDto> list(WebtoonParam parameter);

    /**
     * 단건 웹툰 상세정보
     */
    WebtoonDto getById(long id);

    /**
     * 웹툰 내용 삭제
     */
    boolean del(String idList);

    /**
     * 프론트 웹툰 목록
     */
    List<WebtoonDto> frontList(WebtoonParam parameter);

    /**
     * 프론트 웹툰 상세 정보
     */
    WebtoonDto frontDetail(long id);


    /**
     * 전체 웹툰 목록
     */
    //List<CourseDto> listAll();


}

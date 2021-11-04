package com.example.minisite_project.webtoon.service;

import com.example.minisite_project.webtoon.dto.WebtoonDto;
import com.example.minisite_project.webtoon.model.WebtoonInput;
import com.example.minisite_project.webtoon.model.WebtoonParam;

import java.util.List;

public interface WebtoonService {

    /**
     * 강좌 등록
     */
    boolean add(WebtoonInput parameter);

    /**
     * 강좌 정보수정
     */
   // boolean set(CourseInput parameter);

    /**
     * 강좌 목록
     */
    List<WebtoonDto> list(WebtoonParam parameter);

    /**
     * 강좌 상세정보
     */
  //  CourseDto getById(long id);

    /**
     * 강좌 내용 삭제
     */
 //   boolean del(String idList);

    /**
     * 프론트 강좌 목록
     */
  //  List<CourseDto> frontList(CourseParam parameter);

    /**
     * 프론트 강좌 상세 정보
     */
   // CourseDto frontDetail(long id);

    /**
     * 수강신청
     */
   // ServiceResult req(TakeCourseInput parameter);

    /**
     * 전체 강좌 목록
     */
    //List<CourseDto> listAll();


}

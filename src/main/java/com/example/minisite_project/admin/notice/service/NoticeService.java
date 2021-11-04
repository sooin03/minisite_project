package com.example.minisite_project.admin.notice.service;

import com.example.minisite_project.admin.notice.dto.NoticeDto;
import com.example.minisite_project.admin.notice.model.NoticeInput;
import com.example.minisite_project.admin.notice.model.NoticeParam;

import java.util.List;

public interface NoticeService {
    /**
     * 강좌 등록
     */
    boolean add(NoticeInput parameter);

    /**
     * 공지사항 정보수정
     */
    boolean set(NoticeInput parameter);

    /**
     * 공지사항 목록
     */
    List<NoticeDto> list(NoticeParam parameter);

    /**
     * 공지사항 상세정보
     */
    NoticeDto getById(long id);

    /**
     * 공지사항 내용 삭제
     */
    //boolean del(String idList);

    /**
     * 프론트 공지사항 목록
     */
    //List<CourseDto> frontList(CourseParam parameter);

    /**
     * 프론트 공지사항 상세 정보
     */
    //CourseDto frontDetail(long id);

    /**
     * 전체 공지사항 목록
     */
   // List<CourseDto> listAll();
}

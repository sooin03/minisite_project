package com.example.minisite_project.admin.notice.dto;

import com.example.minisite_project.admin.notice.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NoticeDto {

     Long id;

     Long writer;
     String title;
     String contents;

     LocalDateTime regDt;//등록일(추가날짜)
     LocalDateTime udtDt;//수정일(수정날짜)


    //추가컬럼
    long totalCount;
    long seq;
    
    public static NoticeDto of(Notice notice) {
        return NoticeDto.builder()
                .id(notice.getId())
                .writer(notice.getWriter())
                .contents(notice.getContents())
                .regDt(notice.getRegDt())
                .udtDt(notice.getUdtDt())
                .build();
    }
    
    public static List<NoticeDto> of(List<Notice> notices) {

        if (notices == null) {
            return null;
        }

        List<NoticeDto> courseList = new ArrayList<>();
        for(Notice x : notices) {
            courseList.add(NoticeDto.of(x));
        }
        return courseList;

    }

    
}


















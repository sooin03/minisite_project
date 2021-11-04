package com.example.minisite_project.admin.notice.mapper;

import com.example.minisite_project.admin.notice.dto.NoticeDto;
import com.example.minisite_project.admin.notice.model.NoticeParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    long selectListCount(NoticeParam parameter);
    List<NoticeDto> selectList(NoticeParam parameter);
}

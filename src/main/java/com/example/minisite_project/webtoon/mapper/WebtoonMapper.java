package com.example.minisite_project.webtoon.mapper;

import com.example.minisite_project.webtoon.dto.WebtoonDto;
import com.example.minisite_project.webtoon.model.WebtoonParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WebtoonMapper {
    long selectListCount(WebtoonParam parameter);
    List<WebtoonDto> selectList(WebtoonParam parameter);
}

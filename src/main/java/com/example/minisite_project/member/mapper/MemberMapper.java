package com.example.minisite_project.member.mapper;


import com.example.minisite_project.admin.member.model.MemberParam;
import com.example.minisite_project.admin.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    long selectListCount(MemberParam parameter);
    List<MemberDto> selectList(MemberParam parameter);

}

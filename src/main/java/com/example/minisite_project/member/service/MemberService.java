package com.example.minisite_project.member.service;

import com.example.minisite_project.admin.member.model.MemberParam;
import com.example.minisite_project.admin.dto.MemberDto;
import com.example.minisite_project.member.model.MemberInput;
import com.example.minisite_project.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    boolean register(MemberInput parameter);

    /**
     * uuid에 해당하는 계정을 활성화 함.
     */
    boolean emailAuth(String uuid);

    /**
     * 입력한 이메일로 비밀번호 초기화 정보를 전송
     */
    boolean sendResetPassword(ResetPasswordInput parameter);

    /**
     * 입력한 받은 uuid에 대해서 password로 초기화 함.
     */
    boolean resetPassword(String id, String password);

    /**
     * 입력한 받은 uuid이 유효한지 확인함.
     */
    boolean checkResetPassword(String uuid);

    /**
     * 회원 목록 리턴(관리자에서만 사용 가능)
     */
    List<MemberDto> list(MemberParam parameter);

    /**
     * 회원 상세 정보
     */
  //  MemberDto detail(String userId);

    /**
     * 회원 상태 변경
     */
   // boolean updateStatus(String userId, String userStatus);

    /**
     * 회원 비밀번호 초기화
     */
   // boolean updatePassword(String userId, String password);

    /**
     * 회원정보 수정
     */
   // ServiceResult updateMember(MemberInput parameter);

    /**
     * 회원 정보 페이지내 비밀번호 변경 기능
     */
    //ServiceResult updateMemberPassword(MemberInput parameter);

    /**
     * 회원을 탈퇴시켜 주는 로직
     */
    //ServiceResult withdraw(String userId, String password);


}

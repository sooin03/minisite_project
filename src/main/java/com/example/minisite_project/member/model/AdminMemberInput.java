package com.example.minisite_project.member.model;

import lombok.Data;

@Data
public class AdminMemberInput {
    String userId;
    String userStatus;
    String password;
}

package com.example.minisite_project.member.exception;

public class MemberStopUserException extends RuntimeException {
    public MemberStopUserException(String error) {
        super(error);
    }
}

package com.example.minisite_project.main.controller;

import com.example.minisite_project.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MailComponents mailComponents;

    @RequestMapping("/")
    public String index() {

//        String email = "sooin03@naver.com";
//        String subject = " 안녕하세요. 미니웹툰사이트 입니다. ";
//        String text = "<p>안녕하세요.</p><p>반갑습니다.</p>";
//
//        mailComponents.sendMail(email, subject, text);
//

        return "index";
    }

    @RequestMapping("/error/denied")
    String errorDenied() {

        return "error/denied";
    }



}

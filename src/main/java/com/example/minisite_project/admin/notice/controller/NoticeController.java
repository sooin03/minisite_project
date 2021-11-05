package com.example.minisite_project.admin.notice.controller;

import com.example.minisite_project.admin.category.dto.CategoryDto;
import com.example.minisite_project.admin.category.service.CategoryService;
import com.example.minisite_project.admin.notice.dto.NoticeDto;
import com.example.minisite_project.admin.notice.model.NoticeInput;
import com.example.minisite_project.admin.notice.model.NoticeParam;
import com.example.minisite_project.admin.notice.service.NoticeService;
import com.example.minisite_project.common.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class NoticeController extends BaseController {

    private final NoticeService noticeService;
    private final CategoryService categoryService;

    @GetMapping("/notice")
    public String course(Model model
            , NoticeParam parameter) {

        List<NoticeDto> list = noticeService.frontList(parameter);
        model.addAttribute("list", list);

        return "notice/index";
    }

    @GetMapping("/notice/{id}")
    public String noticeDetail(Model model
            , NoticeParam parameter) {

        NoticeDto detail = noticeService.frontDetail(parameter.getId());
        model.addAttribute("detail", detail);

        return "notice/detail";
    }


}

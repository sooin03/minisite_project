package com.example.minisite_project.webtoon.controller;


import com.example.minisite_project.admin.category.dto.CategoryDto;
import com.example.minisite_project.admin.category.service.CategoryService;
import com.example.minisite_project.common.BaseController;
import com.example.minisite_project.webtoon.dto.WebtoonDto;
import com.example.minisite_project.webtoon.model.WebtoonParam;
import com.example.minisite_project.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class WebtoonController extends BaseController {
    
    private final WebtoonService webtoonService;
    private final CategoryService categoryService;
    
    @GetMapping("/webtoon")
    public String course(Model model, WebtoonParam parameter) {
        
        List<WebtoonDto> list = webtoonService.frontList(parameter);
        model.addAttribute("list", list);
        
        int webtoonTotalCount = 0;
        List<CategoryDto> categoryList = categoryService.frontList(CategoryDto.builder().build());
        if (categoryList != null) {
            for(CategoryDto x : categoryList) {
                webtoonTotalCount += x.getWebtoonCount();
            }
        }
        
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("webtoonTotalCount", webtoonTotalCount);
        
        return "webtoon/index";
    }
    
    @GetMapping("/webtoon/{id}")
    public String webtoonDetail(Model model
            , WebtoonParam parameter) {

        WebtoonDto detail = webtoonService.frontDetail(parameter.getId());
        model.addAttribute("detail", detail);

        return "webtoon/detail";
    }

    
    
    
    
    
}

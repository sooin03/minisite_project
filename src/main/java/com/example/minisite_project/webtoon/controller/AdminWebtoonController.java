package com.example.minisite_project.webtoon.controller;


import com.example.minisite_project.admin.category.service.CategoryService;
import com.example.minisite_project.common.BaseController;
import com.example.minisite_project.webtoon.dto.WebtoonDto;
import com.example.minisite_project.webtoon.model.WebtoonInput;
import com.example.minisite_project.webtoon.model.WebtoonParam;
import com.example.minisite_project.webtoon.service.WebtoonService;
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
public class AdminWebtoonController extends BaseController {
    
    private final WebtoonService webtoonService;
    private final CategoryService categoryService;
    
    @GetMapping("/admin/webtoon/list.do")
    public String list(Model model, WebtoonParam parameter) {
        
        parameter.init();
        List<WebtoonDto> webtoonList = webtoonService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(webtoonList)) {
            totalCount = webtoonList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", webtoonList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);
        
        return "admin/webtoon/list";
    }
    
    @GetMapping(value = {"/admin/webtoon/add.do", "/admin/webtoon/edit.do"})
    public String add(Model model, HttpServletRequest request
            , WebtoonInput parameter) {

        //카테고리 정보를 내려줘야 함.
        model.addAttribute("category", categoryService.list());

        boolean editMode = request.getRequestURI().contains("/edit.do");
        WebtoonDto detail = new WebtoonDto();

        if (editMode) {
            long id = parameter.getId();
            WebtoonDto existWebtoon = webtoonService.getById(id);
            if (existWebtoon == null) {
                // error 처리
                model.addAttribute("message", "웹툰정보가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existWebtoon;
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);

        return "admin/webtoon/add";
    }
//
//
//
//
//    private String[] getNewSaveFile(String baseLocalPath, String baseUrlPath, String originalFilename) {
//
//        LocalDate now = LocalDate.now();
//
//        String[] dirs = {
//                String.format("%s/%d/", baseLocalPath,now.getYear()),
//                String.format("%s/%d/%02d/", baseLocalPath, now.getYear(),now.getMonthValue()),
//                String.format("%s/%d/%02d/%02d/", baseLocalPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth())};
//
//        String urlDir = String.format("%s/%d/%02d/%02d/", baseUrlPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth());
//
//        for(String dir : dirs) {
//            File file = new File(dir);
//            if (!file.isDirectory()) {
//                file.mkdir();
//            }
//        }
//
//        String fileExtension = "";
//        if (originalFilename != null) {
//            int dotPos = originalFilename.lastIndexOf(".");
//            if (dotPos > -1) {
//                fileExtension = originalFilename.substring(dotPos + 1);
//            }
//        }
//
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//        String newFilename = String.format("%s%s", dirs[2], uuid);
//        String newUrlFilename = String.format("%s%s", urlDir, uuid);
//        if (fileExtension.length() > 0) {
//            newFilename += "." + fileExtension;
//            newUrlFilename += "." + fileExtension;
//        }
//
//        return new String[]{newFilename, newUrlFilename};
//    }
//
    @PostMapping(value = {"/admin/webtoon/add.do", "/admin/webtoon/edit.do"})
    public String addSubmit(Model model, HttpServletRequest request
                            , MultipartFile file
            , WebtoonInput parameter) {

//        String saveFilename = "";
//        String urlFilename = "";
//
//        if (file != null) {
//            String originalFilename = file.getOriginalFilename();
//
//            String baseLocalPath = "/Users/kyutaepark/Documents/sources/zerobase/fastlms/files";
//            String baseUrlPath = "/files";
//
//            String[] arrFilename = getNewSaveFile(baseLocalPath, baseUrlPath, originalFilename);
//
//            saveFilename = arrFilename[0];
//            urlFilename = arrFilename[1];
//
//            try {
//                File newFile = new File(saveFilename);
//                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
//            } catch (IOException e) {
//                log.info("############################ - 1");
//                log.info(e.getMessage());
//            }
//        }
//
//        parameter.setFilename(saveFilename);
//        parameter.setUrlFilename(urlFilename);
//
        boolean editMode = request.getRequestURI().contains("/edit.do");

        if (editMode) {
            long id = parameter.getId();
            WebtoonDto existCourse = webtoonService.getById(id);
            if (existCourse == null) {
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "common/error";
            }

            boolean result = webtoonService.set(parameter);

        } else {
            boolean result = webtoonService.add(parameter);
        }

        return "redirect:/admin/webtoon/list.do";
    }

    @PostMapping("/admin/webtoon/delete.do")
    public String del(Model model, HttpServletRequest request
            , WebtoonInput parameter) {

        boolean result = webtoonService.del(parameter.getIdList());

        return "redirect:/admin/webtoon/list.do";
    }

    
}

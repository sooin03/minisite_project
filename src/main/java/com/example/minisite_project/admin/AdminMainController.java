package com.example.minisite_project.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController {
    
    
    @GetMapping("/admin/main.do")
    public String main() {
        
        return "admin/main";
    }
    
    
    
}

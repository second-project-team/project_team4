package com.korea.project2_team4.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "main_form";
    }


    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/test")
    @ResponseBody
    public MultipartFile test(@RequestParam(value = "test", required = false) MultipartFile test) {

        System.out.println("111");
        System.out.println(test);

        return test;
    }

}

package com.korea.project2_team4.Controller;

import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;

@Controller
public class MemberController {

    @GetMapping("/signup")
    public String signup() {
        return "signup_form";
    }


}

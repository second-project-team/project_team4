package com.korea.project2_team4.Controller;

import com.korea.project2_team4.Model.Form.MemberCreateForm;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;

@Controller
@RequestMapping("/member")
public class MemberController {


    public String signup(MemberCreateForm memberCreateForm) {

        return "signup_form";
    }


}

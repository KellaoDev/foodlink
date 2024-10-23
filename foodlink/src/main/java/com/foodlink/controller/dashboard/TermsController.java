package com.foodlink.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/terms-of-use")
public class TermsController {

    @GetMapping
    public String getTermsOfUse() {
        return "/terms/terms";
    }

}

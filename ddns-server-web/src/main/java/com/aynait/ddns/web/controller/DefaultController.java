package com.aynait.ddns.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tianya on 2017/10/10.
 */
@Controller
public class DefaultController {

    @RequestMapping(value = "")
    public String index() {
        return "redirect:/index.html";
    }
}

package com.university.project.agendat2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 06/11/16
 */
@Controller
public class ErrorController {
    @RequestMapping(value = "/error")
    public String getRegisterUser(Model model) {
        return "error";
    }
}

package com.university.project.agendat2.controller;

import com.university.project.agendat2.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 05/11/16
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request){
        request.getSession().setAttribute("user", null);
        return new ModelAndView("redirect:/", "command", new User());
    }
}

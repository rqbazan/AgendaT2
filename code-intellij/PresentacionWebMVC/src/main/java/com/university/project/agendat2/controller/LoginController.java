package com.university.project.agendat2.controller;

import com.university.project.agendat2.business.ContactBL;
import com.university.project.agendat2.business.UserBL;
import com.university.project.agendat2.business.util.ApplicationException;
import com.university.project.agendat2.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/*
 * Created by Ricardo Alexis Quiroz Bazan on 04/11/16.
 */

@Controller
public class LoginController {
    @RequestMapping(value = "/")
    public ModelAndView getLogin(){
        return new ModelAndView("login", "command", new User());
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView postLogin(@ModelAttribute("SpringWeb")User user, ModelMap model, HttpServletRequest request){
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            User validUser = UserBL.getInstance().authUser(username, password);
            request.getSession().setAttribute("user", validUser);
            return new ModelAndView("home", "contacts", ContactBL.getInstance().getAllByIdUser(validUser.getId()));
        } catch (ApplicationException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("command", new User());
            return new ModelAndView("login");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("error");
        }
    }
}

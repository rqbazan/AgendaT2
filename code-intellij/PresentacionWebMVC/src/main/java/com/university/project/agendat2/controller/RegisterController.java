package com.university.project.agendat2.controller;

import com.university.project.agendat2.business.ContactBL;
import com.university.project.agendat2.business.UserBL;
import com.university.project.agendat2.business.util.ApplicationException;
import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.model.Person;
import com.university.project.agendat2.model.SexType;
import com.university.project.agendat2.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 05/11/16
 */
@Controller
public class RegisterController {
    @RequestMapping(value = "/registeruser", method = RequestMethod.GET)
    public ModelAndView getRegisterUser(){
        User user = new User();
        user.setPerson(new Person());
        user.getPerson().setSex(SexType.MALE.asChar());
        return new ModelAndView("register_user", "command", user);
    }

    @RequestMapping(value = "/registercontact", method = RequestMethod.GET)
    public ModelAndView getRegisterContact(ModelMap model,
                                           HttpServletRequest request,
                                           RedirectAttributes reAttrs){
        try {
            User sessionUser = (User) request.getSession().getAttribute("user");
            if (sessionUser == null)
                throw new ApplicationException("No tiene permiso para estar aqui");
            Contact contact = new Contact();
            contact.setSex(SexType.MALE.asChar());
            return new ModelAndView("register_contact", "command", contact);
        }catch (Exception e){
            reAttrs.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:error");
        }
    }

    @RequestMapping(value = "/registeruser", method = RequestMethod.POST)
    public ModelAndView postRegisterUser(@ModelAttribute("SpringWeb")User user,
                                         ModelMap model,
                                         RedirectAttributes reAttrs){
        try {
            UserBL.getInstance().insert(user);
            return new ModelAndView("redirect:/");
        }catch (ApplicationException e){
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("register_user", "command", user);
        }catch (Exception e){
            reAttrs.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:error");
        }
    }

    @RequestMapping(value = "/registercontact", method = RequestMethod.POST)
    public ModelAndView postRegisterContact(@ModelAttribute("SpringWeb")Contact contact,
                                            ModelMap model,
                                            HttpServletRequest request,
                                            RedirectAttributes reAttrs){
        try {
            User sessionUser = (User) request.getSession().getAttribute("user");
            if (sessionUser == null)
                throw new ApplicationException("No tiene permiso para estar aqui");
            ContactBL.getInstance().insert(sessionUser.getId(), contact);
            return new ModelAndView("redirect:home");
        }catch (ApplicationException e){
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("register_contact", "command", contact);
        }catch (Exception e){
            reAttrs.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:error");
        }
    }
}


package com.university.project.agendat2.controller;

import com.university.project.agendat2.business.ContactBL;
import com.university.project.agendat2.business.util.ApplicationException;
import com.university.project.agendat2.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 04/11/16
 */
@Controller
public class ContactController {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView listContacts(ModelMap model, HttpServletRequest request, RedirectAttributes reAttrs){
        try {
            User sessionUser = (User) request.getSession().getAttribute("user");
            if(sessionUser == null)
                throw new ApplicationException("No tiene permiso para estar aqui");
            return new ModelAndView("home", "contacts", ContactBL.getInstance().getAllByIdUser(sessionUser.getId()));
        } catch (ApplicationException e) {
            reAttrs.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:error");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("error");
        }
    }
}

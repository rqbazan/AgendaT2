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
    public String getRegisterUser(){
        return "register_user";
    }

    @RequestMapping(value = "/registercontact", method = RequestMethod.GET)
    public String getRegisterContact(ModelMap model, HttpServletRequest request, RedirectAttributes reAttrs){
        try {
            User sessionUser = (User) request.getSession().getAttribute("user");
            if (sessionUser == null)
                throw new ApplicationException("No tiene permiso para estar aqui");
            return "register_contact";
        }catch (ApplicationException e){
            reAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:error";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/registeruser", method = RequestMethod.POST)
    public ModelAndView postRegisterUser(ModelMap model, HttpServletRequest request){
        try {
            String password = request.getParameter("password");
            String confimrPassword = request.getParameter("confirmPassword");
            if (!password.trim().equals(confimrPassword.trim()))
                throw new ApplicationException("Las contraseñas no coinciden");
            if (password.trim().isEmpty())
                throw new ApplicationException("Las contraseña es un campo obligatorio");

            Person person = new Person();
            person.setName(request.getParameter("name"));
            person.setLastName(request.getParameter("lastName"));
            person.setCellphoneNumber(request.getParameter("cellphoneNumber"));
            person.setEmail(request.getParameter("email"));
            person.setDni(request.getParameter("dni"));

            String sex = request.getParameter("sex");
            if (sex.equals("male")){
                person.setSex(SexType.MALE.asChar());
            }else if (sex.equals("female")){
                person.setSex(SexType.FEMALE.asChar());
            }

            User user = new User();
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setPerson(person);

            UserBL.getInstance().insert(user);
            return new ModelAndView("redirect:/", "command", new User());
        }catch (ApplicationException e){
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("register_user");
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/registercontact", method = RequestMethod.POST)
    public String postRegisterContact(ModelMap model, HttpServletRequest request, RedirectAttributes reAttrs){
        try {
            User sessionUser = (User) request.getSession().getAttribute("user");
            if (sessionUser == null)
                throw new ApplicationException("No tiene permiso para estar aqui");

            Contact contact = new Contact();
            contact.setName(request.getParameter("name"));
            contact.setLastName(request.getParameter("lastName"));
            contact.setCellphoneNumber(request.getParameter("cellphoneNumber"));
            contact.setEmail(request.getParameter("email"));
            contact.setDni(request.getParameter("dni"));

            String sex = request.getParameter("sex");
            if (sex.equals("male")){
                contact.setSex(SexType.MALE.asChar());
            }else if (sex.equals("female")){
                contact.setSex(SexType.FEMALE.asChar());
            }

            ContactBL.getInstance().insert(sessionUser.getId(), contact);
            return "redirect:home";
        }catch (ApplicationException e){
            reAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:error";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}


package com.university.project.agendat2.rest;

import com.university.project.agendat2.business.ContactBL;
import com.university.project.agendat2.business.UserBL;
import com.university.project.agendat2.business.util.ApplicationException;
import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.model.User;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 05/11/16
 */
@RestController
public class RestJsonController {
    @RequestMapping(value = "/restauthuser",
            produces = "application/JSON; charset=UTF-8",
            method = RequestMethod.GET)
    public User authUser(@RequestParam("username") String username,
                         @RequestParam("password") String password) throws ApplicationException{
        try {
            return UserBL.getInstance().authUser(username, password);
        }catch (ApplicationException e){
            e.printStackTrace();
            throw e;
        }
    }

    @RequestMapping(value = "/restlistcontacts",
            produces = "application/JSON; charset=UTF-8",
            method = RequestMethod.GET)
    public List<Contact> listContacts(@RequestParam("idUser") String idUser) throws ApplicationException{
        try {
            return ContactBL.getInstance().getAllByIdUser(Integer.parseInt(idUser));
        } catch (ApplicationException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @RequestMapping(value = "/restinsertuser",
            produces = "application/JSON; charset=UTF-8",
            consumes = "application/JSON; charset=UTF-8",
            method = RequestMethod.POST)
    public int insertUser(@RequestBody User user) throws ApplicationException{
        try {
            return UserBL.getInstance().insert(user);
        }catch (ApplicationException e){
            e.printStackTrace();
            throw e;
        }
    }

    @RequestMapping(value = "/restinsertcontact",
            produces="application/JSON; charset=UTF-8",
            consumes = "application/JSON; charset=UTF-8",
            method = RequestMethod.POST)
    public int insertContact(@RequestParam("idUser") int idUser, @RequestBody Contact contact) throws ApplicationException{
        try {
            return ContactBL.getInstance().insert(idUser, contact);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}

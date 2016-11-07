package com.university.project.agendat2.business.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 06/11/16
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Usuario  y/o contraseña incorrectos")
public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException() {
        super("Usuario  y/o contraseña incorrectos");
    }
}

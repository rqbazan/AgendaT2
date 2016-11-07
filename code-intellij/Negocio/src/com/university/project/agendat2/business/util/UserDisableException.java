package com.university.project.agendat2.business.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 06/11/16
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "El usuario ya no esta activo")
public class UserDisableException extends ApplicationException{
    public UserDisableException() {
        super("El usuario ya no esta activo");
    }
}

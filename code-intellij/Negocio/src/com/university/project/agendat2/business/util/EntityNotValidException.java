package com.university.project.agendat2.business.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 10/11/16
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Registro no válido todos los campos son requeridos")
public class EntityNotValidException extends ApplicationException {
    public EntityNotValidException() {
        super("Registro no válido todos los campos son requeridos");
    }
}

package com.university.project.agendat2.business.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 06/11/16
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "No se puedo guardar en la base de datos")
public class InsertTransactionException extends ApplicationException{
    public InsertTransactionException() {
        super("No se puedo guardar en la base de datos");
    }
}

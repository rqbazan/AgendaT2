package com.university.project.agendat2.business.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Ricardo Alexis Quiroz Bazan on 10/11/16
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "No se puedo obtener de la base de datos")
public class SelectTransactionException extends ApplicationException{
    public SelectTransactionException() {
        super("No se puedo obtener de la base de datos");
    }
}

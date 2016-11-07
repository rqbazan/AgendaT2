package com.university.project.agendat2.model;

import java.io.Serializable;

/**
 * Created by Ricardo Alexis Quiroz Bazan  on 03/11/16.
 */

public enum SexType implements Serializable{
    MALE('M'),
    FEMALE('F');

    private final Character asChar;

    SexType(char asChar){
        this.asChar = asChar;
    }

    public char asChar() {
        return asChar;
    }
}

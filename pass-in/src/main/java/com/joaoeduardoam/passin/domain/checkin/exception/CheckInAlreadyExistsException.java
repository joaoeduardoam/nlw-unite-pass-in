package com.joaoeduardoam.passin.domain.checkin.exception;

public class CheckInAlreadyExistsException extends RuntimeException{

    public CheckInAlreadyExistsException(String message){
        super(message);
    }

}

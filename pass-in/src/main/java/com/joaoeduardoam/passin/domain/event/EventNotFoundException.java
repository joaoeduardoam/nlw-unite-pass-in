package com.joaoeduardoam.passin.domain.event;

public class EventNotFoundException extends RuntimeException{

    public EventNotFoundException (String message){
        super(message);
    }
}

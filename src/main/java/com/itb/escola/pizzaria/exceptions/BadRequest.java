package com.itb.escola.pizzaria.exceptions;



public class BadRequest extends RuntimeException{

    public BadRequest(String message){
         super(message);
    }
}

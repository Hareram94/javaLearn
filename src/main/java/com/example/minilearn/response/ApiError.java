package com.example.minilearn.response;

import java.util.Map;

public class ApiError {


    private boolean status;
    private String message;
    private Map<String,String> errors;

    public ApiError(
            boolean status,
            String message,
            Map<String,String> errors
    ){

        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setErrors(Map<String,String> errors){
        this.errors = errors;
    }

    public Map<String,String> getErrors(){
        return errors;
    }

}

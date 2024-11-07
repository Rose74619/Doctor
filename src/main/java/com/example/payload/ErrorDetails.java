package com.example.payload;

import java.util.Date;

public class ErrorDetails {
      private String Message;
      private Date date;

    public ErrorDetails(String message, Date date) {
        Message = message;
        this.date = date;
    }

    public String getMessage() {
        return Message;
    }

    public Date getDate() {
        return date;
    }
}

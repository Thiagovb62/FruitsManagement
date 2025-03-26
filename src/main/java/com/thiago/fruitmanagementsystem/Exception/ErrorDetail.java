package com.thiago.fruitmanagementsystem.Exception;

import lombok.Builder;


public class ErrorDetail {

    private String id;
    private String title;
    private int status;
    private String message;

    public ErrorDetail(String id, String title, int status, String message) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
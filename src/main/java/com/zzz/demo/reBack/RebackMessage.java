package com.zzz.demo.reBack;

import java.sql.Timestamp;
import java.util.List;

public class RebackMessage {

    private int code;
    private String message;
    private List list;
    private Timestamp timestamp;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public RebackMessage(int code) {
        this.code = code;
    }

    public RebackMessage() {
    }

    public RebackMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public RebackMessage(int code, String message, List list) {
        this.code = code;
        this.message = message;
        this.list = list;
    }

    public RebackMessage(int code, String message, List list, Timestamp timestamp) {
        this.code = code;
        this.message = message;
        this.list = list;
        this.timestamp = timestamp;
    }
}

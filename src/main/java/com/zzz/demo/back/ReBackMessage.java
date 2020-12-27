package com.zzz.demo.back;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ReBackMessage {

    private int code;
    private String message;
    private List list;
    private Timestamp timestamp;

    public ReBackMessage(int code) {
        this.code = code;
    }

    public ReBackMessage() {
    }

    public ReBackMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ReBackMessage(int code, String message, List list) {
        this.code = code;
        this.message = message;
        this.list = list;
    }

    public ReBackMessage(int code, String message, List list, Timestamp timestamp) {
        this.code = code;
        this.message = message;
        this.list = list;
        this.timestamp = timestamp;
    }
}

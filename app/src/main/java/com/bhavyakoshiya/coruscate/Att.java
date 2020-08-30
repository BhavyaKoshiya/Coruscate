package com.bhavyakoshiya.coruscate;

public class Att {
    private String status;
    private String date;

    public Att() {
    }

    public Att(String status, String date) {
        this.status = status;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Att(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

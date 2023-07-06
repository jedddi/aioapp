package com.example.aioapp.Model;

import java.util.Date;

public class todomodel {
    private int id, status;
    private String task, date;

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {return date;}
    public void setDate(String date) {
        this.date = date;
    }

}

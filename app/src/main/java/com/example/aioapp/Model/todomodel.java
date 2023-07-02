package com.example.aioapp.Model;

public class todomodel {
    private int id, status;
    private String task;

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
}

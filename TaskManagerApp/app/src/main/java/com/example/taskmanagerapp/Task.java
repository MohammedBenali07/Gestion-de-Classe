package com.example.taskmanagerapp;

public class Task {
    private int id;
    private String nameofTask;


    public Task(int id, String nameofTask) {
        this.id = id;
        this.nameofTask = nameofTask;
    }

    public int getId() {
        return id;
    }
    public String getNameofTask() {
        return nameofTask;
    }

    public void setNameofTask(String nameofTask) {
        this.nameofTask = nameofTask;
    }

}

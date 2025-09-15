package com.ucumberlands.todoapp.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private List<Task> tasks;

    public User() {
        tasks = new ArrayList<>();
    }

    public User(String username) {
        this.username = username;
        this.tasks = new ArrayList<>();
    }

    public String getUsername() { return username; }
    public List<Task> getTasks() { return tasks; }

    public void addTask(Task task) { tasks.add(task); }
    public void removeTask(Task task) { tasks.remove(task); }
}

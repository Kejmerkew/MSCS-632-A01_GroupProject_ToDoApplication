package com.ucumberlands.todoapp.model;

public class Task {
    private String title;
    private String category;
    private boolean isCompleted;

    public Task() {}

    public Task(String title, String category) {
        this.title = title;
        this.category = category;
        this.isCompleted = false;
    }

    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public boolean isCompleted() { return isCompleted; }

    public void setCompleted(boolean completed) { isCompleted = completed; }

    public void toggleStatus() { isCompleted = !isCompleted; }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s)", isCompleted ? "X" : " ", title, category);
    }
}

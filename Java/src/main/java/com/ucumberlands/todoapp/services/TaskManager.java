package com.ucumberlands.todoapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucumberlands.todoapp.model.Task;
import com.ucumberlands.todoapp.model.User;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private final Map<String, User> users;
    private final ObjectMapper mapper;
    private final File file;

    public TaskManager(String filePath) {
        users = new HashMap<>();
        mapper = new ObjectMapper();
        file = new File(filePath);
        loadTasks();
    }

    public synchronized void addTask(String username, Task task) {
        users.computeIfAbsent(username, User::new).addTask(task);
        saveTasks();
    }

    public synchronized void removeTask(String username, String title) {
        User user = users.get(username);
        if (user != null) {
            user.getTasks().removeIf(task -> task.getTitle().equalsIgnoreCase(title));
            saveTasks();
        }
    }

    public synchronized void markTaskComplete(String username, String title) {
        User user = users.get(username);
        if (user != null) {
            user.getTasks().stream()
                .filter(task -> task.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .ifPresent(Task::toggleStatus);
            saveTasks();
        }
    }

    private void saveTasks() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        if (!file.exists()) return;
        try {
            Map<String, User> loaded = mapper.readValue(file, new TypeReference<Map<String, User>>() {});
            users.putAll(loaded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void printUserTasks(String username) {
        User user = users.get(username);
        if (user != null) {
            System.out.println("Tasks for " + username + ":");
            user.getTasks().forEach(System.out::println);
        } else {
            System.out.println("No tasks for user " + username);
        }
    }
}

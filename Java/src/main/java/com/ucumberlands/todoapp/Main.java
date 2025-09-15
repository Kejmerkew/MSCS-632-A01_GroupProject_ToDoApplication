package com.ucumberlands.todoapp;

import com.ucumberlands.todoapp.model.Task;
import com.ucumberlands.todoapp.service.TaskManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager("src/main/resources/tasks.json");
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Collaborative To-Do List ===");

        System.out.print("Enter your username: ");
        String username = scanner.nextLine().trim();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. View tasks");
            System.out.println("2. Add task");
            System.out.println("3. Remove task");
            System.out.println("4. Mark task as complete/incomplete");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> manager.printUserTasks(username);

                case "2" -> {
                    System.out.print("Task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Category: ");
                    String category = scanner.nextLine();
                    manager.addTask(username, new Task(title, category));
                    System.out.println("Task added!");
                }

                case "3" -> {
                    System.out.print("Task title to remove: ");
                    String title = scanner.nextLine();
                    manager.removeTask(username, title);
                    System.out.println("Task removed if it existed.");
                }

                case "4" -> {
                    System.out.print("Task title to toggle status: ");
                    String title = scanner.nextLine();
                    manager.markTaskComplete(username, title);
                    System.out.println("Task status updated if task existed.");
                }

                case "5" -> {
                    running = false;
                    System.out.println("Exiting. Goodbye!");
                }

                default -> System.out.println("Invalid option, try again.");
            }
        }

        scanner.close();
    }
}

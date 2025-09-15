package com.ucumberlands.todoapp;

import com.ucumberlands.todoapp.model.Task;
import com.ucumberlands.todoapp.service.TaskManager;

public class ConcurrentDemo {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager("src/main/resources/tasks.json");

        // Simulate multiple users adding tasks at the same time
        Thread aliceThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                manager.addTask("Alice", new Task("Alice Task " + i, "Work"));
                System.out.println("Alice added Task " + i);
                try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });

        Thread bobThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                manager.addTask("Bob", new Task("Bob Task " + i, "Personal"));
                System.out.println("Bob added Task " + i);
                try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });

        Thread charlieThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                manager.addTask("Charlie", new Task("Charlie Task " + i, "Hobby"));
                System.out.println("Charlie added Task " + i);
                try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });

        // Start threads simultaneously
        aliceThread.start();
        bobThread.start();
        charlieThread.start();

        try {
            aliceThread.join();
            bobThread.join();
            charlieThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print tasks for all users after concurrent updates
        System.out.println("\n--- Final Tasks ---");
        manager.printUserTasks("Alice");
        manager.printUserTasks("Bob");
        manager.printUserTasks("Charlie");
    }
}

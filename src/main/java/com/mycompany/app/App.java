package com.mycompany.app;

import java.util.function.*;

/**
 * PE08 - Functional Programming in Java
 * Extended from HOS08A with pass/fail counts and average grade
 * 
 * @author Jonathon Pioquinto
 */
public class App {
    public static void main(String[] args) {
        // Initialize array of Student objects with sample data
        Student[] students = {
            new Student("Alice", 85.0),
            new Student("Bob", 62.5),
            new Student("Charlie", 55.0),
            new Student("Diana", 77.3),
            new Student("Eve", 45.0)
        };

        // Predicate - checks if student passed (grade >= 60)
        Predicate<Student> isPassing = student -> student.grade >= 60;

        // Function - adds 5 bonus points to student's grade
        Function<Student, Student> applyBonus = student -> {
            student.grade += 5;
            return student;
        };

        // Consumer - prints student information
        Consumer<Student> printStudent = student ->
            System.out.println(student.name + " - Grade: " + student.grade);

        // Calculate average grade before bonus
        double total = 0;
        for (Student student : students) {
            total += student.grade;
        }
        double average = total / students.length;

        // Count passing and failing students before bonus
        int passingCount = 0;
        int failingCount = 0;
        for (Student student : students) {
            if (isPassing.test(student)) {
                passingCount++;
            } else {
                failingCount++;
            }
        }

        // Print passing students with bonus applied
        System.out.println("Passing Students with Bonus:");
        for (Student student : students) {
            if (isPassing.test(student)) {
                printStudent.accept(applyBonus.apply(student));
            }
        }

        // Print summary
        System.out.println("\n--- Summary ---");
        System.out.println("Total Students: " + students.length);
        System.out.println("Passing: " + passingCount);
        System.out.println("Failing: " + failingCount);
        System.out.printf("Average Grade (before bonus): %.2f%n", average);
    }
}

/**
 * Student class - represents a student with name and grade
 */
class Student {
    String name;
    double grade;

    /**
     * Constructor to initialize student
     * @param name Student's name
     * @param grade Student's grade
     */
    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    /**
     * Override toString for readable output
     */
    @Override
    public String toString() {
        return "Student: " + name + " | Grade: " + grade;
    }
}
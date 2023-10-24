package org.example;

import org.example.model.Student;

import java.util.List;

/**ALl CRUD/PERSISTENCE operations**/
public class App {

    public static void main( String[] args ) {

        System.out.println("**************   START   **************");

        Student student = new Student();
        student.setName("Paquito");
        student.setLastName("El Chocolatero");

        StudentRepository repo = new StudentRepository();

        repo.addStudent(student);

        System.out.println("Added student: " + student);

        System.out.println("*******************************************");

        Student student2 = new Student();
        student2.setName("Lorenzo");
        student2.setLastName("Que sale el sol");

        repo.addStudent(student2);

        System.out.println("Added student: " + student2);

        System.out.println("*******************************************");

        student = repo.findStudent(student.getId());

        System.out.println("Found student: " + student.toString());

        System.out.println("*******************************************");

        student = repo.findStudentById(student.getId());

        System.out.println("Found student by id: " + student.toString());

        System.out.println("*******************************************");

        System.out.println("Found student's names: ");

        repo.findAllNames().forEach(System.out::println);

        System.out.println("*******************************************");

        System.out.println("Found student's last names: ");

        repo.findAllLastNames().forEach(System.out::println);

        System.out.println("*******************************************");

        student.setName("Lorito");
        student.setLastName("El Maicero");

        repo.updateStudent(student);

        System.out.println("Updated Student: " + student);

        System.out.println("*******************************************");

        student = repo.updateNameById("Paquito", student.getId());

        System.out.println("Updated Student (NAME UPDATED): " + student);

        System.out.println("*******************************************");

        student = repo.updateLastNameById("El Chocolatero", student.getId());

        System.out.println("Updated Student (LAST NAME UPDATED): " + student);

        System.out.println("*******************************************");

        repo.deleteStudent(student);

        System.out.println("Removed Student: " + student);

        System.out.println("*******************************************");

        repo.deleteStudentById(student2.getId());

        System.out.println("Removed Student by ID: " + student2);

        System.out.println("**************   END   **************");

        repo.close();

    }
}


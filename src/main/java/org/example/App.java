package org.example;

import org.example.model.Student;

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
        student2.setName("Ginés");
        student2.setLastName("El de los bocadillos");

        repo.addStudent(student2);

        System.out.println("Added student: " + student2);

        System.out.println("*******************************************");

        Student student3 = new Student();
        student3.setName("García");
        student3.setLastName("Agente especial");

        repo.addStudent(student3);

        System.out.println("Added student: " + student3);

        System.out.println("*******************************************");

        Student student4 = new Student();
        student4.setName("Lorenzo");
        student4.setLastName("Que sale el sol");

        repo.addStudent(student4);

        System.out.println("Added student: " + student4);

        System.out.println("*******************************************");

        System.out.println("Number of students in database: " + repo.count());

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

        System.out.println("Found student's name which stars with G: ");

        repo.findByFirstNameStarWith("G").forEach(System.out::println);

        System.out.println("*******************************************");

        System.out.println("Found students order by name ASC: ");

        repo.findSortingByFirstName().forEach(System.out::println);

        System.out.println("*******************************************");

        System.out.println("Found students order by last name DESC: ");

        repo.findSortingByLastName().forEach(System.out::println);

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

        System.out.println("Number of students in database: " + repo.count());

        System.out.println("*******************************************");

        repo.deleteStudentById(student2.getId());

        System.out.println("Removed Student by ID: " + student2);

        System.out.println("Number of students in database: " + repo.count());

        System.out.println("*******************************************");

        repo.deleteAllStudents();

        System.out.println("Number of students in database: " + repo.count());

        System.out.println("**************   END   **************");

        repo.close();

    }
}


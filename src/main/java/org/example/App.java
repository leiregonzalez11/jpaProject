package org.example;

import org.example.Repositories.SchoolRepository;
import org.example.Repositories.StudentRepository;
import org.example.Repositories.TeacherRepository;
import org.example.Repositories.TutorRepository;
import org.example.model.School;
import org.example.model.Student;
import org.example.model.Teacher;
import org.example.model.Tutor;

import java.util.LinkedList;
import java.util.List;

/**ALL CRUD/PERSISTENCE operations**/
public class App {

    public static void main( String[] args ) {

        StudentRepository repoSt = new StudentRepository();
        SchoolRepository repoSc = new SchoolRepository();
        TutorRepository repoT = new TutorRepository();
        TeacherRepository repoTe = new TeacherRepository();

        List<Student> stlist = new LinkedList<>();

        System.out.println("**************   START   **************");

        Student student = new Student();
        student.setName("Paquito");
        student.setLastName("El Chocolatero");

        repoSt.addStudent(student);
        stlist.add(student);

        System.out.println("Added student: " + student);

        System.out.println("*******************************************");

        Student student2 = new Student();
        student2.setName("Ginés");
        student2.setLastName("El de los bocadillos");

        repoSt.addStudent(student2);
        stlist.add(student2);

        System.out.println("Added student: " + student2);

        System.out.println("*******************************************");

        Student student3 = new Student();
        student3.setName("García");
        student3.setLastName("Agente especial");

        repoSt.addStudent(student3);
        stlist.add(student3);

        System.out.println("Added student: " + student3);

        System.out.println("*******************************************");

        Student student4 = new Student();
        student4.setName("Lorenzo");
        student4.setLastName("Que sale el sol");

        repoSt.addStudent(student4);
        stlist.add(student4);

        System.out.println("Added student: " + student4);

        System.out.println("*******************************************");

        System.out.println("Number of students in database: " + repoSt.count());

        System.out.println("*******************************************");

        Tutor tutor = new Tutor("Marcos", "Marquitos");

        repoT.addTutor(tutor);

        System.out.println("Added tutor: " + tutor);

        System.out.println("*******************************************");

        for (int i = 0; i <stlist.size(); i++){
            student = stlist.get(i);
            repoSt.addTutorToStudent(student.getId(), tutor);
            System.out.println("Student new info, with tutor added: " + student);
        }

        System.out.println("*******************************************");

        School school = new School("IESO Las Arenas", "Getxo");

        repoSc.addSchool(school);

        System.out.println("Added school: " + school);

        System.out.println("*******************************************");

        for (int i = 0; i <stlist.size(); i++){
            student = stlist.get(i);
            repoSc.addStudentToSchool(school.getId(), student);
            System.out.println("School new info, with student added: " + school);
        }

        System.out.println("*******************************************");

        Teacher teacher1 = new Teacher("Maricarmen", "Rodriguez");

        teacher1.setSchool(school);

        repoTe.addTeacher(teacher1);

        System.out.println("Added teacher: " + teacher1);

        System.out.println("*******************************************");

        Teacher teacher2 = new Teacher("Maripili", "Gonzalez");

        teacher2.setSchool(school);

        repoTe.addTeacher(teacher2);

        System.out.println("Added teacher: " + teacher2);

        System.out.println("*******************************************");

        student = repoSt.findStudent(student.getId());

        System.out.println("Found student: " + student.toString());

        System.out.println("*******************************************");

        student = repoSt.findStudentById(student.getId());

        System.out.println("Found student by id: " + student.toString());

        System.out.println("*******************************************");

        System.out.println("Found student's names: ");

        repoSt.findAllNames().forEach(System.out::println);

        System.out.println("*******************************************");

        System.out.println("Found student's last names: ");

        repoSt.findAllLastNames().forEach(System.out::println);

        System.out.println("*******************************************");

        System.out.println("Found student's name which stars with G: ");

        repoSt.findByFirstNameStarWith("G").forEach(System.out::println);

        System.out.println("*******************************************");

        System.out.println("Found students order by name ASC: ");

        repoSt.findSortingByFirstName().forEach(System.out::println);

        System.out.println("*******************************************");

        System.out.println("Found students order by last name DESC: ");

        repoSt.findSortingByLastName().forEach(System.out::println);

        System.out.println("*******************************************");

        student.setName("Lorito");
        student.setLastName("El Maicero");

        repoSt.updateStudent(student);

        System.out.println("Updated Student: " + student);

        System.out.println("*******************************************");

        student = repoSt.updateNameById("Paquito", student.getId());

        System.out.println("Updated Student (NAME UPDATED): " + student);

        System.out.println("*******************************************");

        student = repoSt.updateLastNameById("El Chocolatero", student.getId());

        System.out.println("Updated Student (LAST NAME UPDATED): " + student);

        System.out.println("*******************************************");

        repoSc.removeStudentFromSchool(school.getId(), student);

        System.out.println("Removed Student from School: " + student);
        System.out.println("School info updated: " + school);

        System.out.println("*******************************************");

        stlist.remove(student);
        repoSt.deleteStudent(student);

        System.out.println("Removed Student: " + student);

        System.out.println("*******************************************");

        System.out.println("Number of students in database: " + repoSt.count());

        System.out.println("*******************************************");

        repoSt.deleteStudentById(student2.getId());

        System.out.println("Removed Student by ID: " + student2);

        System.out.println("Number of students in database: " + repoSt.count());

        //System.out.println("*******************************************");

        //repoSt.deleteAllStudents();

        //System.out.println("Number of students in database: " + repoSt.count());

        System.out.println("**************   END   **************");

        repoSt.close();

    }
}


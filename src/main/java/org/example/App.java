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

    private static final StudentRepository repoSt = new StudentRepository();
    private static final SchoolRepository repoSc = new SchoolRepository();
    private static final TutorRepository repoTu = new TutorRepository();
    private static final TeacherRepository repoTe = new TeacherRepository();
    private static final List<Student> stlist = new LinkedList<>();
    private static final List<Tutor> tulist = new LinkedList<>();
    private static final List<School> sclist = new LinkedList<>();
    private static final List<Teacher> telist = new LinkedList<>();

    public static void main( String[] args ) {


        System.out.println("**************   START   **************");

        /*Inserción de datos en la base de datos*/

        insercionDeDatosEnBBDD();

        /*

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

        //System.out.println("Number of students in database: " + repoSt.count());*/

        System.out.println("**************   END   **************");

        repoSt.close();

    }


    private static void insercionDeDatosEnBBDD(){

        /*
         * Creación de los estudiantes
         */

        Student student = new Student("Paquito", "El Chocolatero");
        Student student2 = new Student("Ginés", "El de los bocadillos");
        Student student3 = new Student("García", "Agente especial");
        Student student4 = new Student("Lorenzo", "Que sale el sol");

        //Lista con los estudiantes
        stlist.add(student);
        stlist.add(student2);
        stlist.add(student3);
        stlist.add(student4);

        /* ****************************************************************************** */

        /*
         * Inserción de estudiantes en BBDD
         */

        for (Student st: stlist){
            repoSt.addStudent(st);
            System.out.println("*******************************************");
            System.out.println("Added student: " + st);
            System.out.println("*******************************************");
        }

        /* ****************************************************************************** */

        String numSt = repoSt.count().toString();

        System.out.println("*******************************************");

        System.out.println("Number of students in database: " + numSt);

        System.out.println("*******************************************");

        /* ****************************************************************************** */

        /*
         * Creación de los tutores
         */

        Tutor tutor = new Tutor("Marcos", "Marquitos");
        Tutor tutor2 = new Tutor("Pepe", "Pepito");
        Tutor tutor3 = new Tutor("Lucas", "Luquitas");

        tulist.add(tutor);
        tulist.add(tutor2);
        tulist.add(tutor3);

        /* ****************************************************************************** */

        /*
         * Inserción de tutores en BBDD
         */

        for (Tutor tu: tulist){
            repoTu.addTutor(tu);
            System.out.println("*******************************************");
            System.out.println("Added tutor: " + tu);
            System.out.println("*******************************************");
        }

        /* ****************************************************************************** */

        String numTu = repoTu.count().toString();

        System.out.println("*******************************************");

        System.out.println("Number of tutors in database: " + numTu);

        System.out.println("*******************************************");

        /* ****************************************************************************** */

        /*
         * Asignación de tutores a estudiantes (Asignación de tutor aleatoria)
         */

        for (Student value : stlist) {
            int random = (int) (Math.random() *tulist.size());
            repoSt.addTutorToStudent(value.getId(), tulist.get(random));
            System.out.println("*******************************************");
            System.out.println("Tutor added to the student. Student new info: " + value);
            System.out.println("*******************************************");
        }

        /* ****************************************************************************** */

        /*
         * Creación de los colegios
         */

        School school = new School("IESO GuauGuau", "Getxo");
        School school2 = new School("IES PioPio", "Bilbao");

        sclist.add(school);
        sclist.add(school2);

        /* ****************************************************************************** */

        /*
         * Inserción de colegios en BBDD
         */

        for (School sc: sclist){
            repoSc.addSchool(sc);
            System.out.println("*******************************************");
            System.out.println("Added school: " + sc);
            System.out.println("*******************************************");
        }

        /* ****************************************************************************** */

        String numSc = repoSc.count().toString();

        System.out.println("*******************************************");

        System.out.println("Number of schools in database: " + numSc);

        System.out.println("*******************************************");

        /* ****************************************************************************** */

        /*
         * Asignación de estudiantes a los colegios (Asignación aleatoria)
         */

        for (Student value : stlist) {
            int random = (int) (Math.random() *sclist.size());
            repoSc.addStudentToSchool(sclist.get(random).getId(), value);
            System.out.println("*******************************************");
            System.out.println("School info updated. " + value.getName() + " " + value.getLastName() + " added to " + sclist.get(random).getName());
            System.out.println("*******************************************");
        }

        /* ****************************************************************************** */

        /*
         * Creación de los profesores
         */

        Teacher teacher1 = new Teacher("Maricarmen", "Rodriguez");
        Teacher teacher2 = new Teacher("Maripili", "Gonzalez");

        telist.add(teacher1);
        telist.add(teacher2);

        /* ****************************************************************************** */

        /*
         * Asignación de colegio a los profesores (Asignación aleatoria)
         */

        for (Teacher value : telist) {
            int random = (int) (Math.random() *sclist.size());
            value.setSchool(sclist.get(random));
            System.out.println("*******************************************");
            System.out.println("Teacher info updated. " + sclist.get(random).getName() + " added to " + value.getName() + " " + value.getLastname());
            System.out.println("*******************************************");
        }

        /* ****************************************************************************** */

        /*
         * Inserción de profesores en BBDD
         */

        for (Teacher te: telist){
            repoTe.addTeacher(te);
            System.out.println("*******************************************");
            System.out.println("Added teacher: " + te);
            System.out.println("*******************************************");
        }

        /* ****************************************************************************** */

    }
}


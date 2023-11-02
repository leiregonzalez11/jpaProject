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

        /*Métodos de búsqueda de datos*/

        metodosDeBusqueda();

        /*Métodos de modificación de datos*/

        metodosDeModificacion();

        /*Métodos de eliminación de información*/

        metodosDeEliminacion();

        System.out.println("**************   END   **************");

        repoSt.close();

    }


    private static void insercionDeDatosEnBBDD(){

        /*
         * Creación de los estudiantes
         */

        stlist.add(new Student("Paquito", "El Chocolatero"));
        stlist.add(new Student("Ginés", "El de los bocadillos"));
        stlist.add(new Student("García", "Agente especial"));
        stlist.add(new Student("Lorenzo", "Que sale el sol"));

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

        tulist.add(new Tutor("Marcos", "Marquitos"));
        tulist.add(new Tutor("Pepe", "Pepito"));
        tulist.add(new Tutor("Lucas", "Luquitas"));

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

        sclist.add(new School("IESO GuauGuau", "Getxo"));
        sclist.add(new School("IES PioPio", "Bilbao"));

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

        telist.add(new Teacher("Maricarmen", "Rodriguez"));
        telist.add(new Teacher("Maripili", "Gonzalez"));

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

    private static void metodosDeBusqueda(){

        System.out.println("*******************************************");
        System.out.println("Búsqueda de un estudiante cualquiera: ");

        System.out.println("--------------------------------------------");

        int random = (int) (Math.random() *stlist.size());
        System.out.println("Found student: " + repoSt.findStudent(stlist.get(random).getId()));

        System.out.println("*******************************************");

        System.out.println("Búsqueda de un estudiante cualquiera usando Names Queries: ");

        System.out.println("--------------------------------------------");

        random = (int) (Math.random() *stlist.size());

        System.out.println("Found student: " + repoSt.findStudentById(stlist.get(random).getId()));

        System.out.println("*******************************************");

        System.out.println("Búsqueda de los nombres de todos los estudiantes: ");

        System.out.println("--------------------------------------------");

        System.out.println("All student's names which have been found:");

        //repoSt.findAllNames().forEach(System.out::println); --> Para imprimir todos los nombres seguidos, sin estilo.

        int i = 1;

        for (String name: repoSt.findAllNames()){
            System.out.println("--- " + i + ") Student's name : " + name + " ----");
            i++;
        }

        System.out.println("*******************************************");

        System.out.println("Búsqueda de los nombres ordenados por nombre ASC: ");

        System.out.println("--------------------------------------------");

        System.out.println("All student's names which have been found order by name ASC:");

        i = 1;

        for (Student st: repoSt.findSortingByFirstName()){
            System.out.println("--- " + i + ") Student : " + st.getName() + " " + st.getLastName() + " ----");
            i++;
        }

        System.out.println("*******************************************");

        System.out.println("Búsqueda de los apellidos de todos los estudiantes: ");

        System.out.println("--------------------------------------------");

        System.out.println("All student's last names which have been found:");

        i = 1;

        for (String lastname: repoSt.findAllLastNames()){
            System.out.println("--- " + i + ") Student's last name : " + lastname + " ----");
            i++;
        }

        System.out.println("*******************************************");

        System.out.println("Búsqueda de los apellidos de todos los estudiantes ordenados por apellido DESC: ");

        System.out.println("--------------------------------------------");

        System.out.println("All student's last names which have been found order by last name DESC:");

        i = 1;

        for (Student st: repoSt.findSortingByLastName()){
            System.out.println("--- " + i + ") Student : " + st.getName() + " " + st.getLastName() + " ----");
            i++;
        }

        System.out.println("*******************************************");

        System.out.println("Búsqueda de los estudiantes cuyo nombre empieza por G: ");

        System.out.println("--------------------------------------------");

        System.out.println("All student's names which start with G");

        i = 1;

        for (Student st: repoSt.findByFirstNameStarWith("G")){
            System.out.println("--- " + i + ") Student : " + st.getName() + " " + st.getLastName() + " ----");
            i++;
        }

        System.out.println("*******************************************");

    }

    private static void metodosDeModificacion(){

        System.out.println("Modificación de un estudiante al azar: ");

        System.out.println("--------------------------------------------");

        //Se busca un estudiante al azar para realizar las modificaciones
        int random = (int) (Math.random() *stlist.size());
        Student student = repoSt.findStudent(stlist.get(random).getId());
        System.out.println("*******************************************");
        System.out.println("Found student: " + student);
        System.out.println("*******************************************");

        String primerNombre = student.getName();
        String primerApellido = student.getLastName();

        //Modificamos el nombre y apellido del estudiante
        student.setName("Lorito");
        student.setLastName("El Maicero");

        repoSt.updateStudent(student);

        System.out.println("*******************************************");

        System.out.println("Updated student: " + student);

        System.out.println("*******************************************");

        //Modifición únicamente del nombre
        student = repoSt.updateNameById(primerNombre, student.getId());

        System.out.println("*******************************************");

        System.out.println("Updated Student (NAME UPDATED): " + student);

        System.out.println("*******************************************");

        //Modifición únicamente del apellido

        student = repoSt.updateLastNameById(primerApellido, student.getId());

        System.out.println("*******************************************");

        System.out.println("Updated Student (LAST NAME UPDATED): " + student);

        System.out.println("*******************************************");

    }

    private static void metodosDeEliminacion(){

        /*repoSc.removeStudentFromSchool(school.getId(), student);

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

    }

}


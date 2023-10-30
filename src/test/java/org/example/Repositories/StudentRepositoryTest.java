package org.example.Repositories;

import org.example.model.Student;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class StudentRepositoryTest {

    private static StudentRepository repo;

    @BeforeClass
    public static void beforeClass() {
        repo = new StudentRepository("student_pu_test");
    }

    @AfterClass
    public static void afterClass(){
        repo.close();
    }

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
    }

    @Test
    public void count() {

        repo.deleteAllStudents();

        Student st1 = new Student();
        st1.setName("Nombre1");
        st1.setLastName("Ape1");

        Student st2 = new Student();
        st2.setName("Nombre2");
        st2.setLastName("Ape2");

        repo.addStudent(st1);
        repo.addStudent(st2);

        assertEquals(2, Integer.parseInt(repo.count().toString()));
    }

    /**
     *  Add test method
     */

    @Test
    public void addStudent() {

        //Se crea un estudiante nuevo y se añade
        Student st1 = new Student();
        st1.setName("Leire");
        st1.setLastName("Prueba");

        repo.addStudent(st1);

        //Se comprueba que el estudiante ha sido añadido
        assertNotNull(st1.getId());
        assertEquals("Prueba", st1.getLastName());
    }

    /**
     *  Find test methods
     */

    @Test
    public void findStudent() {

        Student st1 = new Student();
        st1.setName("Pablo");
        st1.setLastName("Prueba2");

        repo.addStudent(st1);

        st1 = repo.findStudent(st1.getId());

        //Se comprueba que el estudiante ha sido añadido y que coinciden sus datos
        assertNotNull(st1);
        assertNotNull(st1.getId());
        assertEquals("Prueba2", st1.getLastName());

    }

    @Test
    public void findAllNames() {

        Student st1 = new Student();
        st1.setName("Nombre1");
        st1.setLastName("Ape1");

        Student st2 = new Student();
        st2.setName("Nombre2");
        st2.setLastName("Ape2");

        repo.addStudent(st1);
        repo.addStudent(st2);

        List<String> st = repo.findAllNames();

        assertEquals(st.get(0), st1.getName());
        assertNotEquals(st.get(1), st1.getName());
    }

    @Test
    public void findAllLastNames() {

        repo.deleteAllStudents();

        Student st1 = new Student();
        st1.setName("Nombre1");
        st1.setLastName("Ape1");

        Student st2 = new Student();
        st2.setName("Nombre2");
        st2.setLastName("Ape2");

        repo.addStudent(st1);
        repo.addStudent(st2);

        List<String> st = repo.findAllLastNames();

        assertEquals(st.get(0), st1.getLastName());
        assertNotEquals(st.get(1), st1.getLastName());
    }

    @Test
    public void findStudentById() {

        Student st1 = new Student();
        st1.setName("Pablo");
        st1.setLastName("Prueba2");

        repo.addStudent(st1);

        st1 = repo.findStudentById(st1.getId());

        //Se comprueba que el estudiante ha sido añadido y que coinciden sus datos
        assertNotNull(st1);
        assertNotNull(st1.getId());
        assertEquals("Prueba2", st1.getLastName());

    }

    @Test
    public void findByFirstNameStarWith() {

        Student st1 = new Student();
        st1.setName("Nombre1");
        st1.setLastName("Ape1");

        Student st2 = new Student();
        st2.setName("Nombre2");
        st2.setLastName("Ape2");

        repo.addStudent(st1);
        repo.addStudent(st2);

        repo.addStudent(st1);

        List<Student> stlist1 = repo.findByFirstNameStarWith("N");
        List<Student> stlist2 = repo.findByFirstNameStarWith("J");

        assertEquals(stlist2.size(), 0);
        assertNotNull(stlist1);
        assertNotNull(stlist1.get(0));
        assertEquals("Nombre1", stlist1.get(0).getName());
    }


    @Test
    public void findSortingByFirstName() {

        repo.deleteAllStudents();

        Student st1 = new Student();
        st1.setName("Nombre1");
        st1.setLastName("Ape1");

        Student st2 = new Student();
        st2.setName("Nombre3");
        st2.setLastName("Ape3");

        Student st3 = new Student();
        st3.setName("Nombre2");
        st3.setLastName("Ape2");

        repo.addStudent(st1);
        repo.addStudent(st2);
        repo.addStudent(st3);

        List<Student> st = repo.findSortingByFirstName();
        assertEquals(st.get(0).getName(), st1.getName());
        assertEquals(st.get(1).getName(), st3.getName());
        assertNotEquals(st.get(1).getName(), st1.getName());
    }

    @Test
    public void findSortingByLastName() {

        repo.deleteAllStudents();

        //Sorting DESC

        Student st1 = new Student();
        st1.setName("Nombre1");
        st1.setLastName("Ape1");

        Student st2 = new Student();
        st2.setName("Nombre3");
        st2.setLastName("Ape3");

        Student st3 = new Student();
        st3.setName("Nombre2");
        st3.setLastName("Ape2");

        repo.addStudent(st1);
        repo.addStudent(st2);
        repo.addStudent(st3);

        List<Student> st = repo.findSortingByLastName();
        assertEquals(st.get(0).getLastName(), st2.getLastName());
        assertEquals(st.get(1).getLastName(), st3.getLastName());
        assertNotEquals(st.get(1).getLastName(), st1.getLastName());
    }

    /**
     *  Update test methods
     */

    @Test
    public void updateStudent() {

        Student st1 = new Student();
        st1.setName("Pedro");
        st1.setLastName("Prueba1");

        repo.addStudent(st1);

        st1.setLastName("Prueba3");

        repo.updateStudent(st1);

        //Se comprueba que el estudiante ha sido añadido y que sus datos se han modificado correctamente
        assertNotNull(st1);
        assertNotNull(st1.getId());
        assertEquals("Prueba3", st1.getLastName());
        assertEquals("Pedro", st1.getName());

    }

    @Test
    public void updateNameById() {

        Student st1 = new Student();
        st1.setName("Pedro");
        st1.setLastName("Prueba1");

        repo.addStudent(st1);

        st1 = repo.updateNameById("Pablo", st1.getId());

        //Se comprueba que el estudiante ha sido añadido y que sus datos se han modificado correctamente
        assertNotNull(st1);
        assertNotNull(st1.getId());
        assertEquals("Prueba1", st1.getLastName());
        assertEquals("Pablo", st1.getName());
        assertNotEquals("Pedro", st1.getName());

    }

    @Test
    public void updateLastNameById() {

        Student st1 = new Student();
        st1.setName("Pedro");
        st1.setLastName("Prueba1");

        repo.addStudent(st1);

        st1 = repo.updateLastNameById("Prueba2", st1.getId());

        //Se comprueba que el estudiante ha sido añadido y que sus datos se han modificado correctamente
        assertNotNull(st1);
        assertNotNull(st1.getId());
        assertEquals("Pedro", st1.getName());
        assertEquals("Prueba2", st1.getLastName());
        assertNotEquals("Prueba1", st1.getLastName());
    }

    /**
     *  Delete test methods
     */

    @Test
    public void deleteStudent() {

        Student st1 = new Student();
        st1.setName("Iker");
        st1.setLastName("Prueba4");

        repo.addStudent(st1);

        repo.deleteStudent(st1);

        st1 = repo.findStudent(st1.getId());

        //Se comprueba que el estudiante ha sido eliminado de la BBDD
        assertNull(st1);
    }

    @Test
    public void deleteStudentById() {

        repo.deleteAllStudents();

        Student st1 = new Student();
        st1.setName("Iker");
        st1.setLastName("Prueba4");

        repo.addStudent(st1);

        repo.deleteStudentById(st1.getId());

        st1 = repo.findStudent(st1.getId());

        //Se comprueba que el estudiante ha sido eliminado de la BBDD
        assertNull(st1);
    }

    @Test
    public void deleteAllStudents() {

        Student st = new Student();
        st.setName("Iker");
        st.setLastName("Prueba4");

        Student st1 = new Student();
        st1.setName("lolo");
        st1.setLastName("Prueba5");

        repo.addStudent(st);
        repo.addStudent(st1);

        repo.deleteAllStudents();

        st1 = repo.findStudent(st1.getId());

        assertNull(st1);
        assertEquals(Integer.parseInt(repo.count().toString()), 0);
    }
}

package org.example;

import org.example.model.Student;
import org.junit.*;

import static org.junit.Assert.*;

public class StudentRepositoryTest {

    private static StudentRepository repo;

    @BeforeClass
    public static void beforeClass() throws Exception {
        repo = new StudentRepository("student_pu_test");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        repo.close();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

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
}

package org.example.Repositories;

import org.example.model.School;
import org.example.model.Student;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class SchoolRepositoryTest {

    private static SchoolRepository repo;
    private static StudentRepository repoSt;

    @BeforeClass
    public static void beforeClass() {
        repo = new SchoolRepository("student_pu_test");
        repoSt = new StudentRepository("student_pu_test");
    }

    @AfterClass
    public static void afterClass(){
        repo.close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addSchool() {
        //Se crea una escuela nueva y se añade
        School sc1 = new School();
        sc1.setName("IES Las Arenas");
        sc1.setCity("Getxo");

        repo.addSchool(sc1);

        //Se comprueba que la escuela ha sido añadida
        assertNotNull(sc1.getId());
        assertEquals("Getxo", sc1.getCity());
    }

    @Test
    public void findSchool() {
        School sc1 = new School();
        sc1.setName("IES Las Arenas");
        sc1.setCity("Getxo");

        repo.addSchool(sc1);

        sc1 = repo.findSchool(sc1.getId());

        //Se comprueba que el escudiante ha sido añadido y que coinciden sus datos
        assertNotNull(sc1);
        assertNotNull(sc1.getId());
        assertEquals("Getxo", sc1.getCity());
    }

    @Test
    public void updateSchool() {
        School sc1 = new School();
        sc1.setName("IES Las Arenas");
        sc1.setCity("Leioa");

        repo.addSchool(sc1);

        sc1.setCity("Getxo");

        repo.updateSchool(sc1);

        //Se comprueba que el estudiante ha sido añadido y que sus datos se han modificado correctamente
        assertNotNull(sc1);
        assertNotNull(sc1.getId());
        assertEquals("IES Las Arenas", sc1.getName());
        assertEquals("Getxo", sc1.getCity());
        assertNotEquals("Leioa", sc1.getCity());
    }

    @Test
    public void deleteSchool() {
        School sc1 = new School();
        sc1.setName("IES Las Arenas");
        sc1.setCity("Getxo");

        repo.addSchool(sc1);

        repo.deleteSchool(sc1);

        sc1 = repo.findSchool(sc1.getId());

        //Se comprueba que la escuela ha sido eliminada de la BBDD
        assertNull(sc1);
    }

    @Test
    public void addStudentToSchool() {

        Student st1 = new Student();
        st1.setName("Iker");
        st1.setLastName("Prueba4");

        repoSt.addStudent(st1);

        School sc1 = new School();
        sc1.setName("IES Las Arenas");
        sc1.setCity("Getxo");

        repo.addSchool(sc1);

        repo.addStudentToSchool(sc1.getId(), st1);

        assertNotNull(st1);
        assertNotNull(sc1);
        assertNotNull(sc1.getStudents());
        assertEquals(sc1.getStudents().size(), 1);

    }

    @Test
    public void removeStudentFromSchool() {

        Student st1 = new Student();
        st1.setName("Iker");
        st1.setLastName("Prueba4");

        repoSt.addStudent(st1);

        School sc1 = new School();
        sc1.setName("IES Las Arenas");
        sc1.setCity("Getxo");

        repo.addSchool(sc1);

        repo.addStudentToSchool(sc1.getId(), st1);

        assertEquals(sc1.getStudents().size(), 1);

        repo.removeStudentFromSchool(sc1.getId(), st1);

        assertEquals(sc1.getStudents().size(), 0);
    }
}
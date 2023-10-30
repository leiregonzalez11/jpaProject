package org.example.Repositories;

import org.example.model.Student;
import org.example.model.Tutor;
import org.junit.*;

import static org.junit.Assert.*;

public class TutorRepositoryTest {

    private static TutorRepository repo;

    @BeforeClass
    public static void beforeClass() {
        repo = new TutorRepository("student_pu_test");
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
    public void addTutor() {
        //Se crea un tutor nuevo y se añade
        Tutor tt1 = new Tutor();
        tt1.setName("Leire");
        tt1.setLastname("Prueba");

        repo.addTutor(tt1);

        //Se comprueba que el tutor ha sido añadido
        assertNotNull(tt1.getId());
        assertEquals("Prueba", tt1.getLastname());
    }

    @Test
    public void findTutor() {

        Tutor tt1 = new Tutor();
        tt1.setName("Pablo");
        tt1.setLastname("Prueba2");

        repo.addTutor(tt1);

        tt1 = repo.findTutor(tt1.getId());

        //Se comprueba que el tutor ha sido añadido y que coinciden sus datos
        assertNotNull(tt1);
        assertNotNull(tt1.getId());
        assertEquals("Prueba2", tt1.getLastname());
    }

    @Test
    public void updateTutor() {

        Tutor tt1 = new Tutor();
        tt1.setName("Pedro");
        tt1.setLastname("Prueba1");

        repo.addTutor(tt1);

        tt1.setLastname("Prueba3");

        repo.updateTutor(tt1);

        //Se comprueba que el tutor ha sido añadido y que sus datos se han modificado correctamente
        assertNotNull(tt1);
        assertNotNull(tt1.getId());
        assertEquals("Prueba3", tt1.getLastname());
        assertEquals("Pedro", tt1.getName());
    }

    @Test
    public void deleteTutor() {

        Tutor tt1 = new Tutor();
        tt1.setName("Iker");
        tt1.setLastname("Prueba4");

        repo.addTutor(tt1);

        repo.deleteTutor(tt1);

        tt1 = repo.findTutor(tt1.getId());

        //Se comprueba que el tutor ha sido eliminado de la BBDD
        assertNull(tt1);
    }
}
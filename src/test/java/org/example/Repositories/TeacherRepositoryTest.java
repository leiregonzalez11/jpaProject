package org.example.Repositories;

import org.junit.*;

public class TeacherRepositoryTest {

    private static TeacherRepository repo;

    @BeforeClass
    public static void beforeClass() {
        repo = new TeacherRepository("student_pu_test");
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

    @Test //TODO
    public void addTeacher() {
    }

    @Test //TODO
    public void findTeacher() {
    }

    @Test //TODO
    public void updateTeacher() {
    }

    @Test //TODO
    public void deleteTeacher() {
    }
}
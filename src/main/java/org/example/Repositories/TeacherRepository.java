package org.example.Repositories;

import org.example.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TeacherRepository {

    private final EntityManager em;
    private final EntityManagerFactory emf;

    /**
     * Constructors
     * */

    public TeacherRepository() {
        emf = Persistence.createEntityManagerFactory("student_pu");
        this.em = emf.createEntityManager();
    }

    //Constructor para los test
    public TeacherRepository(String persistenceunit) {
        emf = Persistence.createEntityManagerFactory(persistenceunit);
        this.em = emf.createEntityManager();
    }

    public void close(){
        this.em.close();
        this.emf.close();
    }

    /**CRUD METHODS*/

    public void addTeacher(Teacher teacher){
        em.getTransaction().begin();
        em.persist(teacher);
        em.getTransaction().commit();
    }

    public Teacher findTeacher (Long id){
        return em.find(Teacher.class, id);
    }

    public void updateTeacher(Teacher teacher){
        Teacher stUpdate = findTeacher(teacher.getId());
        em.getTransaction().begin();
        stUpdate.setName(teacher.getName());
        em.getTransaction().commit();
    }

    public void deleteTeacher(Teacher teacher){
        em.getTransaction().begin();
        em.remove(teacher);
        em.getTransaction().commit();
    }
}

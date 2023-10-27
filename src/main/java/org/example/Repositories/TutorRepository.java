package org.example.Repositories;

import org.example.model.Student;
import org.example.model.Tutor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TutorRepository {

    private final EntityManager em;
    private final EntityManagerFactory emf;

    /**
     * Constructors
     * */

    public TutorRepository() {
        emf = Persistence.createEntityManagerFactory("student_pu");
        this.em = emf.createEntityManager();
    }

    //Constructor para los test
    public TutorRepository(String persistenceunit) {
        emf = Persistence.createEntityManagerFactory(persistenceunit);
        this.em = emf.createEntityManager();
    }

    public void close(){
        this.em.close();
        this.emf.close();
    }

    /**CRUD METHODS*/

    public void addTutor(Tutor tutor){
        em.getTransaction().begin();
        em.persist(tutor);
        em.getTransaction().commit();
    }

    public Tutor findTutor (Long id){
        return em.find(Tutor.class, id);
    }

    public void updateTutor(Tutor tutor){
        Tutor stUpdate = findTutor(tutor.getId());
        em.getTransaction().begin();
        stUpdate.setName(tutor.getName());
        em.getTransaction().commit();
    }

    public void deleteTutor(Tutor tutor){
        em.getTransaction().begin();
        em.remove(tutor);
        em.getTransaction().commit();
    }


}

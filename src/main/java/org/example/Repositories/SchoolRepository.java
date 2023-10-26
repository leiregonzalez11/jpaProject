package org.example.Repositories;

import org.example.model.School;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SchoolRepository {

    private final EntityManager em;
    private final EntityManagerFactory emf;

    /**
     * Constructors
     * */

    public SchoolRepository() {
        emf = Persistence.createEntityManagerFactory("student_pu");
        this.em = emf.createEntityManager();
    }

    //Constructor para los test
    public SchoolRepository(String persistenceunit) {
        emf = Persistence.createEntityManagerFactory(persistenceunit);
        this.em = emf.createEntityManager();
    }

    /**CRUD METHODS*/

    public void addSchool(School school){
        em.getTransaction().begin();
        em.persist(school);
        em.getTransaction().commit();
    }

    public School findSchool (Long id){
        return em.find(School.class, id);
    }

    public void updateSchool(School school){
        School scUpdate = findSchool(school.getId());
        em.getTransaction().begin();
        scUpdate.setName(school.getName());
        em.getTransaction().commit();
    }

    public void deleteSchool(School school){
        em.getTransaction().begin();
        em.remove(school);
        em.getTransaction().commit();
    }

    public void close(){
        this.em.close();
        this.emf.close();
    }
    
    
    
}

package org.example;

import org.example.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**Controlador lógico de Student:
 *  Concentra los métodos CRUD/persistentes en una única clase.
 * **/
public class StudentRepository {

    private final EntityManager em;
    private final EntityManagerFactory emf;

    public StudentRepository() {
        emf = Persistence.createEntityManagerFactory("student_pu");
        this.em = emf.createEntityManager();
    }

    //Constructor para los test
    public StudentRepository(String persistenceunit) {
        emf = Persistence.createEntityManagerFactory(persistenceunit);
        this.em = emf.createEntityManager();
    }

    public void close(){
        this.em.close();
        this.emf.close();
    }

    /**CRUD METHODS*/

    public void addStudent(Student student){
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    }

    public Student findStudent (Long id){
        return em.find(Student.class, id);
    }

    public void updateStudent(Student student){
        Student stUpdate = findStudent(student.getId());
        em.getTransaction().begin();
        stUpdate.setName(student.getName());
        em.getTransaction().commit();
    }

    public void deleteStudent(Student student){
        em.getTransaction().begin();
        em.remove(student);
        em.getTransaction().commit();
    }

    /**
     * EXTRA METHODS WITH JPQL
     */

    public List<String> findAllNames(){
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT s.name FROM Student s");
        em.getTransaction().commit();
        return query.getResultList();
    }

    public List<String> findAllLastNames(){
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT s.lastName FROM Student s");
        em.getTransaction().commit();
        return query.getResultList();
    }

    /**
     * EXTRA METHODS WITH NAMED QUERIES
     */

    public Student findStudentById(Long id){
        Query query = em.createNamedQuery("find student by id");
        query.setParameter("id", id);
        return (Student) query.getSingleResult();
    }

    /**
     * EXTRA METHODS FOR UPDATE ENTITIES
     */



}

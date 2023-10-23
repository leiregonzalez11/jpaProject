package org.example.CRUD;

import org.example.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FindStudent {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("student_pu");
        EntityManager em = emf.createEntityManager();

        Student student = em.find(Student.class, 1L);

        if (student!= null){
            System.out.println(student);

        }

    }
}

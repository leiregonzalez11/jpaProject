package org.example.CRUD;

import org.example.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UpdateStudent {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("student_pu");
        EntityManager em = emf.createEntityManager();

        Student student = em.find(Student.class, 1L);

        System.out.println("Before update: " + student.toString());

        EntityTransaction et = em.getTransaction();

        et.begin();

        student.setName("Iker");

        System.out.println("After update: " + student);

        et.commit();
        em.close();
        emf.close();


    }

}

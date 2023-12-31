package org.example.Repositories;

import org.example.model.Student;
import org.example.model.Tutor;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;

/** Controlador lógico de Student:
 *     Concentra los métodos CRUD/persistentes en una única clase.
 * **/
@SuppressWarnings("DuplicatedCode")
public class StudentRepository {

    private final EntityManager em;
    private final EntityManagerFactory emf;

    /**
     * Constructors
     * */

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
     * JPQL (Java Persistence Query Language)
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
     * NAMED QUERIES
     */

    public Student findStudentById(Long id){
        Query query = em.createNamedQuery("find student by id");
        query.setParameter("id", id);
        return (Student) query.getSingleResult();
    }

    /**
     * UPDATING ENTITIES
     */

    public Student updateNameById(String name, Long id){
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Student SET name = '"+ name +"' WHERE id = " + id); //NamedNativeQuery
        query.executeUpdate();
        em.getTransaction().commit();
        em.clear();
        return  findStudentById(id);
    }

    public Student updateLastNameById(String lastname, Long id){
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Student SET lastName = '"+ lastname +"' WHERE id = " + id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.clear();
        return  findStudentById(id);
    }

    /**
     * DELETING ENTITIES
     */

    public void deleteStudentById(Long id){
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Student WHERE id = " + id);
        query.executeUpdate();
        em.clear();
        em.getTransaction().commit();
    }

    public void deleteAllStudents(){
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Student");
        query.executeUpdate();
        em.clear();
        em.getTransaction().commit();
    }

    /**
     * FILTERING ENTITIES
     */

    public List<Student> findByFirstNameStarWith (String keyword){
        Query query = em.createQuery("SELECT s FROM Student s WHERE s.name LIKE '" + keyword + "%'");
        return query.getResultList();
    }

    public Long count(){
        Query query = em.createQuery("SELECT COUNT(s) FROM Student s");
        return (Long) query.getSingleResult();
    }

    /**
     * SORTING ENTITIES
     */

    public List<Student> findSortingByFirstName(){
        Query query = em.createQuery("SELECT s FROM Student s ORDER BY s.name");
        return query.getResultList();
    }

    public List<Student> findSortingByLastName(){
        Query query = em.createQuery("SELECT s FROM Student s ORDER BY s.lastName DESC");
        return query.getResultList();
    }

    /**
     * ONE-TO-ONE RELATIONSHIP METHODS
     */

    public void addTutorToStudent(Long id, Tutor tutor){
        em.getTransaction().begin();
        Student st = findStudent(id);
        st.setTutor(tutor);
        em.getTransaction().commit();
    }

    /**
     * QUERIES WITH CRITERIA API
     */

    public List<Student> getStudentWithCriteriaBuilder(){
        em.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);

        Root<Student> studentRoot = criteriaQuery.from(Student.class);

        criteriaQuery.select(studentRoot.get("name"));

        CriteriaQuery<Student> select = criteriaQuery.select(studentRoot);
        TypedQuery<Student> query = em.createQuery(select);

        em.getTransaction().commit();

        return query.getResultList();

    }


    public List<Student> getStudentWithCriteriaBuilderWithOrderBy(){

        em.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);

        Root<Student> studentRoot = criteriaQuery.from(Student.class);

        criteriaQuery.select(studentRoot.get("name"));
        criteriaQuery.distinct(true);
        criteriaQuery.orderBy(criteriaBuilder.asc(studentRoot.get("name")));

        CriteriaQuery<Student> select = criteriaQuery.select(studentRoot);
        TypedQuery<Student> query = em.createQuery(select);

        em.getTransaction().commit();

        return query.getResultList();

    }

    public List<Student> getStudentWithCriteriaBuilderWithWhere(){
        em.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);

        Root<Student> studentRoot = criteriaQuery.from(Student.class);

        List<String> namelist = Arrays.asList("Basilio", "Paquito");

        Expression<String> exp = studentRoot.get("name");
        Predicate in = exp.in(namelist);

        criteriaQuery.where(in);

        CriteriaQuery<Student> select = criteriaQuery.select(studentRoot);
        TypedQuery<Student> query = em.createQuery(select);

        em.getTransaction().commit();

        return query.getResultList();

    }

    public List<Student> getStudentWithCriteriaBuilderWithGroupBy(){
        em.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);

        Root<Student> studentRoot = criteriaQuery.from(Student.class);

        List<String> namelist = Arrays.asList("Basilio", "Paquito");

        Expression<String> exp = studentRoot.get("name");
        Predicate in = exp.in(namelist);

        criteriaQuery.where(in);
        criteriaQuery.groupBy(studentRoot.get("lastName"));


        CriteriaQuery<Student> select = criteriaQuery.select(studentRoot);
        TypedQuery<Student> query = em.createQuery(select);

        em.getTransaction().commit();

        return query.getResultList();

    }


}

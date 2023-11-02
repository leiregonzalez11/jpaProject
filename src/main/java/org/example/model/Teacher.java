package org.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstName", nullable = false, length = 150)
    private String name;
    @Column(name = "lastName", nullable = false, length = 200)
    private String lastname;

    @ManyToOne
    private School school;

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "teachers_students",
            joinColumns =  { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") },
            uniqueConstraints = {
                    @UniqueConstraint(
                            columnNames = { "teacher_id", "student_id" }
                    )
            }

    )
    private Set<Student> students = new HashSet<>();

    public Teacher(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public Teacher() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void addStudent(Student student) {
        boolean added = students.add(student);
        if(added) {
            student.getTeachers().add(this);
        }
    }

    public void removeStudent(Student student) {
        boolean removed = students.remove(student);
        if(removed) {
            student.getTeachers().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", school=" + school +
                '}';
    }

}

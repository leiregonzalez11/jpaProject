package org.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "ST")
@NamedQuery(name="find student by id", query ="SELECT s FROM Student s WHERE s.id =: id")
public class Student extends Person {

    @OneToOne
    private Tutor tutor;

    @ManyToMany(mappedBy = "students")
    private Set<Teacher> teachers = new HashSet<>();

    public Student() {
    }

    public Student(String name, String lastName) {
        super(name, lastName);
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public void addTeacher(Teacher teacher) {
        boolean added = teachers.add(teacher);
        if (added) {
            teacher.getStudents().add(this);
        }
    }

    public void removeTeacher(Teacher teacher) {
        boolean remove = teachers.remove(teacher);
        if (remove) {
            teacher.getStudents().remove(this);
        }
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}

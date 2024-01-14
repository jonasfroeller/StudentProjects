package at.htlleonding.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@NamedQuery(name = Student.QUERY_SELECT_ALL_BY_CLASS, query = "SELECT s FROM Student s WHERE s.studentId.clazz = :clazz")
public class Student extends Person {
    public static final String QUERY_SELECT_ALL_BY_CLASS = "Student.SELECT.ALL.BY(CLASS_ID)";

    @EmbeddedId
    @AttributeOverride(name = "clazz", column = @Column(name = "clazz"))
    @AttributeOverride(name = "catalogNumber", column = @Column(name = "catalog_number"))
    private StudentId studentId;
    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties({"student"})
    private Set<Task> tasks;
    @ManyToMany
    @JoinTable(
            name = "task",
            joinColumns = {
                    @JoinColumn(name = "student_catalog_number", referencedColumnName = "catalog_number"),
                    @JoinColumn(name = "student_clazz", referencedColumnName = "clazz")
            },
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties({"students"})
    private Set<Project> projects;

    public StudentId getStudentId() {
        return studentId;
    }

    public void setStudentId(StudentId studentId) {
        this.studentId = studentId;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}

package at.htlleonding.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@NamedQuery(name = Task.QUERY_SELECT_BY_PROJECT, query = "SELECT t FROM Task t WHERE t.project.id = :projectId")
public class Task {
    public static final String QUERY_SELECT_BY_PROJECT = "Task.SELECT.BY(PROJECT_ID)";

    @Id
    private Long id;
    private String title;
    @Column(name = "hours_worked")
    private int hoursWorked;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"tasks"})
    private Project project;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "student_catalog_number", referencedColumnName = "catalog_number"),
            @JoinColumn(name = "student_clazz", referencedColumnName = "clazz")
    })
    @JsonIgnoreProperties({"tasks"})
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
}

package at.htlleonding.model;

import jakarta.persistence.*;

@Entity
@NamedQuery(name = Task.QUERY_SELECT_BY_PROJECT, query = "SELECT t FROM Task t WHERE t.project.id = :projectId")
public class Task {
    public static final String QUERY_SELECT_BY_PROJECT = "Task.SELECT.BY(PROJECT_ID)";

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @ManyToOne
    private Student student;

    private String title;
    @Column(name = "hours_worked")
    private int hoursWorked;

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

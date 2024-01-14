package at.htlleonding.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Teacher extends Person {
    @Id
    private Long id;
    @Column(name = "room_number")
    private int roomNumber;

    @ManyToMany
    @JoinTable(
            name = "project_teacher",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    @JsonIgnoreProperties({"teachers"})
    private Set<Project> projects;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

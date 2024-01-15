package at.htlleonding.repository;

import at.htlleonding.statistics.ProjectDTO;
import at.htlleonding.statistics.StudentDTO;
import at.htlleonding.statistics.TeacherDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class ReportRepository {
    @Inject
    EntityManager entityManager;

    public double averageHoursPerTask() {
        try {
            return (double) this.entityManager.
                    createQuery("SELECT AVG(t.hoursWorked) FROM Task t")
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException("Failed to get average hours worked.");
        }
    }

    public List<TeacherDTO> teacherReportsList() {
        return this.entityManager
                .createQuery(
                        "SELECT NEW at.htlleonding.statistics.TeacherDTO(t.lastName, COUNT(p.id)) " +
                                "FROM Teacher t " +
                                "LEFT JOIN t.projects p " +
                                "GROUP BY t.id, t.lastName " +
                                "ORDER BY COUNT(p.id) DESC",
                        TeacherDTO.class)
                .getResultList();
    }

    public List<StudentDTO> hardestWorkingStudentsListWithSpecifiedLength(int amount) {
        return this.entityManager
                .createQuery(
                        "SELECT NEW at.htlleonding.statistics.StudentDTO(s.studentId.clazz, s.studentId.catalogNumber, s.lastName, SUM(t.hoursWorked)) " +
                                "FROM Student s " +
                                "LEFT JOIN Task t ON s = t.student " +
                                "GROUP BY s.studentId.clazz, s.studentId.catalogNumber, s.lastName " +
                                "ORDER BY SUM(t.hoursWorked) DESC",
                        StudentDTO.class)
                .setMaxResults(amount)
                .getResultList();
    }

    public List<ProjectDTO> projectReportsList() {
        return entityManager
                .createQuery(
                        "SELECT NEW at.htlleonding.statistics.ProjectDTO(p.name, SUM(t.hoursWorked), COUNT(DISTINCT s)) " +
                                "FROM Project p " +
                                "LEFT JOIN Task t ON p = t.project " +
                                "LEFT JOIN Student s ON t.student = s " +
                                "GROUP BY p.name ORDER BY p.name ASC",
                        ProjectDTO.class)
                .getResultList();
    }
}

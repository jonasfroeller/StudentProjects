package at.htlleonding.repository;

import at.htlleonding.model.Student;
import at.htlleonding.model.StudentId;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class StudentRepository {
    @Inject
    EntityManager entityManager;

    public List<Student> studentListByClass(String clazz) {
        return entityManager.createNamedQuery(Student.QUERY_SELECT_ALL_BY_CLASS, Student.class)
                .setParameter("clazz", clazz)
                .getResultList();
    }

    public Student studentById(StudentId id) {
        return entityManager.find(Student.class, id);
    }
}

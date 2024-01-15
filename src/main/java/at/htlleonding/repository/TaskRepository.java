package at.htlleonding.repository;

import at.htlleonding.model.Task;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class TaskRepository {
    @Inject
    EntityManager entityManager;

    public List<Task> taskListByProjectId(Long projectId) {
        return entityManager.createNamedQuery(Task.QUERY_SELECT_BY_PROJECT, Task.class)
                .setParameter("projectId", projectId)
                .getResultList();
    }

    public Task taskById(Long id) {
        return entityManager.find(Task.class, id);
    }
}

package at.htlleonding.repository;

import at.htlleonding.model.Project;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ProjectRepository {
	@Inject
	EntityManager entityManager;

	public List<Project> projectList() {
		return entityManager.createNamedQuery(Project.QUERY_SELECT_ALL, Project.class)
			.getResultList();
	}

	public Project projectById(Long id) {
		return entityManager.find(Project.class, id);
	}
}

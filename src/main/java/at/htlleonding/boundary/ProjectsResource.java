package at.htlleonding.boundary;

import at.htlleonding.model.Project;
import at.htlleonding.repository.ProjectRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/projects")
public class ProjectsResource {
	@Inject
	ProjectRepository projectRepository;

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> projectList() {
		return projectRepository.projectList();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Project projectById(@PathParam("id") Long id) {
		Project project = projectRepository.projectById(id);
		if (project == null)  {
			throw new NotFoundException("Project not found!");
		}
		return project;
	}
}

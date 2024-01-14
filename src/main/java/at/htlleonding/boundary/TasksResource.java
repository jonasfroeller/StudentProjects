package at.htlleonding.boundary;

import at.htlleonding.model.Task;
import at.htlleonding.repository.TaskRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/tasks")
public class TasksResource {

	@Inject
	TaskRepository taskRepository;

	@GET
	@Path("list/{projectId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> taskListByProjectId(@PathParam("projectId") Long projectId) {
		return taskRepository.taskListByProjectId(projectId);
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Task taskById(@PathParam("id") Long id) {
		return taskRepository.taskById(id);
	}
}

package at.htlleonding.boundary;

import at.htlleonding.repository.ReportRepository;
import at.htlleonding.statistics.ProjectDTO;
import at.htlleonding.statistics.StudentDTO;
import at.htlleonding.statistics.TeacherDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/reports")
public class ReportResource {
    @Inject
    ReportRepository reportRepository;

    @GET
    @Path("average-hours-per-task")
    @Produces(MediaType.TEXT_PLAIN)
    public double averageHoursPerTask() {
        return reportRepository.averageHoursPerTask();
    }

    @GET
    @Path("list/teacher-reports")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TeacherDTO> teacherReportsList() {
        return reportRepository.teacherReportsList();
    }

    @GET
    @Path("list/hardest-working-students/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentDTO> hardestWorkingStudentsListWithSpecifiedLength(@PathParam("amount") int amount) {
        return reportRepository.hardestWorkingStudentsListWithSpecifiedLength(amount);
    }

    @GET
    @Path("list/project-reports")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProjectDTO> projectReportsList() {
        return reportRepository.projectReportsList();
    }
}

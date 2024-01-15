package at.htlleonding.boundary;

import at.htlleonding.model.Student;
import at.htlleonding.model.StudentId;
import at.htlleonding.repository.StudentRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/students")
public class StudentResource {
    @Inject
    StudentRepository studentRepository;

    @GET
    @Path("list/{clazz}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> studentListByClass(@PathParam("clazz") String clazz) {
        return studentRepository.studentListByClass(clazz);
    }

    @GET
    @Path("{clazz}/{catalogNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student studentById(@PathParam("clazz") String clazz, @PathParam("catalogNumber") int catalogNumber) {
        Student student = studentRepository.studentById(new StudentId(clazz, catalogNumber));
        if (student == null) {
            throw new NotFoundException("Student not found!");
        }
        return student;
    }
}

package com.example.database.jpa.model.examples.one_to_many_join;

import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/jpa/task")
@Produces(MediaType.APPLICATION_JSON)
public class JpaTaskResource {

    private final TaskRepository taskRepository;

    @Inject()
    public JpaTaskResource(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Task> all() {
        return taskRepository.findAll();
    }

    @GET
    @Path("/{id}")
    public Task getById(@PathParam("id") String id) {
        return taskRepository.findById(id).get();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        taskRepository.deleteById(id);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Task task) {
        taskRepository.save(task);
        return Response.ok().build();
    }
}

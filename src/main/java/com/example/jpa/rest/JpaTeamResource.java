package com.example.jpa.rest;

import com.example.jpa.model.Team;
import com.example.jpa.repository.TeamRepository;
import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/jpa/teams")
@Produces(MediaType.APPLICATION_JSON)
public class JpaTeamResource {

    private final TeamRepository teamRepository;

    @Inject
    public JpaTeamResource(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Team> allTeams() {
        return teamRepository.findAll();
    }

    @GET
    @Path("/{id}")
    public Team getTeamById(@PathParam("id") String id) {
        return teamRepository.findById(id).get();
    }

    @GET
    @Path("/find-by-name")
    public Team findTeamByName(@QueryParam("name") String name) {
        return teamRepository.findByName(name);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTeam(@PathParam("id") String id) {
        teamRepository.deleteById(id);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveTeam(Team team) {
        teamRepository.save(team);
        return Response.ok().build();
    }

    @DELETE
    public Response deleteAll() {
        teamRepository.deleteAll();
        return Response.ok().build();
    }
}

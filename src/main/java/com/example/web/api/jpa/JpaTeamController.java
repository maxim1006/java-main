package com.example.web.api.jpa;

import com.example.constants.UrlConstants;
import com.example.database.jpa.model.Team;
import com.example.database.jpa.repository.TeamRepository;
import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(UrlConstants.API + "/jpa/teams")
@Produces(MediaType.APPLICATION_JSON)
public class JpaTeamController {
    @Inject
    TeamRepository teamRepository;

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

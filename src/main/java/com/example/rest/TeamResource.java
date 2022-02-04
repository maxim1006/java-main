package com.example.rest;

import com.example.model.Team;
import com.example.service.TeamService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/teams")
public class TeamResource {

    private final TeamService teamService;

    @Inject
    public TeamResource(TeamService teamService) {
        this.teamService = teamService;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Team> allTeams() {
        return teamService.getAllTeams();
    }
}

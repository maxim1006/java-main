package com.example.rest;

import com.example.models.Team;
import com.example.service.TeamService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.inject.Inject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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

    @GET
    @Path("/test")
    public Response test() {

        try {
            String str = "41393";

            Gson gson = new Gson();
            List<Object> objects = gson.fromJson(str,new TypeToken<List<Object>>(){}.getType());
        } catch (Exception e) {
            return null;
        }

        return Response.ok().build();
    }
}

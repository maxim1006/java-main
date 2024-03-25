package com.example.jpa.rest;

import com.example.jpa.model.TeamMember;
import com.example.jpa.repository.TeamMemberRepository;
import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// Entity:
// GET /<entity>s -> all entities
// POST /<entity>s -> create
// DELETE /<entity>s -> delete all

// GET /<entity>s/{id} -> find by id
// PUT/PATCH /<entity>s/{id} -> update entity by id
// DELETE /<entity>s/{id} -> delete by id

@Path("/jpa/team-members")
@Produces(MediaType.APPLICATION_JSON)
public class JpaTeamMembersResource {

    private final TeamMemberRepository teamMemberRepository;

    @Inject
    public JpaTeamMembersResource(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<TeamMember> allMembers() {
        return teamMemberRepository.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public TeamMember saveMember(TeamMember teamMember) {
        return teamMemberRepository.save(teamMember);
    }

    @DELETE
    @Path("")
    public Response deleteAll() {
        teamMemberRepository.deleteAll();
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TeamMember getTeamMemberById(@PathParam("id") String id) {
        return teamMemberRepository.findById(id).get();
    }

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TeamMember updateTeamMember(@PathParam("id") String id, TeamMember teamMember) {
        return teamMemberRepository.save(teamMember);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMember(@PathParam("id") String id) {
        teamMemberRepository.deleteById(id);
        return Response.noContent().build();
    }
}

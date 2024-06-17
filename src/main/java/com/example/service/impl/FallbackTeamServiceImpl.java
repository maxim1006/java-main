package com.example.service.impl;

import com.example.models.internal.Team;
import com.example.service.api.TeamService;
import io.quarkus.arc.DefaultBean;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;

@DefaultBean
@ApplicationScoped
public class FallbackTeamServiceImpl implements TeamService {

    @Override
    public List<Team> getAllTeams() {
        return Collections.emptyList();
    }
}

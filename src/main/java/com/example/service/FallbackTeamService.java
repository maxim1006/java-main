package com.example.service;

import com.example.models.internal.Team;
import io.quarkus.arc.DefaultBean;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;

@DefaultBean
@ApplicationScoped
public class FallbackTeamService implements TeamService {

    @Override
    public List<Team> getAllTeams() {
        return Collections.emptyList();
    }
}

package com.example.service.impl;

import com.example.models.internal.Team;
import com.example.models.internal.TeamMember;
import com.example.service.api.TeamService;
import jakarta.inject.Named;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Named("custom")
@ApplicationScoped
public class TeamServiceImpl implements TeamService {
    private final List<Team> TEAMS = Collections.unmodifiableList(
            List.of(
                    new Team(
                            "1",
                            "UX",
                            Collections.singletonList(
                                    new TeamMember(
                                            "mama1213",
                                            "Maxim Maximov",
                                            Arrays.asList("project1", "project2", "project3", "project4")
                                    )
                            )
                    )
            )
    );

    @Override
    public List<Team> getAllTeams() {
        return TEAMS;
    }
}

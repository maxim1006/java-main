package com.example.service;

import com.example.model.Team;
import com.example.model.TeamMember;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Named("custom")
@ApplicationScoped
public class TeamServiceImpl implements TeamService {
    private final List<Team> TEAMS = Collections.unmodifiableList(
            Arrays.asList(
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

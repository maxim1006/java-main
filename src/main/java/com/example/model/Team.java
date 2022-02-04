package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class Team {
    private String id;
    private String name;
    private List<TeamMember> members;
}

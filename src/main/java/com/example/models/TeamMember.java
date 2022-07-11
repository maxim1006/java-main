package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
public class TeamMember {
    private String id;
    private String name;
    private List<String> projects;
}

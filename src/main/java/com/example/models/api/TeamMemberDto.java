package com.example.models.api;

import lombok.*;

import java.util.List;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamMemberDto {
    private String id;
    private String name;
    private List<String> projects;
}

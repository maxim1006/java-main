package com.example.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Team {
    @Id
    private String id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL/*, fetch = FetchType.EAGER*/)
    private List<TeamMember> members;
}

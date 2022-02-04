package com.example.jpa.repository;

import com.example.jpa.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, String> {

    Team findByName(String name);
}

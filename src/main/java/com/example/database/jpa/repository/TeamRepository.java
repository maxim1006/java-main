package com.example.database.jpa.repository;

import com.example.database.jpa.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, String> {

    Team findByName(String name);
}

package com.example.database.jpa.repository;

import com.example.database.jpa.model.TeamMember;
import org.springframework.data.repository.CrudRepository;

public interface TeamMemberRepository extends CrudRepository<TeamMember, String> {

}

package com.example.jpa.repository;

import com.example.jpa.model.TeamMember;
import org.springframework.data.repository.CrudRepository;

public interface TeamMemberRepository extends CrudRepository<TeamMember, String> {

}

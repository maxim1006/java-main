package com.example.database.jpa.model.examples.one_to_many_join;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, String> {}

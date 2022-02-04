package com.example.jpa.model.examples.one_to_many_join;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Task {
    @Id
    private String id;

    private String name;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JsonIgnore
    private Project project;
}

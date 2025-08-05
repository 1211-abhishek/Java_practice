package com.example.spring_mapping.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @SequenceGenerator(name = "team_id_generator",allocationSize = 20,initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "team_id_generator")
    private int teamId;

    private String teamName;
    private String city;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "league_id")
    private League league;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Players> players;

    @OneToOne
    @JoinColumn(name = "owner_id")
    //@JsonBackReference
    private Owner owner;

}
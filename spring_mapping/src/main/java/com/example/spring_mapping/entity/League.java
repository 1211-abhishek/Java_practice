package com.example.spring_mapping.entity;

import com.example.spring_mapping.entity.constants.Country;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class League {

    @Id
    @SequenceGenerator(name = "league_id_generator",allocationSize = 10,initialValue = 101)
    @GeneratedValue(generator = "league_id_generator", strategy = GenerationType.SEQUENCE)
    private int leagueId;

    private String leagueName;

    private Country country;

    @JsonManagedReference
    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL)
    private List<Team> teams;
}

package com.example.spring_mapping.entity;

import com.example.spring_mapping.entity.constants.Country;
import com.example.spring_mapping.entity.constants.PlayerRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Players {

    @Id
    @SequenceGenerator(name = "player_id_generator", allocationSize = 10, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_id_generator")
    int playerId;

    String playerName;

    PlayerRole role;

    Country country;

    //@OneToMany(cascade = CascadeType.ALL,)
    //String team;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "team_id")
    private Team team;



}

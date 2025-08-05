package com.example.spring_mapping.entity;

import com.example.spring_mapping.entity.constants.Country;
import com.example.spring_mapping.entity.constants.PlayerRole;
import lombok.Data;

@Data
public class PlayerDTO {

    int playerId;

    String playerName;

    PlayerRole role;

    Country country;

    Team team;
}

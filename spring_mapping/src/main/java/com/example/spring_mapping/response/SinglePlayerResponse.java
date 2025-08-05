package com.example.spring_mapping.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SinglePlayerResponse {

    private int playerId;
    private String playerName;
    private String team;
    private String county;
    private String role;
}

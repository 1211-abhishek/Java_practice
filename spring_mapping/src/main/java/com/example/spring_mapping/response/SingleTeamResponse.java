package com.example.spring_mapping.response;


import com.example.spring_mapping.entity.Players;
import com.example.spring_mapping.entity.Team;
import com.example.spring_mapping.entity.constants.Country;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SingleTeamResponse {

    private int teamId;
    private String teamName;
    private String city;
    private List<Players> players;

}

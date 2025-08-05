package com.example.spring_mapping.response;


import com.example.spring_mapping.entity.Team;
import com.example.spring_mapping.entity.constants.Country;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SingleLeagueResponse {

    private int leagueId;
    private String leagueName;
    private Country country;
    private List<Team> teams;

}

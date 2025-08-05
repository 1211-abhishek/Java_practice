package com.example.spring_mapping.controller;

import com.example.spring_mapping.entity.Team;
import com.example.spring_mapping.response.SingleTeamResponse;
import com.example.spring_mapping.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/")
    public ResponseEntity<List<SingleTeamResponse>> getAllTeam() {
        List<Team> allTeam = teamService.getAllTeam();
        ResponseEntity<List<SingleTeamResponse>> listResponseEntity = new ResponseEntity<>(allTeam.stream().map(team -> new SingleTeamResponse(team.getTeamId(), team.getTeamName(), team.getCity(), team.getPlayers())).toList(), HttpStatus.OK);

        return listResponseEntity;
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<SingleTeamResponse> getAllTeam(@PathVariable int teamId) {
        Team teamDTO = teamService.getTeamById(teamId);
        ResponseEntity<SingleTeamResponse> responseEntity = new ResponseEntity<>(new SingleTeamResponse(teamDTO.getTeamId(), teamDTO.getTeamName(), teamDTO.getCity(), teamDTO.getPlayers()), HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping("/")
    public ResponseEntity<SingleTeamResponse> postTeam(@RequestBody Team team1) {

        Team savedTeam = teamService.postTeam(team1);
        return new ResponseEntity<>(new SingleTeamResponse(savedTeam.getTeamId(), savedTeam.getTeamName(), savedTeam.getCity(), savedTeam.getPlayers()), HttpStatus.CREATED);
    }
}


package com.example.spring_mapping.service;

import com.example.spring_mapping.entity.Team;
import com.example.spring_mapping.exception.TeamNotFoundException;
import com.example.spring_mapping.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public List<Team> getAllTeam(){
        return teamRepository.findAll();
    }

    public Team getTeamById(int teamId){

        return teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException("Team not found"));
    }

    public Team postTeam(Team team){

        return teamRepository.save(team);
    }

}

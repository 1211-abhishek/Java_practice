package com.example.spring_mapping.service;

import com.example.spring_mapping.entity.League;
import com.example.spring_mapping.entity.constants.Country;
import com.example.spring_mapping.exception.LeagueNotFoundException;
import com.example.spring_mapping.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {

    @Autowired
    LeagueRepository leagueRepository;

    public List<League> getAllLeague() {
        return leagueRepository.findAll();
    }

    public League getLeagueById(int leagueId) {

        return leagueRepository.findById(leagueId).orElseThrow(() -> new LeagueNotFoundException("League not found"));
    }

    public League postLeague(League league) {

        return leagueRepository.save(league);
    }

    public League putLeague(League newLeague) {

        League currentLeague = leagueRepository.findById(newLeague.getLeagueId()).orElseThrow(() -> new LeagueNotFoundException("League not found"));

        String leagueName = newLeague.getLeagueName();
        if (leagueName != null && !leagueName.isBlank()) {

            currentLeague.setLeagueName(leagueName);
        }

        Country country = newLeague.getCountry();
        if (country != null) {

            currentLeague.setCountry(country);
        }

        return leagueRepository.save(currentLeague);
    }

    public boolean deleteLeague(int leagueId) {

        League league = leagueRepository.findById(leagueId).orElseThrow(() -> new LeagueNotFoundException("League not found"));
        leagueRepository.deleteById(leagueId);
        return true;
    }
}

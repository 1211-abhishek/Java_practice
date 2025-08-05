package com.example.spring_mapping.service;

import com.example.spring_mapping.entity.PlayerDTO;
import com.example.spring_mapping.entity.Players;
import com.example.spring_mapping.exception.PlayerNotFoundException;
import com.example.spring_mapping.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public List<Players> getAllPlayers(){
        return playerRepository.findAll();
    }

    public Players getPlayerById(int playerId){

        return playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player not found"));
    }

    public Players postPlayer(Players players){

        return playerRepository.save(players);
    }
}

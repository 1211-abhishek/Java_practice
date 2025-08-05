package com.example.spring_mapping.controller;

import com.example.spring_mapping.entity.PlayerDTO;
import com.example.spring_mapping.entity.Players;
import com.example.spring_mapping.response.SinglePlayerResponse;
import com.example.spring_mapping.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/")
    public ResponseEntity<List<SinglePlayerResponse>> getAllPlayers(){
        List<Players> allPlayers = playerService.getAllPlayers();
        ResponseEntity<List<SinglePlayerResponse>> listResponseEntity = new ResponseEntity<>(allPlayers.stream().map(playerDTO -> new SinglePlayerResponse(playerDTO.getPlayerId(), playerDTO.getPlayerName(),playerDTO.getTeam().getTeamName(), playerDTO.getCountry().name(), playerDTO.getRole().name())).toList(), HttpStatus.OK);

        return listResponseEntity;
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<SinglePlayerResponse> getAllPlayers(@PathVariable int playerId){
        Players playerDTO = playerService.getPlayerById(playerId);
        ResponseEntity<SinglePlayerResponse> responseEntity = new ResponseEntity<>(new SinglePlayerResponse(playerDTO.getPlayerId(), playerDTO.getPlayerName(),playerDTO.getTeam().getTeamName(), playerDTO.getCountry().name(), playerDTO.getRole().name()), HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping("/")
    public ResponseEntity<SinglePlayerResponse> postPlayer(@RequestBody PlayerDTO playerDTO){

        Players players = new Players();
        players.setPlayerName(playerDTO.getPlayerName());
        players.setRole(playerDTO.getRole());
        players.setCountry(playerDTO.getCountry());
        players.setTeam(playerDTO.getTeam());
        Players savedPlayer = playerService.postPlayer(players);

        return  new ResponseEntity<>(new SinglePlayerResponse(savedPlayer.getPlayerId(), savedPlayer.getPlayerName(),savedPlayer.getTeam().getTeamName(), savedPlayer.getCountry().name(), savedPlayer.getRole().name()), HttpStatus.CREATED);

    }
}

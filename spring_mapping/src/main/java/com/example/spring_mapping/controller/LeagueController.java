package com.example.spring_mapping.controller;

import com.example.spring_mapping.entity.League;
import com.example.spring_mapping.response.DeleteResponse;
import com.example.spring_mapping.response.SingleLeagueResponse;
import com.example.spring_mapping.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/league")
public class LeagueController {

    @Autowired
    LeagueService leagueService;

    @GetMapping("/")
    public ResponseEntity<List<SingleLeagueResponse>> getAllLeague() {
        List<League> allLeague = leagueService.getAllLeague();
        ResponseEntity<List<SingleLeagueResponse>> listResponseEntity = new ResponseEntity<>(allLeague.stream().map(league -> new SingleLeagueResponse(league.getLeagueId(), league.getLeagueName(), league.getCountry(), league.getTeams())).toList(), HttpStatus.OK);

        return listResponseEntity;
    }

    @GetMapping("/{leagueId}")
    public ResponseEntity<SingleLeagueResponse> getAllLeague(@PathVariable int leagueId) {
        League leagueDTO = leagueService.getLeagueById(leagueId);
        ResponseEntity<SingleLeagueResponse> responseEntity = new ResponseEntity<>(new SingleLeagueResponse(leagueDTO.getLeagueId(), leagueDTO.getLeagueName(), leagueDTO.getCountry(), leagueDTO.getTeams()), HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping("/")
    public ResponseEntity<SingleLeagueResponse> postLeague(@RequestBody League league1) {

        League league = new League();
        league.setLeagueName(league1.getLeagueName());
        league.setCountry(league1.getCountry());
        League savedLeague = leagueService.postLeague(league);

        return new ResponseEntity<>(new SingleLeagueResponse(savedLeague.getLeagueId(), savedLeague.getLeagueName(), savedLeague.getCountry(), savedLeague.getTeams()), HttpStatus.CREATED);

    }

    //@PutMapping("/{leagueId}")
    @PutMapping("/")
    public ResponseEntity<SingleLeagueResponse> putLeague(@RequestBody League league) {

        League updatedLeague = leagueService.putLeague(league);

        return new ResponseEntity<>(new SingleLeagueResponse(updatedLeague.getLeagueId(), updatedLeague.getLeagueName(), updatedLeague.getCountry(),updatedLeague.getTeams()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{leagueId}")
    public ResponseEntity<DeleteResponse> deleteLeague(@PathVariable int leagueId){

        boolean status = leagueService.deleteLeague(leagueId);

        if (status){
            return new ResponseEntity<>(new DeleteResponse("Success", "Given league deleted successfully"), HttpStatus.OK);
        }
            return new ResponseEntity<>(new DeleteResponse("Unsuccessful", "Given league is not deleted"), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}


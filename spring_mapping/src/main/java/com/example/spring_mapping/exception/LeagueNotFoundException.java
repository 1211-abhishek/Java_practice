package com.example.spring_mapping.exception;

public class LeagueNotFoundException extends RuntimeException {
    public LeagueNotFoundException(String message) {
        super(message);
    }
}

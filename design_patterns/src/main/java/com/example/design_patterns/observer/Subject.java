package com.example.design_patterns.observer;

public interface Subject {

    void addMember(Observer observer);
    void removeMember(Observer observer);
    void notifyMembers(String message);
}

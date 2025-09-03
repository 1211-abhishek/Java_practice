package com.example.design_patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class WhatsAppGroup implements Subject{

    private String groupName;
    private List<GroupMember> groupMembers = new ArrayList<>();

    public WhatsAppGroup(String groupName){
        this.groupName = groupName;
    }

    @Override
    public void addMember(Observer observer) {
        groupMembers.add((GroupMember) observer);
    }

    @Override
    public void removeMember(Observer observer) {
        groupMembers.remove((GroupMember) observer);
    }

    @Override
    public void notifyMembers(String message) {
        System.out.println("notifying member in group : " + groupName);
        for (GroupMember member : groupMembers){
            member.notification(message);
        }
    }
}

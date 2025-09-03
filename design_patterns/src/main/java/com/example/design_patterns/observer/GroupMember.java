package com.example.design_patterns.observer;

public class GroupMember implements Observer{

    private String memberName;

    public GroupMember(String memberName){
        this.memberName = memberName;
    }
    @Override
    public void notification(String message) {
        System.out.println(memberName + " got message : " + message);
    }
}

package com.example.design_patterns.observer;

public class WhatsApp {

    public static void main(String[] args) {

        WhatsAppGroup whatsAppGroup = new WhatsAppGroup("Eidiko");

        GroupMember groupMember1 = new GroupMember("Abhishek");
        GroupMember groupMember2 = new GroupMember("Jayesh");

        whatsAppGroup.addMember(groupMember1);
        whatsAppGroup.addMember(groupMember2);

        whatsAppGroup.notifyMembers("Good Morning .....!");
    }
}

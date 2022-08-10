package com.example.shopping;

public class MessageUser {
    private String chats;
    private String view;
    private String date;

    public MessageUser() {

    }

    public MessageUser(String chats, String view, String date) {
        this.chats = chats;
        this.view = view;
        this.date = date;
    }

    public String getChats() {
        return chats;
    }

    public String getView() {
        return view;
    }

    public String getDate() {
        return date;
    }
}

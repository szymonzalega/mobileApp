package com.example.myapplication.dto;

import com.example.myapplication.ListActivity;

import java.util.List;

public class UserRequest {

    public UserRequest(List<User> users) {
        this.users = users;
    }

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

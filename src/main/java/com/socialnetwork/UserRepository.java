package com.socialnetwork;

import java.util.HashMap;

public class UserRepository {

    private HashMap<String,User> users = new HashMap<>();
    public boolean checkIfExists(String userName) {
        return users.containsKey(userName);
    }

    public void add(User user) {
        users.put(user.getUserName(), user);
    }
}

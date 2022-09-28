package com.socialnetwork.repos;

import java.util.HashMap;

public class UserRepository {

    private final HashMap<String,User> users = new HashMap<>();
    public boolean checkIfExists(String userName) {
        return users.containsKey(userName);
    }

    public void add(User user) {
        users.put(user.getUserName(), user);
    }

    public User get(String username) {
        return users.get(username);
    }

    public void update(User user) {
        users.replace(user.getUserName(), user);
    }
}

package com.socialnetwork.repos;

import java.util.HashMap;

public class UserInMemoryRepository implements UserRepository {

    private final HashMap<String,User> users = new HashMap<>();
    @Override
    public boolean checkIfExists(String userName) {
        return users.containsKey(userName);
    }

    @Override
    public void add(User user) {
        users.put(user.getUserName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void update(User user) {
        users.replace(user.getUserName(), user);
    }
}

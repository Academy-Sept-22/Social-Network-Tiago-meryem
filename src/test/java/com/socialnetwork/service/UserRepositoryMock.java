package com.socialnetwork.service;

import com.socialnetwork.repos.User;
import com.socialnetwork.repos.UserRepository;

public class UserRepositoryMock implements UserRepository {

    private int countUpdateCalls = 0;

    @Override
    public boolean checkIfExists(String userName) {
        if(userName.equals("Charlie") || userName.equals("Alice")){
            return true;
        }
        return false;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public User get(String username) {
        if(username.equals("Charlie") || username.equals("Alice")) {
            User user = new User(username);
            return user;
        }

        throw new IllegalArgumentException("User name not expected " + username);
    }

    @Override
    public void update(User user) {
        countUpdateCalls++;

        User aliceUser = new User("Alice");

        User charlieUserFollowingAlice = new User("Charlie");
        charlieUserFollowingAlice.follow(aliceUser);

        if(!user.equals(charlieUserFollowingAlice)) {
            throw new IllegalArgumentException("Not expected user " + user);
        }
    }

    public int verifyUpdateCalled(){
        return countUpdateCalls;
    }
}

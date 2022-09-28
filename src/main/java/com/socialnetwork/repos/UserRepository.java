package com.socialnetwork.repos;

public interface UserRepository {
    boolean checkIfExists(String userName);

    void add(User user);

    User get(String username);

    void update(User user);
}

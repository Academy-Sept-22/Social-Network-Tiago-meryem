package com.socialnetwork.repos;

import java.util.*;

public class User {

    private final String userName;

    private final Collection<User> follows = new HashSet<>();

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void follow(User userToFollow) {
        follows.add(userToFollow);
    }

    public Collection<User> follows() {
        return Collections.unmodifiableCollection(follows);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userName.equals(user.userName)) return false;
        return follows.equals(user.follows);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + follows.hashCode();
        return result;
    }
}

package com.socialnetwork.repos;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserShould {

    @Test
    void follow() {

        User user = new User("Charlie");
        User userToFollow = new User("Alice");

        user.follow(userToFollow);

        assertThat(user.follows()).contains(userToFollow);

    }
}
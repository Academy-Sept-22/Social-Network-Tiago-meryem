package com.socialnetwork.service;

import com.socialnetwork.command.Command;
import com.socialnetwork.command.CommandType;
import com.socialnetwork.repos.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FollowExecutionCommandWithMockShould {

    private UserRepositoryMock userRepository = new UserRepositoryMock();

    @Test
    void execute() {

        Command commandToExecute = new Command("Charlie",
                CommandType.FOLLOWS_COMMAND,
                "Alice");

        User charlieUser = new User("Charlie");
        User aliceUser = new User("Alice");

        userRepository.checkIfExistsWillReturn("Charlie", true);
        userRepository.checkIfExistsWillReturn("Alice", true);

        userRepository.getWillReturn("Charlie", charlieUser);
        userRepository.getWillReturn("Alice", aliceUser);

        FollowExecutionCommand executionCommand = new FollowExecutionCommand(userRepository);
        executionCommand.execute(commandToExecute);

        User charlieFollowingAlice = new User("Charlie");
        charlieFollowingAlice.follow(new User("Alice"));

        assertEquals(2, userRepository.getCountCheckIfExistsCalls());
        assertEquals(2, userRepository.getCountGetCalls());
        assertTrue(userRepository.updateWasCalledWith(charlieFollowingAlice));

    }
}

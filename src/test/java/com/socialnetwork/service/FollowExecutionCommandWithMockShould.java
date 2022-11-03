package com.socialnetwork.service;

import com.socialnetwork.command.Command;
import com.socialnetwork.command.CommandType;
import com.socialnetwork.repos.User;
import com.socialnetwork.repos.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

public class FollowExecutionCommandWithMockShould {

    private UserRepositoryMock userRepository = new UserRepositoryMock();

    @Test
    void execute() {

        Command commandToExecute = new Command("Charlie",
                CommandType.FOLLOWS_COMMAND,
                "Alice");

        FollowExecutionCommand executionCommand = new FollowExecutionCommand(userRepository);
        executionCommand.execute(commandToExecute);

        Assertions.assertEquals(1, userRepository.verifyUpdateCalled());

    }
}

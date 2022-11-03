package com.socialnetwork.service;

import com.socialnetwork.command.Command;
import com.socialnetwork.command.CommandType;
import com.socialnetwork.repos.User;
import com.socialnetwork.repos.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class FollowExecutionCommandShould {
    @Mock
    private UserRepository userRepository;

    @Test
    void execute() {

        Command commandToExecute = new Command("Bob",
                CommandType.FOLLOWS_COMMAND,
                "Alice");

        User charlieUser = new User("Charlie");
        User aliceUser = new User("Alice");

        given(userRepository.checkIfExists("Charlie")).willReturn(true);
        given(userRepository.checkIfExists("Alice")).willReturn(true);
        given(userRepository.get("Charlie")).willReturn(charlieUser);
        given(userRepository.get("Alice")).willReturn(aliceUser);

        FollowExecutionCommand executionCommand = new FollowExecutionCommand(userRepository);
        executionCommand.execute(commandToExecute);

        User charlieFollowingAlice = new User("Charlie");
        charlieFollowingAlice.follow(aliceUser);

        then(userRepository).should().update(charlieUser);
    }
}

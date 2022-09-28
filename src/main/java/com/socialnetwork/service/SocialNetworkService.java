package com.socialnetwork.service;

import com.socialnetwork.command.Command;
import com.socialnetwork.command.CommandType;
import com.socialnetwork.repos.PostRepository;
import com.socialnetwork.repos.UserRepository;
import com.socialnetwork.util.ClockService;
import com.socialnetwork.util.Console;

import java.util.HashMap;
import java.util.Optional;

public class SocialNetworkService {

    private final HashMap<CommandType, ExecutionCommand> executionCommands = new HashMap<>();

    public SocialNetworkService(UserRepository userRepository,
                                PostRepository postRepository,
                                ClockService clockService,
                                Console console) {
        this.executionCommands.put(CommandType.POST_COMMAND,
                new PostExecutionCommand(userRepository, postRepository, clockService));
        this.executionCommands.put(CommandType.READ_COMMAND,
                new ReadExecutionCommand(userRepository, postRepository, clockService, console));
        this.executionCommands.put(CommandType.FOLLOWS_COMMAND,
                new FollowExecutionCommand(userRepository));
    }

    public void execute(Command command) {
        Optional.of(executionCommands.get(command.getType())).
                ifPresent(executionCommand -> executionCommand.execute(command));
    }

}

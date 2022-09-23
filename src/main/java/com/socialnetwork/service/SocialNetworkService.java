package com.socialnetwork.service;

import com.socialnetwork.command.Command;
import com.socialnetwork.command.CommandType;
import com.socialnetwork.repos.Post;
import com.socialnetwork.repos.PostRepository;
import com.socialnetwork.repos.UserRepository;
import com.socialnetwork.util.ClockService;
import com.socialnetwork.util.Console;

import java.util.Comparator;
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
    }

    public void execute(Command command) {
        Optional<ExecutionCommand> executionCommandOpt =
                Optional.of(executionCommands.get(command.getType()));
        executionCommandOpt.ifPresent(executionCommand -> executionCommand.execute(command));
    }

    public static class RecentFirstComparator implements Comparator<Post> {
        @Override
        public int compare(Post post1, Post post2) {
            // reverse comparison
            return post2.getDateTime().compareTo(post1.getDateTime());
        }
    }
}

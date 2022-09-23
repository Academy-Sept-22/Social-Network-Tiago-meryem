package com.socialnetwork;

import java.time.LocalDateTime;
import java.util.List;

public class ReadExecutionCommand extends ExecutionCommand {
    private static final TimeDifferenceFormatter timeDifferenceFormatter = new TimeDifferenceFormatter();
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ClockService clockService;
    private final Console console;

    public ReadExecutionCommand(UserRepository userRepository,
                                PostRepository postRepository,
                                ClockService clockService,
                                Console console) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.clockService = clockService;
        this.console = console;
    }

    @Override
    public void execute(Command command) {
        if (userRepository.checkIfExists(command.getUserName())) {
            List<Post> posts = postRepository.getPosts(command.getUserName());
            LocalDateTime currentTime = clockService.getCurrentTime();
            posts.stream().sorted(new SocialNetworkService.RecentFirstComparator())
                    .forEach(post ->
                            console.printLine(post.getMessage() +
                                    " (" + timeDifferenceFormatter.formatTimeDifference(post.getDateTime(), currentTime) + ")"));
        }
    }
}
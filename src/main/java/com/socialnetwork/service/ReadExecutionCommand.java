package com.socialnetwork.service;

import com.socialnetwork.command.Command;
import com.socialnetwork.repos.Post;
import com.socialnetwork.repos.PostRepository;
import com.socialnetwork.repos.UserRepository;
import com.socialnetwork.util.ClockService;
import com.socialnetwork.util.Console;
import com.socialnetwork.util.TimeDifferenceFormatter;

import java.time.LocalDateTime;
import java.util.Comparator;
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
            posts.stream().sorted(new RecentFirstComparator())
                    .forEach(post ->
                            console.printLine(post.getMessage() +
                                    " (" + timeDifferenceFormatter.formatTimeDifference(post.getDateTime(), currentTime) + ")"));
        }
    }

    public static class RecentFirstComparator implements Comparator<Post> {
        @Override
        public int compare(Post post1, Post post2) {
            // reverse comparison
            return post2.getDateTime().compareTo(post1.getDateTime());
        }
    }
}
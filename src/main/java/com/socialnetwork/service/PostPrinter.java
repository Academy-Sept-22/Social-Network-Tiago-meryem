package com.socialnetwork.service;

import com.socialnetwork.repos.Post;
import com.socialnetwork.util.ClockService;
import com.socialnetwork.util.Console;
import com.socialnetwork.util.RecentFirstComparator;
import com.socialnetwork.util.TimeDifferenceFormatter;

import java.time.LocalDateTime;
import java.util.List;

public class PostPrinter {

    private static final TimeDifferenceFormatter timeDifferenceFormatter = new TimeDifferenceFormatter();
    public final ClockService clockService;
    public final Console console;

    public PostPrinter(ClockService clockService, Console console) {
        this.clockService = clockService;
        this.console = console;
    }

    public void printAllPosts(List<Post> posts) {
        LocalDateTime currentTime = clockService.getCurrentTime();
        posts.stream().sorted(new RecentFirstComparator())
                .forEach(post ->
                        console.printLine(post.getMessage() +
                                " (" + timeDifferenceFormatter.formatTimeDifference(post.getDateTime(), currentTime) + ")"));
    }
}
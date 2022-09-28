package com.socialnetwork.service;

import com.socialnetwork.repos.Post;
import com.socialnetwork.util.ClockService;
import com.socialnetwork.util.Console;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostPrinterShould {

    @Mock private ClockService clockService;

    @Mock private Console console;

    @Test
    void print_sorted_by_recent() {
        LocalDateTime currentDateTime = LocalDateTime.of(2022, 9, 1, 12, 0, 0);

        when(clockService.getCurrentTime()).thenReturn(currentDateTime);

        PostPrinter postPrinter = new PostPrinter(clockService, console);

        List<Post> posts = List.of(
                new Post("Alice", "I love the weather today", currentDateTime.minusMinutes(5)),
                new Post("Bob", "Damn! We lost!", currentDateTime.minusMinutes(2)),
                new Post("Bob", "Good game though.", currentDateTime.minusMinutes(1)));

        postPrinter.printAllPosts(posts);

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("Good game though. (1 minute ago)");
        inOrder.verify(console).printLine("Damn! We lost! (2 minutes ago)");
        inOrder.verify(console).printLine("I love the weather today (5 minutes ago)");

    }
}

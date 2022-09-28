package com.socialnetwork.service;

import com.socialnetwork.command.Command;
import com.socialnetwork.command.CommandType;
import com.socialnetwork.repos.Post;
import com.socialnetwork.repos.PostRepository;
import com.socialnetwork.repos.User;
import com.socialnetwork.repos.UserRepository;
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

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class WallExecutionCommandShould {

    @Mock private UserRepository userRepository;
    @Mock private PostRepository postRepository;
    @Mock private ClockService clockService;
    @Mock private Console console;

    @Test
    public void read_user_post_and_print_it_out_on_the_console(){
        LocalDateTime currentTime = LocalDateTime.of(2022, 9, 1, 12, 0, 0);
        Command commandToExecute = new Command("Charlie", CommandType.WALL_COMMAND, null);

        User aliceUser = new User("Alice");
        User userCharlie = new User("Charlie");
        userCharlie.follow(aliceUser);

        given(userRepository.checkIfExists("Charlie")).willReturn(true);
        given(userRepository.get("Charlie")).willReturn(userCharlie);

        Post charliePost = new Post("Charlie",
                "I'm in New York today! Anyone want to have a coffee?",
                currentTime.minusSeconds(2));
        Post alicePost = new Post("Alice",
                "I love the weather today",
                currentTime.minusMinutes(5));

        given(postRepository.getPosts("Charlie")).willReturn(List.of(charliePost));
        given(postRepository.getPosts("Alice")).willReturn(List.of(alicePost));

        given(clockService.getCurrentTime()).willReturn(currentTime);

        WallExecutionCommand executionCommand = new WallExecutionCommand(userRepository,
                postRepository, new PostPrinter(clockService, console));
        executionCommand.execute(commandToExecute);

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("I'm in New York today! Anyone want to have a coffee? (2 seconds ago)");
        inOrder.verify(console).printLine("I love the weather today (5 minutes ago)");
    }
}
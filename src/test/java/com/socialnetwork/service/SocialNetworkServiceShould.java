package com.socialnetwork.service;

import com.socialnetwork.command.Command;
import com.socialnetwork.command.CommandType;
import com.socialnetwork.repos.Post;
import com.socialnetwork.repos.PostRepository;
import com.socialnetwork.repos.User;
import com.socialnetwork.repos.UserRepository;
import com.socialnetwork.util.ClockService;
import com.socialnetwork.util.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class SocialNetworkServiceShould {

    private SocialNetworkService service;
    @Mock private UserRepository userRepository;
    @Mock private PostRepository postRepository;
    @Mock private ClockService clockService;
    @Mock private Console console;

    @BeforeEach
    void setUp() {
        service = new SocialNetworkService(userRepository, postRepository, clockService, console);
    }

    @Test
    void create_user_on_first_post_and_create_post_on_repository() {
        LocalDateTime currentDateTime = LocalDateTime.of(2022, 9, 1, 12, 0, 0);

        Command commandToExecute = new Command("Alice",
                CommandType.POST_COMMAND,
                "I love the weather today");

        given(userRepository.checkIfExists("Alice")).willReturn(false);
        given(clockService.getCurrentTime()).willReturn(currentDateTime);

        service.execute(commandToExecute);

        User expectedUser = new User("Alice");
        Post expectedPost = new Post("Alice",
                "I love the weather today",
                currentDateTime);

        then(userRepository).should().add(expectedUser);
        then(postRepository).should().add(expectedPost);
    }

    @Test
    public void read_user_post_and_print_it_out_on_the_console(){
        LocalDateTime currentTime = LocalDateTime.of(2022, 9, 1, 12, 0, 0);
        Command commandToExecute = new Command("Alice", CommandType.READ_COMMAND, null);
        Post expectedPost = new Post("Alice",
                "I love the weather today",
                currentTime.minusMinutes(5));
        Post expectedPost2 = new Post("Alice",
                "It's a sunny day",
                currentTime.minusSeconds(10));

        given(userRepository.checkIfExists("Alice")).willReturn(true);
        given(postRepository.getPosts("Alice")).willReturn(List.of(expectedPost, expectedPost2));
        given(clockService.getCurrentTime()).willReturn(currentTime);

        service.execute(commandToExecute);

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("It's a sunny day (10 seconds ago)");
        inOrder.verify(console).printLine("I love the weather today (5 minutes ago)");
    }
}
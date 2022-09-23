import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class SocialNetworkServiceShould {

    private SocialNetworkService service;
    @Mock private UserRepository userRepository;
    @Mock private PostRepository postRepository;
    @Mock private ClockService clockService;

    @BeforeEach
    void setUp() {
        service = new SocialNetworkService(userRepository, postRepository, clockService);
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
}
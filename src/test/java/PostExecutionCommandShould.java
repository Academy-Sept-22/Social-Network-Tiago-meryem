import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PostExecutionCommandShould {

    @Mock
    private UserRepository userRepository;
    @Mock private PostRepository postRepository;
    @Mock private ClockService clockService;

    @Test
    void execute() {

        LocalDateTime currentDateTime = LocalDateTime.of(2022, 9, 1, 12, 0, 0);

        Command commandToExecute = new Command("Alice",
                CommandType.POST_COMMAND,
                "I love the weather today");

        given(userRepository.checkIfExists("Alice")).willReturn(false);
        given(clockService.getCurrentTime()).willReturn(currentDateTime);

        PostExecutionCommand executionCommand = new PostExecutionCommand(userRepository,
                postRepository,
                clockService);
        executionCommand.execute(commandToExecute);

        User expectedUser = new User("Alice");
        Post expectedPost = new Post("Alice",
                "I love the weather today",
                currentDateTime);

        then(userRepository).should().add(expectedUser);
        then(postRepository).should().add(expectedPost);

    }
}
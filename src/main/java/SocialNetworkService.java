import java.time.LocalDateTime;

public class SocialNetworkService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ClockService clockService;

    public SocialNetworkService(UserRepository userRepository,
                                PostRepository postRepository,
                                ClockService clockService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.clockService = clockService;
    }

    public void execute(Command command) {

        if (command.getType() == CommandType.POST_COMMAND) {
            executePostCommand(command);
        }
    }

    private void executePostCommand(Command command) {

        if (!userRepository.checkIfExists(command.getUserName())) {
            userRepository.add(new User(command.getUserName()));
        }

        postRepository.add(new Post(command.getUserName(),
                command.getMessage(), clockService.getCurrentTime()));

    }
}

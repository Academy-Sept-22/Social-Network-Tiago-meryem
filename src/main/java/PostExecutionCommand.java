public class PostExecutionCommand extends ExecutionCommand {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ClockService clockService;

    public PostExecutionCommand(UserRepository userRepository,
                                PostRepository postRepository,
                                ClockService clockService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.clockService = clockService;
    }

    @Override
    public void execute(Command command) {

        if (!userRepository.checkIfExists(command.getUserName())) {
            userRepository.add(new User(command.getUserName()));
        }

        postRepository.add(new Post(command.getUserName(),
                command.getMessage(), clockService.getCurrentTime()));

    }
}
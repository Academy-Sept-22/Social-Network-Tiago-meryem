import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class SocialNetworkService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ClockService clockService;
    private final Console console;
    private final TimeDifferenceFormatter timeDifferenceFormatter = new TimeDifferenceFormatter();

    public SocialNetworkService(UserRepository userRepository,
                                PostRepository postRepository,
                                ClockService clockService,
                                Console console) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.clockService = clockService;
        this.console = console;
    }

    public void execute(Command command) {

        if (command.getType() == CommandType.POST_COMMAND) {
            executePostCommand(command);
            return;
        }
        if (command.getType() == CommandType.READ_COMMAND) {
            executeReadCommand(command);
            return;
        }
    }

    private void executeReadCommand(Command command) {

        if (userRepository.checkIfExists(command.getUserName())) {
            List<Post> posts = postRepository.getPosts(command.getUserName());
            LocalDateTime currentTime = clockService.getCurrentTime();
            posts.stream().sorted(new RecentFirstComparator())
                    .forEach(post ->
                        console.printLine(post.getMessage() +
                        " (" + timeDifferenceFormatter.formatTimeDifference(post.getDateTime(), currentTime) + ")"));
        }
    }

    private void executePostCommand(Command command) {

        if (!userRepository.checkIfExists(command.getUserName())) {
            userRepository.add(new User(command.getUserName()));
        }

        postRepository.add(new Post(command.getUserName(),
                command.getMessage(), clockService.getCurrentTime()));

    }

    public static class RecentFirstComparator implements Comparator<Post> {
        @Override
        public int compare(Post post1, Post post2) {
            // reverse comparison
            return post2.getDateTime().compareTo(post1.getDateTime());
        }
    }
}

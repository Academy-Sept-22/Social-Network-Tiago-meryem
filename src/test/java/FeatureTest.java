import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FeatureTest {
    /*>
     Alice -> I love the weather today
    > Bob -> Damn! We lost!
    > Bob -> Good game though.
    */

    /*> Alice
    I love the weather today (5 minutes ago)
    > Bob
    Good game though. (1 minute ago)
    Damn! We lost! (2 minutes ago)*/

    SocialNetworkAPI socialNetworkAPI;
    @Mock private Console console;
    @Mock private ClockService clockService;

    @BeforeEach
    public void setup(){
        SocialNetworkService socialNetworkService =
                new SocialNetworkService(new UserRepository(), new PostRepository(), clockService);
        socialNetworkAPI = new SocialNetworkAPI(new CommandParser(), socialNetworkService);
    }

    @Test
    public void post_and_read(){
        socialNetworkAPI.execute("Alice -> I love the weather today");
        socialNetworkAPI.execute("Bob -> Damn! We lost!");
        socialNetworkAPI.execute("Bob -> Good game though.");

        socialNetworkAPI.execute("Alice");
        socialNetworkAPI.execute("Bob");

        verify(console).printLine("I love the weather today (5 minutes ago)");
        verify(console).printLine("Good game though. (1 minute ago)");
        verify(console).printLine("Damn! We lost! (2 minutes ago)");

    }
}

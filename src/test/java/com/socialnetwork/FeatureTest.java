package com.socialnetwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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
                new SocialNetworkService(new UserRepository(), new PostRepository(), clockService, console);
        socialNetworkAPI = new SocialNetworkAPI(new CommandParser(), socialNetworkService);
    }

    @Test
    public void post_and_read(){
        LocalDateTime aliceFirstPostTime = LocalDateTime.of(2022, 9, 1, 12, 0, 0);
        LocalDateTime bobFirstPostTime = aliceFirstPostTime.plusMinutes(2);

        givenTheTimeIs(aliceFirstPostTime);
        socialNetworkAPI.execute("Alice -> I love the weather today");

        givenTheTimeIs(bobFirstPostTime);
        socialNetworkAPI.execute("Bob -> Damn! We lost!");
        givenTheTimeIs(bobFirstPostTime.plusMinutes(1));
        socialNetworkAPI.execute("Bob -> Good game though.");

        givenTheTimeIs(aliceFirstPostTime.plusMinutes(5));
        socialNetworkAPI.execute("Alice");

        givenTheTimeIs(bobFirstPostTime.plusMinutes(2));
        socialNetworkAPI.execute("Bob");

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("I love the weather today (5 minutes ago)");
        inOrder.verify(console).printLine("Good game though. (1 minute ago)");
        inOrder.verify(console).printLine("Damn! We lost! (2 minutes ago)");
    }

    private void givenTheTimeIs(LocalDateTime aliceFirstPostTime) {
        given(clockService.getCurrentTime()).willReturn(aliceFirstPostTime);
    }
}

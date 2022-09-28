package com.socialnetwork.util;

import com.socialnetwork.SocialNetworkAPI;
import com.socialnetwork.command.Command;
import com.socialnetwork.command.CommandType;
import com.socialnetwork.service.SocialNetworkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class SocialNetworkAPIShould {

    @Mock
    SocialNetworkService socialNetworkService;

    SocialNetworkAPI socialNetworkAPI;

    @BeforeEach
    public void setUp(){
        socialNetworkAPI = new SocialNetworkAPI(socialNetworkService);
    }


    @Test
    public void parse_post_command_and_invoke_service(){
        Command command = new Command("Alice", CommandType.POST_COMMAND, "I love the weather today");

        socialNetworkAPI.execute("Alice -> I love the weather today");

        then(socialNetworkService).should().execute(command);
    }

    @Test
    void throw_when_unknown_command() {

        assertThrows(IllegalArgumentException.class, () ->
                socialNetworkAPI.execute("Alice how are you"));
    }
}

package com.socialnetwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class SocialNetworkAPIShould {

    @Mock
    CommandParser commandParser;
    @Mock
    SocialNetworkService socialNetworkService;

    SocialNetworkAPI socialNetworkAPI;

    @BeforeEach
    public void setUp(){
        socialNetworkAPI = new SocialNetworkAPI(commandParser, socialNetworkService);
    }


    @Test
    public void parse_post_command_and_invoke_service(){
        Command command = new Command("Alice", CommandType.POST_COMMAND, "I love the weather today");

        given(commandParser.parseString("Alice -> I love the weather today")).willReturn(command);

        socialNetworkAPI.execute("Alice -> I love the weather today");

        then(socialNetworkService).should().execute(command);

    }
}

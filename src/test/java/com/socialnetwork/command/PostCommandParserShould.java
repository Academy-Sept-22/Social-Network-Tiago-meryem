package com.socialnetwork.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PostCommandParserShould {

    private PostCommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new PostCommandParser();
    }

    @Test
    void parse_post_command() {
        Command command = parser.parse("Alice -> I love the weather today");

        Command expectedCommand = new Command("Alice",
                CommandType.POST_COMMAND,
                "I love the weather today");
        assertEquals(expectedCommand, command);
    }

}
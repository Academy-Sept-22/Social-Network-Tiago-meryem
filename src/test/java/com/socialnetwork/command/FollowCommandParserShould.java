package com.socialnetwork.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FollowCommandParserShould {

    private FollowCommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new FollowCommandParser();
    }

    @Test
    void parse_follow_command() {
        Command command = parser.parse("Charlie follows Alice");

        Command expectedCommand = new Command("Charlie",
                CommandType.FOLLOWS_COMMAND,
                "Alice");
        assertEquals(expectedCommand, command);
    }

}
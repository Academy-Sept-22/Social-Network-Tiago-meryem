package com.socialnetwork.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class WallCommandParserShould {

    private WallCommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new WallCommandParser();
    }

    @Test
    void parse_wall_command() {
        Command command = parser.parse("Charlie wall");

        Command expectedCommand = new Command("Charlie",
                CommandType.WALL_COMMAND,
                null);
        assertEquals(expectedCommand, command);
    }
}
package com.socialnetwork.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ReadCommandParserShould {

    private ReadCommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new ReadCommandParser();
    }


    @Test
    void parse_read_command() {
        Command command = parser.parse("Alice");

        Command expectedCommand = new Command("Alice",
                CommandType.READ_COMMAND,
                null);
        assertEquals(expectedCommand, command);
    }

}
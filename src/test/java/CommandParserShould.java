import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommandParserShould {

    private CommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new CommandParser();
    }

    @Test
    void parse_post_command() {
        Command command = parser.parseString("Alice -> I love the weather today");

        Command expectedCommand = new Command("Alice",
                CommandType.POST_COMMAND,
                "I love the weather today");
        assertEquals(expectedCommand, command);
    }
}
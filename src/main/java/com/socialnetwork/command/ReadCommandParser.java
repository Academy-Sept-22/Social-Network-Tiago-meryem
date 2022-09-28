package com.socialnetwork.command;

public class ReadCommandParser extends CommandParser {

    public Command parse(String commandLine) {
        if (!commandLine.contains(" ")) {
            return new Command(commandLine,
                    CommandType.READ_COMMAND,
                    null);
        }
        return null;
    }
}
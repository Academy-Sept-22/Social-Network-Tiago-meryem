package com.socialnetwork.command;

public class PostCommandParser extends CommandParser {

    public Command parse(String commandLine) {
        String[] partsOfCommand = commandLine.split(" -> ");
        if (partsOfCommand.length == 2
                && !partsOfCommand[0].trim().contains(" ")) {
            return new Command(partsOfCommand[0].trim(),
                    CommandType.POST_COMMAND,
                    partsOfCommand[1]);
        }
        return null;
    }
}
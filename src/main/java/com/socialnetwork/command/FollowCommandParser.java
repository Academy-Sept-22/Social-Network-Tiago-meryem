package com.socialnetwork.command;

public class FollowCommandParser extends CommandParser {

    public Command parse(String commandLine) {
        String[] partsOfCommand = commandLine.split(" follows ");
        if (partsOfCommand.length == 2
                && !partsOfCommand[0].trim().contains(" ")
                && !partsOfCommand[1].trim().contains(" ")) {
            return new Command(partsOfCommand[0].trim(),
                    CommandType.FOLLOWS_COMMAND,
                    partsOfCommand[1].trim());
        }
        return null;
    }
}
package com.socialnetwork.command;

public class WallCommandParser extends CommandParser {

    public Command parse(String commandLine) {
        String[] partsOfCommand = commandLine.split(" ");
        if (partsOfCommand.length == 2
                && partsOfCommand[1].trim().equals("wall")) {
            return new Command(partsOfCommand[0].trim(),
                    CommandType.WALL_COMMAND,
                    null);
        }
        return null;
    }
}
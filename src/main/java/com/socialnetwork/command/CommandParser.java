package com.socialnetwork.command;

public class CommandParser {
    public Command parseString(String commandLine) {
        commandLine = commandLine.trim();

        String[] partsOfCommand = commandLine.split(" follows ");
        if (partsOfCommand.length == 2
                && !partsOfCommand[0].trim().contains(" ")
                && !partsOfCommand[1].trim().contains(" ")) {
            return new Command(partsOfCommand[0].trim(),
                    CommandType.FOLLOWS_COMMAND,
                    partsOfCommand[1].trim());
        }

        partsOfCommand = commandLine.split(" -> ");
        if (partsOfCommand.length == 2
                && !partsOfCommand[0].trim().contains(" ")) {
            return new Command(partsOfCommand[0].trim(),
                    CommandType.POST_COMMAND,
                    partsOfCommand[1]);
        }

        if(!commandLine.contains(" ")){
            return new Command(commandLine,
                    CommandType.READ_COMMAND,
                    null);
        }

        throw new IllegalArgumentException("Cannot parse " + commandLine);
    }
}

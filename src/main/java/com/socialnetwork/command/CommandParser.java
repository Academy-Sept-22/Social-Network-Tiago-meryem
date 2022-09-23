package com.socialnetwork.command;

public class CommandParser {
    public Command parseString(String commandLine) {
        if (commandLine.contains(" -> ")) {
            String[] partsOfCommand = commandLine.split(" -> ");
            return new Command(partsOfCommand[0],
                    CommandType.POST_COMMAND,
                    partsOfCommand[1]);
        }

        if(commandLine.trim().split(" ").length == 1){
            return new Command(commandLine.trim(),
                    CommandType.READ_COMMAND,
                    null);
        }
        throw new IllegalArgumentException("Cannot parse " + commandLine);
    }
}

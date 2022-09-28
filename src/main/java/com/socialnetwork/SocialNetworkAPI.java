package com.socialnetwork;

import com.socialnetwork.command.*;
import com.socialnetwork.service.SocialNetworkService;

import java.util.List;

public class SocialNetworkAPI {

    private static final List<CommandParser> PARSERS = List.of(
            new PostCommandParser(),
            new FollowCommandParser(),
            new WallCommandParser(),
            new ReadCommandParser());

    private final SocialNetworkService socialNetworkService;

    public SocialNetworkAPI(SocialNetworkService socialNetworkService) {
        this.socialNetworkService = socialNetworkService;
    }

    public void execute(String command) {
        socialNetworkService.execute(parseString(command));
    }

    public static Command parseString(String commandLine) {
        commandLine = commandLine.trim();

        Command command;
        for (CommandParser parser: PARSERS) {
            command = parser.parse(commandLine);
            if (command != null) {
                return command;
            }
        }

        throw new IllegalArgumentException("Cannot parse '" + commandLine + "'");
    }
}

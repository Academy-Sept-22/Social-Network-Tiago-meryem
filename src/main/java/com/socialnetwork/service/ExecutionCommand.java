package com.socialnetwork.service;

import com.socialnetwork.command.Command;

public abstract class ExecutionCommand {

    public abstract void execute(Command command);
}

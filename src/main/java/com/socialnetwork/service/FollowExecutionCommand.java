package com.socialnetwork.service;

import com.socialnetwork.command.Command;
import com.socialnetwork.repos.User;
import com.socialnetwork.repos.UserRepository;

public class FollowExecutionCommand extends ExecutionCommand {
    private final UserRepository userRepository;

    public FollowExecutionCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(Command commandToExecute) {

        if (userRepository.checkIfExists(commandToExecute.getUserName())
            && userRepository.checkIfExists(commandToExecute.getMessage())) {

            User userToFollow = userRepository.get(commandToExecute.getMessage());
            User user = userRepository.get(commandToExecute.getUserName());
            user.follow(userToFollow);

            userRepository.update(user);
        }

    }
}

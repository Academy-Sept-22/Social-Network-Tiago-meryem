package com.socialnetwork;

import com.socialnetwork.command.CommandParser;
import com.socialnetwork.repos.PostRepository;
import com.socialnetwork.repos.UserRepository;
import com.socialnetwork.service.PostPrinter;
import com.socialnetwork.service.SocialNetworkService;
import com.socialnetwork.util.ClockService;
import com.socialnetwork.util.Console;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        ClockService clockService = new ClockService();
        Console console = new Console();
        SocialNetworkService socialNetworkService =
                new SocialNetworkService(new UserRepository(), new PostRepository(), clockService, new PostPrinter(clockService, console));
        SocialNetworkAPI socialNetworkAPI =
                new SocialNetworkAPI(new CommandParser(), socialNetworkService);
        Scanner in = new Scanner(System.in);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Exiting...")));

        while (true) {

            System.out.print("> ");
            String readLine = in.nextLine();
            if (readLine != null && !readLine.isEmpty()) {
                socialNetworkAPI.execute(readLine);
            }

        }

    }
}

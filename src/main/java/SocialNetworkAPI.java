public class SocialNetworkAPI {


    private final CommandParser commandParser;
    private final SocialNetworkService socialNetworkService;

    public SocialNetworkAPI(CommandParser commandParser, SocialNetworkService socialNetworkService) {
        this.commandParser = commandParser;
        this.socialNetworkService = socialNetworkService;
    }

    public void execute(String command) {
        socialNetworkService.execute(commandParser.parseString(command));
    }
}

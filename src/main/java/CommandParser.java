public class CommandParser {
    public Command parseString(String commandLine) {
        if (commandLine.contains(" -> ")) {
            String[] partsOfCommand = commandLine.split(" -> ");
            return new Command(partsOfCommand[0],
                    CommandType.POST_COMMAND,
                    partsOfCommand[1]);
        }
        throw new IllegalArgumentException("Cannot parse " + commandLine);
    }
}

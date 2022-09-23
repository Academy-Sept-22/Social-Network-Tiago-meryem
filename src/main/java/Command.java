public class Command {


    private String userName;
    private CommandType type;
    private String message;

    public Command(String userName, CommandType type, String message) {
        this.userName = userName;
        this.type = type;
        this.message = message;
    }
}

import java.util.Objects;

public class Command {

    private String userName;
    private CommandType type;
    private String message;

    public Command(String userName, CommandType type, String message) {
        this.userName = userName;
        this.type = type;
        this.message = message;
    }

    public CommandType getType() {
        return this.type;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;

        if (!userName.equals(command.userName)) return false;
        if (type != command.type) return false;
        return Objects.equals(message, command.message);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}

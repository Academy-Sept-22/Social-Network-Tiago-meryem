import java.time.LocalDateTime;

public class Post {
    private final String userName;
    private final String message;
    private final LocalDateTime dateTime;

    public Post(String userName, String message, LocalDateTime dateTime) {
        this.userName = userName;
        this.message = message;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (!userName.equals(post.userName)) return false;
        if (!message.equals(post.message)) return false;
        return dateTime.equals(post.dateTime);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + dateTime.hashCode();
        return result;
    }
}

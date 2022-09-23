import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryShould {

    UserRepository userRepository;

    @BeforeEach
    public void setup(){
        userRepository = new UserRepository();
    }

    @Test
    public void user_does_not_exists_return_false(){
       assertFalse(userRepository.checkIfExists("Alice"));
    }

    @Test
    public void user_exists_return_true(){
        User user = new User("Alice");
        userRepository.add(user);
        assertTrue(userRepository.checkIfExists("Alice"));
    }

}
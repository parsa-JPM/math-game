package ir.codefather.game.helpers;

import ir.codefather.game.models.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * This class designed to provide logged in user in system
 */
@Component
public class Player {
    private User user;

    public Optional<User> getUser() {
        return Optional.ofNullable(user);
    }

    public void setUser(User user) {
        this.user = user;
    }
}

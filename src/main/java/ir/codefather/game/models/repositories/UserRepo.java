package ir.codefather.game.models.repositories;

import ir.codefather.game.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    @Query("from User u where u.username=?1 and u.password=?2")
    Optional<User> logn(String username, String password);
}

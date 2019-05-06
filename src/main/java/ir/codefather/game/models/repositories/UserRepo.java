package ir.codefather.game.models.repositories;

import ir.codefather.game.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}

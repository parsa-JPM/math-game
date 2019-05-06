package ir.codefather.game.controllers;

import ir.codefather.game.controllers.transfer_objects.UserTransfer;
import ir.codefather.game.models.User;
import ir.codefather.game.models.repositories.UserRepo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/")
    @ResponseBody
    public String checkUsername() {
        //todo check if username doesn't exist create

        return "Username exists";
    }


    @PostMapping("/user")
    @ResponseBody
    public UserTransfer createUser(@Valid UserTransfer userTransfer) {
        //todo add validation to DTO
        //todo generate token after registering
        User user = new User();
        user.setUsername(userTransfer.getUsername());
        user.setPassword(userTransfer.getPassword());
        userRepo.save(user);
        return userTransfer;
    }
}

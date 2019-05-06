package ir.codefather.game.controllers;

import ir.codefather.game.controllers.transfer_objects.UserRegisterDTO;
import ir.codefather.game.helpers.SecurityHelper;
import ir.codefather.game.models.User;
import ir.codefather.game.models.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userRepo;

    /**
     * login user to the application
     *
     * @param userTransfer data that need to Authentication
     *
     * @return User
     */
    @PostMapping("/login")
    @ResponseBody
    public String login(@Valid UserRegisterDTO userTransfer) {
        //todo make a structure class for API (ApiResponse)
        return SecurityHelper.sha1(userTransfer.getPassword());
    }


    @PostMapping("/user")
    @ResponseBody
    public UserRegisterDTO createUser(@Valid UserRegisterDTO userTransfer) {
        //todo add validation to DTO
        //todo generate token after registering
        User user = new User();
        user.setUsername(userTransfer.getUsername());
        user.setPassword(userTransfer.getPassword());
        userRepo.save(user);
        return userTransfer;
    }
}

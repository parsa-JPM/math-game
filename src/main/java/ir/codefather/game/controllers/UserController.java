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
    public UserTransfer createUser(UserTransfer userTransfer) {
        User user = new User();
        user.setUsername(userTransfer.getUsername());
        user.setPassword(userTransfer.getPassword());
        userRepo.save(user);
        return userTransfer;
    }


    @PostMapping("/test")
    @ResponseBody
    public String test() {
        User user = userRepo.findById(1).get();
        DateTime dt=new DateTime(user.getCreatedAt());
        return dt.dayOfWeek().getAsText();
    }
}

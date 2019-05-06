package ir.codefather.game.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/")
    @ResponseBody
    public String checkUsername() {
        //todo check if username doesn't exist create

        return "Username exists";
    }

}

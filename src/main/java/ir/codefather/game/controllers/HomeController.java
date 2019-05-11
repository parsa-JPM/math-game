package ir.codefather.game.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World..!!!";
    }


    @GetMapping("/test")
    @ResponseBody
    public String test() {

        return "Hellllll";
    }
}




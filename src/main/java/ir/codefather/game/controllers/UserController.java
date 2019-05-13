package ir.codefather.game.controllers;

import ir.codefather.game.ApiResponse;
import ir.codefather.game.controllers.transfer_objects.UserRegisterDTO;
import ir.codefather.game.helpers.SecurityHelper;
import ir.codefather.game.helpers.Trans;
import ir.codefather.game.models.User;
import ir.codefather.game.models.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userRepo;

    /**
     * Login user to the application
     *
     * @param userTransfer data that need to Authentication
     * @return ApiResponse
     */
    @PostMapping("/login")
    @ResponseBody
    public ApiResponse login(@Valid UserRegisterDTO userTransfer) {
        String encryptPass = SecurityHelper.sha1(userTransfer.getPassword());
        Optional<User> userOptional = userRepo.login(userTransfer.getUsername(), encryptPass);
        if (userOptional.isEmpty())
            return new ApiResponse(null)
                    .setErrorCode(HttpStatus.NOT_FOUND.value())
                    .setErrorMessage(Trans.get("users", "not-found"));
        return new ApiResponse(userOptional.orElseThrow());
    }


    /**
     * Register new user
     *
     * @param userTransfer data that need to Authentication
     * @return ApiResponse
     */
    @PostMapping("/register")
    @ResponseBody
    public ApiResponse register(@Valid UserRegisterDTO userTransfer) {
        if (isUsernameExist(userTransfer.getUsername()))
            return new ApiResponse(null)
                    .setErrorCode(HttpStatus.IM_USED.value())
                    .setErrorMessage(Trans.get("users", "already-exist"));
        User user = new User();
        user.setUsername(userTransfer.getUsername());
        String encryptPass = SecurityHelper.sha1(userTransfer.getPassword());
        user.setPassword(encryptPass);
        user.setToken(UUID.randomUUID().toString());
        userRepo.save(user);
        return new ApiResponse(user);
    }


    /**
     * Check user name already exists
     *
     * @param username unique name of users
     * @return boolean
     */
    private boolean isUsernameExist(String username) {
        return userRepo.findByUsername(username).isPresent();
    }
}

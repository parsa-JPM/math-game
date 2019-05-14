package ir.codefather.game.controllers;

import ir.codefather.game.ApiResponse;
import ir.codefather.game.controllers.transfer_objects.UserRegisterDTO;
import ir.codefather.game.helpers.Player;
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


    @Autowired
    Player player;

    /**
     * @api {post} /user/login   Login
     * @apiName Login user
     * @apiGroup User
     * @apiParam {String}  Username username of user.
     * @apiParam {String}  Password password of user.
     * @apiSuccess {User} responseObject User full info.
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     * "errorCode": null,
     * "errorMessage": null,
     * "responseObject": {
     * "id": 2,
     * "username": "parsa",
     * "password": "7C222FB2927D828AF22F592134E8932480637C0D",
     * "token": "923df645-94b6-4d52-a953-55b515576b09",
     * "invitedBy": 0,
     * "device": null,
     * "phoneNumber": null,
     * "gender": null,
     * "createdAt": "2019-05-07T13:39:58.000+0000"
     * }
     * }
     * @apiError UserNotFound The Username or Password of the User was not match or correct.
     * @apiErrorExample UserNotFound:
     * HTTP/1.1 200 OK
     * {
     * "errorCode": 404,
     * "errorMessage": "User not found",
     * "responseObject": null
     * }
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
     * @api {post} /user/register   Register
     * @apiName Register user
     * @apiGroup User
     * @apiParam {String}  Username username of user.
     * @apiParam {String}  Password password of user.
     * @apiSuccess {User} responseObject User full info.
     * @apiSuccessExample Success-Response:
     * HTTP/1.1 200 OK
     * {
     * "errorCode": null,
     * "errorMessage": null,
     * "responseObject": {
     * "id": 3,
     * "username": "parsa",
     * "password": "7C222FB2927D828AF22F592134E8932480637C0D",
     * "token": "6b71215d-daea-4448-87d9-b9fbc1f8d843",
     * "invitedBy": 0,
     * "device": null,
     * "phoneNumber": null,
     * "gender": null,
     * "createdAt": null
     * }
     * }
     * @apiError UserNameTaken The Username already exist.
     * @apiErrorExample UserNameTaken:
     * HTTP/1.1 200 OK
     * {
     * "errorCode": 226,
     * "errorMessage": "Username had been taken",
     * "responseObject": null
     * }
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


    @PostMapping("/auth/info")
    @ResponseBody
    public ApiResponse getUserInfo() {
        return new ApiResponse(player.getUser().orElse(null));
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

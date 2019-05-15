package ir.codefather.game.controllers;

import ir.codefather.game.ApiResponse;
import ir.codefather.game.helpers.Trans;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class TokenValidationResponseController {

    /**
     * error response when token is null
     *
     * @return ApiResponse
     */
    @PostMapping("/token/null")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ApiResponse notNull() {
        return new ApiResponse(null)
                .setErrorCode(HttpStatus.FORBIDDEN.value())
                .setErrorMessage(Trans.get("users", "token.null"));
    }


    /**
     * error response when token is invalid
     *
     * @return ApiResponse
     */
    @PostMapping("/token/invalid")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ApiResponse invalid() {
        return new ApiResponse(null)
                .setErrorCode(HttpStatus.FORBIDDEN.value())
                .setErrorMessage(Trans.get("users", "token.invalid"));
    }

}

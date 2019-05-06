package ir.codefather.game.controllers.transfer_objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserTransfer {

    @NotNull
    @Size(max = 30)
    private String username;

    @NotNull
    @Size(min = 8,max = 30)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

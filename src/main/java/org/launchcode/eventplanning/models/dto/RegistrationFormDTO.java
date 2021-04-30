package org.launchcode.eventplanning.models.DTO;
import org.launchcode.eventplanning.models.dto.LoginFormDTO;

public class RegistrationFormDTO extends LoginFormDTO {
    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

}

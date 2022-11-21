package pl.dawidlisowski.OrganiserApp.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterForm {
    @Pattern(regexp = "[a-zA-Z0-9]{3,20}")
    private String login;
    @Size(min = 3, max = 50)
    private String password;
    @Size(min = 3, max = 50)
    private String passwordRepeat;
    @Pattern(regexp = "[a-zA-Z]{3,25}")
    private String city;
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}")
    private String postalCode;
}

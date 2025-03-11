package be.bdus.models.auth.forms;

import be.bdus.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterForm(
        @NotBlank @Size(max = 50)
        String username,
        @NotBlank @Size(max = 150)
        String email,
        @NotBlank
        String password

) {

    public User toUser() {
        return new User(
                username,
                email,
                password

        );
    }
}

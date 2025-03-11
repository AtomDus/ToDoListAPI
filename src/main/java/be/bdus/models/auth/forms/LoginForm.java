package be.bdus.models.auth.forms;

import jakarta.validation.constraints.NotBlank;

public record LoginForm(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}

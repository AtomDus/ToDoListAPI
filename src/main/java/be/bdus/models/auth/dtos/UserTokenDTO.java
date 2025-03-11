package be.bdus.models.auth.dtos;

public record UserTokenDTO(
        UserSessionDTO user,
        String token
) {
}

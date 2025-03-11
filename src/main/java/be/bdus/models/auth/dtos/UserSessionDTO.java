package be.bdus.models.auth.dtos;

import be.bdus.entities.User;
import be.bdus.entities.enums.UserRole;

public record UserSessionDTO(
        Long id,
        UserRole role,
        String userName
) {

    public static UserSessionDTO fromUser(User user) {
        return new UserSessionDTO(user.getId(),user.getRole(),user.getUsername());
    }
}

package be.bdus.services.auth;

import be.bdus.entities.User;

public interface AuthService {

    void register(User user);

    User login(String email, String password);

}

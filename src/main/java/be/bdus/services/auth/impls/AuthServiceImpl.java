package be.bdus.services.auth.impls;

import be.bdus.entities.User;
import be.bdus.entities.enums.UserRole;
import be.bdus.exceptions.user.BadCredentialsException;
import be.bdus.exceptions.user.UserNotFoundException;
import be.bdus.repositories.UserRepository;
import be.bdus.services.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final EmailService emailService;

    @Override
    public void register(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new UserNotFoundException(HttpStatus.NOT_ACCEPTABLE, "Bad credentials");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.PARTICIPANT);
        //Todo ajouter mailer
        //emailService.sendSimpleMail(new EmailsDTO(user.getEmail(), "Votre code de validation est le suivant", "Bienvenue sur Eventrack"));
        userRepository.save(user);
    }


    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(HttpStatus.NOT_FOUND, "User with email " + email + " not found")
        );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException(HttpStatus.NOT_ACCEPTABLE, "Bad credentials");
        }
        return user;
    }

    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(HttpStatus.NOT_FOUND, email)
        );
    }
}

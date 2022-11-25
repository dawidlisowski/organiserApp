package pl.dawidlisowski.OrganiserApp.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dawidlisowski.OrganiserApp.models.UserSession;
import pl.dawidlisowski.OrganiserApp.models.entities.UserEntity;
import pl.dawidlisowski.OrganiserApp.models.forms.LoginForm;
import pl.dawidlisowski.OrganiserApp.models.forms.RegisterForm;
import pl.dawidlisowski.OrganiserApp.models.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;
    final PasswordHashingService passwordHashingService;
    final UserSession userSession;

    @Autowired
    public UserService(UserRepository userRepository, PasswordHashingService passwordHashingService, UserSession userSession) {
        this.userRepository = userRepository;
        this.passwordHashingService = passwordHashingService;
        this.userSession = userSession;
    }

    public boolean tryLogin(LoginForm loginForm) {
        Optional<UserEntity> userOptional = userRepository.getUserByLogin(loginForm.getLogin());
        if (!userOptional.isPresent()) {
            return false;
        }
        if (passwordHashingService.matches(loginForm.getPassword(), userOptional.get().getPassword())) {
            userSession.setLoggedIn(true);
            userSession.setUserEntity(userOptional.get());
            return true;
        }
        return false;
    }

    public boolean checkIfLoginExists(String login) {
        return userRepository.existsByLogin(login);
    }

    public boolean isRepeatedPasswordCorrect(RegisterForm registerForm) {
        return registerForm.getPassword().equals(registerForm.getPasswordRepeat());
    }

    public void addUser(RegisterForm registerForm) {
        registerForm.setPassword(passwordHashingService.hash(registerForm.getPassword()));
        UserEntity newUser = new UserEntity(registerForm);

        userRepository.save(newUser);
    }
}

package pl.dawidlisowski.OrganiserApp.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.dawidlisowski.OrganiserApp.models.entities.UserEntity;
import pl.dawidlisowski.OrganiserApp.models.forms.RegisterForm;
import pl.dawidlisowski.OrganiserApp.models.repositories.UserRepository;

import java.util.Optional;

@Service
public class ApiService {

    final UserRepository userRepository;

    @Autowired
    public ApiService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity getUserById(int id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            return ResponseEntity.ok(userEntityOptional.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body("User not found");
    }

    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<Object> deleteUserById(int id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity saveUser(RegisterForm user) {
        //todo check if login is free
        userRepository.save(new UserEntity(user));
        return ResponseEntity.ok().build();
    }
}

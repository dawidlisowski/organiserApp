package pl.dawidlisowski.OrganiserApp.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dawidlisowski.OrganiserApp.models.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE login = ?1")
    Optional<UserEntity> getUserByLogin(String login);

    boolean existsByLogin(String login);
}

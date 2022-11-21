package pl.dawidlisowski.OrganiserApp.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dawidlisowski.OrganiserApp.models.entities.NoteEntity;

@Repository
public interface NoteRepository extends CrudRepository<NoteEntity, Integer> {
}

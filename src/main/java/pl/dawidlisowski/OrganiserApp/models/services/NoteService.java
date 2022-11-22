package pl.dawidlisowski.OrganiserApp.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dawidlisowski.OrganiserApp.models.UserSession;
import pl.dawidlisowski.OrganiserApp.models.entities.NoteEntity;
import pl.dawidlisowski.OrganiserApp.models.forms.NoteForm;
import pl.dawidlisowski.OrganiserApp.models.repositories.NoteRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NoteService {
    final UserSession userSession;
    final NoteRepository noteRepository;
    @Autowired
    public NoteService(UserSession userSession, NoteRepository noteRepository) {
        this.userSession = userSession;
        this.noteRepository = noteRepository;
    }
    private LocalDate convertStringToLocalDate(String stringDate) {
        LocalDate dateFromUser = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyy-MM-dd"));
        return dateFromUser;
    }

    public void addNote(NoteForm noteForm) {
        NoteEntity newNote = new NoteEntity();
        newNote.setNoteDate(convertStringToLocalDate(noteForm.getNoteDate()));
        newNote.setTitle(noteForm.getTitle());
        newNote.setText(noteForm.getText());
        newNote.setUser(userSession.getUserEntity());

        noteRepository.save(newNote);
    }

    public List<NoteEntity> getNotesForToday() {
        List<NoteEntity> notesList = noteRepository.noteListForToday(
                userSession.getUserEntity().getLogin(),
                LocalDate.now());
        return notesList;
    }
}

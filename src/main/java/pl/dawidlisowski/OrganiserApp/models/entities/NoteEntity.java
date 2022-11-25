package pl.dawidlisowski.OrganiserApp.models.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dawidlisowski.OrganiserApp.models.UserSession;
import pl.dawidlisowski.OrganiserApp.models.forms.NoteForm;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "note")
@NoArgsConstructor
public class NoteEntity {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private @Column(name = "note_date") LocalDate noteDate;

    private String title;
    private String text;

    public NoteEntity(NoteForm noteForm, UserSession userSession, LocalDate noteDate) {
        user = userSession.getUserEntity();
        title = noteForm.getTitle();
        text = noteForm.getText();
        this.noteDate = noteDate;
    }

}

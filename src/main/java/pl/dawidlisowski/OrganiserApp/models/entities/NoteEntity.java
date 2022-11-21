package pl.dawidlisowski.OrganiserApp.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "note")
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

}

package pl.dawidlisowski.OrganiserApp.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue
    private int id;

    private String login;
    private String password;
    private String city;
    private @Column(name = "postal_code") String postalCode;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<NoteEntity> notesList;
}

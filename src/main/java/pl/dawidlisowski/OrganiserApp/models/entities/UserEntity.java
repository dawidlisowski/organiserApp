package pl.dawidlisowski.OrganiserApp.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dawidlisowski.OrganiserApp.models.forms.RegisterForm;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue
    private int id;

    private String login;
    @JsonIgnore
    private String password;
    private String city;
    private @Column(name = "postal_code") String postalCode;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<NoteEntity> notesList;

    public UserEntity(RegisterForm registerForm) {
        login = registerForm.getLogin();
        password = registerForm.getPassword();
        city = registerForm.getCity();
        postalCode = registerForm.getPostalCode();
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}

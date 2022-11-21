package pl.dawidlisowski.OrganiserApp.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class NoteForm {
    @Pattern(regexp = "[0-9]{4}-[0-1][0-9]-[0-3][0-9]")
    private String noteDate;
    @Size(min = 3, max = 50)
    private String title;
    @Size(min = 3, max = 250)
    private String text;
}

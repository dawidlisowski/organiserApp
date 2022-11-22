package pl.dawidlisowski.OrganiserApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.dawidlisowski.OrganiserApp.models.UserSession;
import pl.dawidlisowski.OrganiserApp.models.dtos.WeatherDto;
import pl.dawidlisowski.OrganiserApp.models.forms.NoteForm;
import pl.dawidlisowski.OrganiserApp.models.services.NoteService;
import pl.dawidlisowski.OrganiserApp.models.services.WeatherService;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class IndexController {

    final UserSession userSession;
    final WeatherService weatherService;
    final NoteService noteService;

    @Autowired
    public IndexController(UserSession userSession, WeatherService weatherService, NoteService noteService) {
        this.userSession = userSession;
        this.weatherService = weatherService;
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String showMainPage(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/user/login";
        }

        WeatherDto weatherDto = weatherService.loadWeatherFor(userSession.getUserEntity().getCity());

        model.addAttribute("login", userSession.getUserEntity().getLogin());
        model.addAttribute("notes", noteService.getNotesForToday());
        model.addAttribute("weather", (int) weatherDto.getTempDto().getTemperature() - 273);
        model.addAttribute("clouds", (int) weatherDto.getCloudsDto().getClouds());
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("city", userSession.getUserEntity().getCity());

        return "index";
    }

    @GetMapping("/note/add")
    public String showNoteForm(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/user/login";
        }
        model.addAttribute("noteForm", new NoteForm());
        return "noteForm";
    }

    @PostMapping("/note/add")
    public String getNote(@ModelAttribute @Valid NoteForm noteForm,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("noteInfo", "Date is incorrect");
        }

        noteService.addNote(noteForm);
        return "redirect:/";
    }
}

package pl.dawidlisowski.OrganiserApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.dawidlisowski.OrganiserApp.models.UserSession;
import pl.dawidlisowski.OrganiserApp.models.dtos.WeatherDto;
import pl.dawidlisowski.OrganiserApp.models.services.WeatherService;

import java.time.LocalDate;

@Controller
public class IndexController {

    final UserSession userSession;
    final WeatherService weatherService;

    @Autowired
    public IndexController(UserSession userSession, WeatherService weatherService) {
        this.userSession = userSession;
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String showMainPage(Model model) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/user/login";
        }

        WeatherDto weatherDto = weatherService.loadWeatherFor(userSession.getUserEntity().getCity());

        model.addAttribute("login", userSession.getUserEntity().getLogin());
        model.addAttribute("weather", (int) weatherDto.getTempDto().getTemperature() - 273);
        model.addAttribute("clouds", (int) weatherDto.getCloudsDto().getClouds());
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("city", userSession.getUserEntity().getCity());

        return "index";
    }
}

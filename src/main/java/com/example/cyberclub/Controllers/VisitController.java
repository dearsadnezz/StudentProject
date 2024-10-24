package com.example.cyberclub.Controllers;

import com.example.cyberclub.Data.Computer;
import com.example.cyberclub.Data.Guest;
import com.example.cyberclub.Data.Visit;
import com.example.cyberclub.Repository.ComputerRepository;
import com.example.cyberclub.Repository.GuestRepository;
import com.example.cyberclub.Repository.VisitRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Visit")
@Controller
@SessionAttributes({"visits", "guests", "computers", "account"})
public class VisitController {
    private final VisitRepository visitRepo;
    private final ComputerRepository computerRepo;
    private final GuestRepository guestsRepo;

    @Autowired
    public VisitController(VisitRepository visitRepo, ComputerRepository computerRepo, GuestRepository guestsRepo) {
        this.visitRepo = visitRepo;
        this.computerRepo = computerRepo;
        this.guestsRepo = guestsRepo;
    }

    @GetMapping
    public String showVisits(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("visit", new Visit());
        Iterable<Visit> visits = visitRepo.findAll();
        Iterable<Computer> computers = computerRepo.findAll();
        Iterable<Guest> guests = guestsRepo.findAll();
        model.addAttribute("visits", visits);
        model.addAttribute("computers", computers);
        model.addAttribute("guests", guests);
        model.addAttribute("account", userDetails==null? "Вход" : userDetails.getUsername());
        return "Visit/Visit";
    }

    @PostMapping(value = "/create")
    public String createVisit(@Valid @ModelAttribute("visit") Visit visit, BindingResult result, Model model) {
        if (result.hasErrors()) {return "Visit/Visit";}
        else {
            Computer computer = computerRepo.findById(visit.getComputer().getId()).get();
            visit.setPay(visit.getTime() * computer.getPlan());
            visitRepo.save(visit);
            return "redirect:/Visit";
        }
    }

    @PostMapping(value = "/delete")
    public String deleteVisit(@ModelAttribute("visit") Visit visit) {
        visitRepo.deleteById(visit.getId()); return "redirect:/Visit";
    }

    @PostMapping(value = "/update")
    public String updateVisit(@ModelAttribute("visit") Visit visit, Model model) {
        model.addAttribute("visit", visit);
        model.addAttribute("visits", visitRepo.findAll());
        model.addAttribute("guests", guestsRepo.findAll());
        model.addAttribute("computers", computerRepo.findAll());
        return "Visit/VisitUpdate";
    }

    @PostMapping(value = "/save")
    public String saveVisit(@Valid @ModelAttribute("visit") Visit visit, BindingResult result) {
        if (result.hasErrors()) {return "Visit/VisitUpdate";}
        else {
            Computer computer = computerRepo.findById(visit.getComputer().getId()).get();
            visit.setPay(visit.getTime() * computer.getPlan());
            visitRepo.save(visit);
            return "redirect:/Visit";
        }
    }
}

package com.example.cyberclub.Controllers;

import com.example.cyberclub.Data.Guest;
import com.example.cyberclub.Repository.GuestRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Guest")
@Controller
@SessionAttributes({"guests","account"})
public class GuestController {
    private final GuestRepository guestsRepo;

    @Autowired
    public GuestController(GuestRepository guestsRepo) {this.guestsRepo = guestsRepo;}

    @GetMapping
    public String showGuests(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Guest guest = new Guest();
        model.addAttribute("guest", guest);
        Iterable<Guest> guests = guestsRepo.findAll();
        model.addAttribute("guests", guests);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername());
        return "Guest/Guest";
    }

    @PostMapping(value = "/create")
    public String createGuest(@Valid @ModelAttribute("guest") Guest guest, BindingResult result) {
        if (result.hasErrors()) {return "Guest/Guest";}
        else{guestsRepo.save(guest);return "redirect:/Guest";}
    }

    @PostMapping(value = "/delete")
    public String deleteGuest(@ModelAttribute(value = "var") Guest guest) {
        guestsRepo.deleteById(guest.getId()); return "redirect:/Guest";
    }

    @PostMapping(value = "/update")
    public String updateGuest(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute(value = "guest") Guest guest, Model model) {
        model.addAttribute("guest", guest);
        Iterable<Guest> guests = guestsRepo.findAll();
        model.addAttribute("guests", guests);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername());
        return "Guest/GuestUpdate";
    }

    @PostMapping(value = "/save")
    public String saveGuest(@Valid @ModelAttribute(value = "guest") Guest guest, BindingResult result) {
        if (result.hasErrors()) {return "Guest/GuestUpdate";}
        else{guestsRepo.save(guest); return "redirect:/Guest";}
    }
}

package com.example.cyberclub.Controllers;

import com.example.cyberclub.Data.Status;
import com.example.cyberclub.Repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import jakarta.validation.Valid;

@RequestMapping("/Status")
@Controller
@SessionAttributes({"statuses", "account"})
public class StatusController {
    private final StatusRepository statusRepo;

    @Autowired
    public StatusController(StatusRepository statusRepo) {this.statusRepo = statusRepo;}

    @GetMapping
    public String showStatuses(@AuthenticationPrincipal UserDetails userdetails, Model model) {
        Status status = new Status();
        model.addAttribute("status", status);
        Iterable<Status> statuses = statusRepo.findAll();
        model.addAttribute("statuses", statuses);
        model.addAttribute("account", userdetails==null ? "Вход" : userdetails.getUsername());
        return "Status/Status";
    }

    @PostMapping(value = "/create")
    public String createStatus(@Valid @ModelAttribute(value = "status") Status status, BindingResult result) {
        if (result.hasErrors()) {return "Status/Status";}
        else {statusRepo.save(status); return "redirect:/Status";}
    }

    @PostMapping(value = "/delete")
    public String deleteStatus(@ModelAttribute(value = "var") Status status) {
        statusRepo.deleteById(status.getId()); return "redirect:/Status";
    }

    @PostMapping(value = "/update")
    public String updateStatus(@AuthenticationPrincipal UserDetails userdetails, @ModelAttribute(value = "status") Status status, Model model) {
        model.addAttribute("status", status);
        Iterable<Status> statuses = statusRepo.findAll();
        model.addAttribute("statuses", statuses);
        model.addAttribute("account", userdetails==null ? "Вход" : userdetails.getUsername());
        return "Status/StatusUpdate";
    }   

    @PostMapping(value = "/save")
    public String saveStatus(@Valid @ModelAttribute(value = "status") Status status, BindingResult result) {
        if (result.hasErrors()) {return "Status/StatusUpdate";}
        statusRepo.save(status);
        return "redirect:/Status";
    }

}


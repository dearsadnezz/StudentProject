package com.example.cyberclub.Controllers;

import com.example.cyberclub.Data.Computer;
import com.example.cyberclub.Data.Status;
import com.example.cyberclub.Repository.ComputerRepository;
import com.example.cyberclub.Repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/Computer")
@Controller
@SessionAttributes({"computers", "statuses", "account"})
public class ComputerController {
    private final ComputerRepository computerRepo;
    private final StatusRepository statusRepo;

    @Autowired
    public ComputerController(ComputerRepository computerRepo, StatusRepository statusRepo)
    {
        this.computerRepo = computerRepo;
        this.statusRepo = statusRepo;
    }

    @GetMapping
    public String getComputer(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Computer computer = new Computer();
        model.addAttribute("computer", computer);
        Iterable<Computer> computers = computerRepo.findAll();
        Iterable<Status> statuses = statusRepo.findAll();
        model.addAttribute("computers", computers);
        model.addAttribute("statuses", statuses);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername());
        return "Computer/Computer";
    }

    @PostMapping(value = "/create")
    public String createComputer(@Valid @ModelAttribute(value = "computer") Computer computer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {return "Computer/Computer";}
        else {
            computerRepo.save(computer); return "redirect:/Computer";
        }
    }

    @PostMapping(value = "/delete")
    public String deleteComputer(@ModelAttribute(value = "var") Computer computer) {
        computerRepo.deleteById(computer.getId()); return "redirect:/Computer";
    }

    @PostMapping(value = "/update")
    public String updateComputer(@ModelAttribute(value = "computer") Computer computer,Model model) {
        model.addAttribute("computer", computer);
        model.addAttribute("statuses", statusRepo.findAll());
        model.addAttribute("statuses", statusRepo.findAll());
        return "Computer/ComputerUpdate";
    }

    @PostMapping(value = "/save")
    public String saveComputer(@Valid @ModelAttribute(value = "computer") Computer computer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "Computer/ComputerUpdate";}
        else {computerRepo.save(computer); return "redirect:/Computer";}
    }
}

package com.beautify.demo.controllers;

import com.beautify.demo.objects.BookingType;
import com.beautify.demo.objects.City;
import com.beautify.demo.objects.Saloon;
import com.beautify.demo.objects.User;
import com.beautify.demo.services.BookingServiceImpl;
import com.beautify.demo.services.CityServiceImpl;
import com.beautify.demo.services.SaloonServiceImpl;
import com.beautify.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Transactional
public class SaloonController {

    @Autowired
    private SaloonServiceImpl saloonService;

    @Autowired
    private UserService userService;

    @Autowired
    private CityServiceImpl cityService;

    @Autowired
    private BookingServiceImpl bookingService;

    @GetMapping("/saloons")
    public String showSaloons(Authentication authentication, Model model, City city){

        model.addAttribute("user", userService.getSpecificUser(authentication));
        model.addAttribute("saloon", saloonService.getSaloons());
        model.addAttribute("city", cityService.getCities());
        return "saloons";
    }

    @GetMapping("/saloon/")
    public String showSpecificSaloon(@RequestParam long id, Authentication authentication, Model model) throws RuntimeException {

        Optional<Saloon> saloon = saloonService.getSpecificSaloon(id);

        if(saloon.isEmpty()){
            throw new RuntimeException("Error");
        }

        model.addAttribute("user", userService.getSpecificUser(authentication));
        model.addAttribute("saloonName", saloon.get().getName());
        model.addAttribute("saloonCity", saloon.get().getCity().getName());
        model.addAttribute("saloonOwner", saloon.get().getOwner());
        model.addAttribute("saloonDesc", saloon.get().getDescription());

        model.addAttribute("saloonServices", saloon.get().getServices());
        return "saloonPage";
    }

    @GetMapping("/owner/dashboard")
    public String showOwnerDashboard(Authentication authentication, Model model){

        User user = userService.getSpecificUser(authentication);

        model.addAttribute("user", user);

        model.addAttribute("saloon", user.getSaloon());

        model.addAttribute("service", new BookingType());

        return "ownerdashboard";
    }

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Authentication authentication, Model model){
        model.addAttribute("user", userService.getSpecificUser(authentication));
        model.addAttribute("saloon", saloonService.getSaloons());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("addSaloon", new Saloon());
        model.addAttribute("city", cityService.getCities());
        model.addAttribute("owner", userService.getOwners());

        return "admindashboard";
    }

    @PostMapping("/add")
    public String addSaloon(@ModelAttribute("saloon") Saloon saloon){
        saloonService.addSaloon(saloon);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/addService")
    public String addService(@RequestParam long saloonId, @ModelAttribute("service") BookingType service){

        Optional<Saloon> saloon = saloonService.getSpecificSaloon(saloonId);


        if(saloon.isEmpty()){
            throw new RuntimeException("SaloonError");
        }

        service.setSaloon(saloon.get());
        saloon.get().addService(service);


        return "redirect:/owner/dashboard";
    }


    @PostMapping("/setOwner")
    public String setOwner(@RequestParam long saloonId, @RequestParam long ownerId) throws RuntimeException {

        Optional<Saloon> saloon = saloonService.getSpecificSaloon(saloonId);

        if(saloon.isEmpty()){
            throw new RuntimeException("SaloonError");
        }

        Optional<User> user = userService.getSpecificUserById(ownerId);

        if(user.isEmpty()){
            throw new RuntimeException("UserError");
        }

        saloon.get().setOwner(user.get());

        return "redirect:/admin/dashboard";
    }

    @GetMapping("/deleteSaloon")
    public String deleteSaloon(@RequestParam long id){
        saloonService.deleteSaloon(id);
        return "redirect:/admin/dashboard";
    }

    /*@GetMapping("/saloons/filter")
    public String filterSaloonsByCity(@RequestParam int id, Model model, City city){
        model.addAttribute("city", cityService.getCities());
        model.addAttribute("saloon", saloonService.filterByCity(id));
        return "saloons";
    }*/

}

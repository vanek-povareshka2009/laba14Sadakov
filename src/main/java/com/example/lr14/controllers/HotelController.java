package com.example.lr14.controllers;

import com.example.lr14.entities.Hotel;
import com.example.lr14.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/hotels")
@SessionAttributes({"name", "passport", "room"})
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @ModelAttribute("name")
    public String initName() {
        return "";
    }

    @ModelAttribute("passport")
    public String initPassport() {
        return "";
    }

    @ModelAttribute("room")
    public String initRoom() {
        return "";
    }

    @GetMapping
    public String showHotelList(@ModelAttribute("name") String name,
                                @ModelAttribute("passport") String passport,
                                @ModelAttribute("room") String room,
                                Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels(name, passport, room));
        return "hotel";
    }

    @GetMapping("/filter")
    public String filterHotels(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "passport", required = false) String passport,
                               @RequestParam(value = "room", required = false) String room,
                               Model model) {
        model.addAttribute("name", name);
        model.addAttribute("passport", passport);
        model.addAttribute("room", room);
        model.addAttribute("hotels", hotelService.getAllHotels(name, passport, room));
        return "hotel";
    }

    @GetMapping("/show/{id}")
    public String showOneHotel(Model model, @PathVariable(value = "id") Integer id) {
        Hotel hotel = hotelService.getById(id);
        model.addAttribute("hotel", hotel);
        return "resident-info";
    }

    @GetMapping("/delete/{id}")
    public String deleteHotel(@PathVariable(value = "id") Integer id) {
        Hotel hotel = hotelService.getById(id);
        if (hotel != null) {
            hotelService.delete(hotel);
        }
        return "redirect:/hotels";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "add-resident";
    }

    @PostMapping("/add")
    public String addHotel(@ModelAttribute("hotel") Hotel hotel) {
        hotelService.add(hotel);
        return "redirect:/hotels";
    }

    @GetMapping("/edit/{id}")
    public String editHotel(Model model, @PathVariable(value = "id") Integer id) {
        Hotel hotel = hotelService.getById(id);
        model.addAttribute("hotel", hotel);
        return "edit-resident";
    }

    @PostMapping("/edit")
    public String updateHotel(@ModelAttribute("hotel") Hotel updatedHotel) {
        Hotel existingHotel = hotelService.getById(updatedHotel.getId());
        if (existingHotel != null) {
            hotelService.update(existingHotel, updatedHotel);
        }
        return "redirect:/hotels";
    }


    @GetMapping("/resetFilter")
    public String resetFilter(SessionStatus status) {
        status.setComplete();
        return "redirect:/hotels";
    }
}

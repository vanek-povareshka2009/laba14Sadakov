package com.example.lr14.services;

import com.example.lr14.entities.Hotel;
import com.example.lr14.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService { // Изменено название сервиса

    private final HotelRepository repository;

    @Autowired
    public HotelService(HotelRepository repository) {
        this.repository = repository;
    }

    public Hotel getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<Hotel> getAllHotels() { // Изменено название метода
        return repository.findAll();
    }

    public List<Hotel> getAllHotels(String name, String passport, String room) { // Изменено название метода и параметра
        return repository.findAll().stream()
                .filter(o -> name.isBlank() || o.getName().contains(name))
                .filter(o -> passport.isBlank() || o.getPassport().contains(passport))
                .filter(o -> room.isBlank() || o.getRoom().contains(room))
                .collect(Collectors.toList());
    }

    public void add(Hotel hotel) { // Изменено название параметра
        repository.save(hotel);
    }

    public void delete(Hotel hotel) { // Изменено название параметра
        repository.delete(hotel);
    }

    public void update(Hotel exist, Hotel updated) {
        if (!updated.getName().isBlank()) exist.setName(updated.getName());
        if (!updated.getPassport().isBlank()) exist.setPassport(updated.getPassport());
        if (!updated.getPhone().isBlank()) exist.setPhone(updated.getPhone());
        if (!updated.getRoom().isBlank()) exist.setRoom(updated.getRoom());
        repository.save(exist);
    }
}

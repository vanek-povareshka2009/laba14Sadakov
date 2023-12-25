package com.example.lr14.repositories;

import com.example.lr14.entities.Hotel; // Изменили на Hotel
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> { // Изменили название интерфейса на HotelRepository

}
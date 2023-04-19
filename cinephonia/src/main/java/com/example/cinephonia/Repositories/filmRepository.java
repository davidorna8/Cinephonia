package com.example.cinephonia.Repositories;

import com.example.cinephonia.Models.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface filmRepository extends JpaRepository<Film,Long> {
}

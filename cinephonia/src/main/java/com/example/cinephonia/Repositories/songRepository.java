package com.example.cinephonia.Repositories;

import com.example.cinephonia.Models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface songRepository extends JpaRepository<Song,Long> {
}

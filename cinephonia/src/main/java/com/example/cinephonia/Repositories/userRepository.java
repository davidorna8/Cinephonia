package com.example.cinephonia.Repositories;

import com.example.cinephonia.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User,Long> {
}

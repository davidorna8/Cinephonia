package com.example.cinephonia.Repositories;

import com.example.cinephonia.Models.Cover;
import com.example.cinephonia.Models.Film;
import com.example.cinephonia.Models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface filmRepository extends JpaRepository<Film,Long> {
    /*List<Film> findFilmById(long id);
    List<Film> findFilmByCover(Cover cover);
    List<Film> findFilmByFilmUser(User user);
    List<Film> findByGenre(String genre);
    List<Film> findFirst10ByDirector(String director, Sort sort);
    int countByGenre (String genre);
    List<Film> queryByYearAndDirector(String year, String director);*/
}

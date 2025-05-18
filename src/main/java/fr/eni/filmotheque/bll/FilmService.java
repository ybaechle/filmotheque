package fr.eni.filmotheque.bll;

import java.util.List;
import java.util.Optional;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.exceptions.FilmNotFound;

public interface FilmService {
	
	List<Film> findAllFilms();
	Film findFilmById(int idFilm) throws FilmNotFound;
	void saveFilm(Film film);

}

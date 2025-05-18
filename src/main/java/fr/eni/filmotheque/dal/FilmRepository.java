package fr.eni.filmotheque.dal;

import java.util.List;
import java.util.Optional;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Participant;

public interface FilmRepository {
	
	List<Film> findAllFilms();
	Optional<Film> findFilmById(int idFilm);
	void saveFilm(Film film);
	int countFilmWithGenre(int idGenre);
	List<Participant> findActeursByFilm(int idFilm);
}

package fr.eni.filmotheque.dal;

import java.util.List;
import java.util.Optional;

import fr.eni.filmotheque.bo.Genre;

public interface GenreRepository {
	

	List<Genre> findAllGenres();
	
	Optional<Genre> findGenreById(int id);

	void save(Genre genre);
	
	void delete(int idGenre);
	
}

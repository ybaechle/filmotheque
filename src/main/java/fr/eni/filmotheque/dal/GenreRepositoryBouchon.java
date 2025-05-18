package fr.eni.filmotheque.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import fr.eni.filmotheque.bo.Genre;

@Repository
public class GenreRepositoryBouchon implements GenreRepository{

	private List<Genre> genres ;
	
	public GenreRepositoryBouchon() {
		genres = new ArrayList<>();
		genres.add(new Genre(1, "Animation"));
		genres.add(new Genre(2, "Aventure"));
		genres.add(new Genre(3, "Comédie"));
		genres.add(new Genre(4, "Horreur"));
		
	}
	
	@Override
	public List<Genre> findAllGenres() {
		return genres;
	}

	@Override
	public Optional<Genre> findGenreById(int id) {
		return genres.stream().filter(genre->genre.getId()==id).findFirst();
	}

	@Override
	public void save(Genre genre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int idGenre) {
		// TODO Auto-generated method stub
		
	}

}

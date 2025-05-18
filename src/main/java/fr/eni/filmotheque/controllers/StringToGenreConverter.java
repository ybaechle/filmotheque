package fr.eni.filmotheque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.dal.GenreRepository;

@Component
public class StringToGenreConverter implements Converter<String, Genre>{

	@Autowired
	private GenreRepository genreRepo;
	
	@Override
	public Genre convert(String idGenre) {
		int id = 0;
		
		try {
			id= Integer.parseInt(idGenre);
		}catch(NumberFormatException nfe) {			
			//TODO : cas idGenre pas entier
			nfe.printStackTrace();
		}
		
		return genreRepo.findGenreById(id).orElse(null);
	}

}

package fr.eni.filmotheque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.filmotheque.bll.FilmService;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.exceptions.FilmNotFound;
import jakarta.validation.Valid;

@Controller
public class FilmController {

	private FilmService filmService;
	
	public FilmController(FilmService filmService) {
		super();
		this.filmService = filmService;
	}

	@GetMapping("/film")
	public String afficherFilm(@RequestParam("id") String id, Model modele) {
		
		int idFilm;
		
		try {
			idFilm = Integer.parseInt(id);
		}catch(NumberFormatException nfe) {
			
			return "redirect:/erreur";
		}
		
		Film film =null;
		try {
			film = filmService.findFilmById(idFilm);
		} catch (FilmNotFound e) {
			System.out.println(e.getMessage());
			return "redirect:/erreur";
		}
		
		modele.addAttribute("film", film);
		
		return "film";
	}

	@GetMapping({"/", "/films", "/filmotheque"})
	public String afficherFilms(Model modele) {
		
		modele.addAttribute("films", filmService.findAllFilms());
		
		return "films";
	}
	
	@GetMapping("/ajoutFilm")
	public String afficherNouveauFilm(Model modele) {
		modele.addAttribute("film", new Film());
		return "ajout-film";
	}
	
	@PostMapping("/enregistrerFilm")
	public String enregistrerFilm(@Valid @ModelAttribute("film") Film film
			,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "ajout-film";
		}
		filmService.saveFilm(film);
		return "redirect:/films";
	}

	@GetMapping("/login")
	public String login(Model modele) {
		
		return "login";
	}

	
	@GetMapping("/filmLogout")
	public String logout(Model modele) {
		
		return "redirect:/films";
	}

}

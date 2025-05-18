package fr.eni.filmotheque.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.filmotheque.bll.GenreService;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.exceptions.GenreNotFound;
import fr.eni.filmotheque.exceptions.SuppressionGenreImpossible;


@Controller
@RequestMapping("/genres")
public class GenreController {

	private final GenreService genreService;

	public GenreController(GenreService genreService) {
		this.genreService = genreService;
	}

//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.registerCustomEditor(Genre.class, new GenrePropertyEditor(genreService));
//	}

	@GetMapping
	public String listGenres(Model model) {
		List<Genre> genres = genreService.findAllGenres();
		model.addAttribute("genres", genres);
		model.addAttribute("genre", new Genre()); // Pour le formulaire d'ajout/nouvelle modification
		return "genres";
	}

	@PostMapping("/edit")
	public String editGenre(@ModelAttribute Genre genre) {
		genreService.saveGenre(genre);
		return "redirect:/genres";
	}

	@GetMapping("/edit")
	public String editGenreForm(@RequestParam(name = "id", required = false) Integer id, Model model)
			throws GenreNotFound {
		// La logique pour afficher le formulaire de modification est incluse dans la
		// méthode listGenres
		List<Genre> genres = genreService.findAllGenres();
		model.addAttribute("genres", genres);
		if (id != null) {
			Genre existingGenre = genreService.findGenreById(id);
			model.addAttribute("genre", existingGenre);
		}
		return "genres";
	}

	@GetMapping("/delete")
	public String deleteGenre(@RequestParam(name = "id") Integer idGenre) throws GenreNotFound, SuppressionGenreImpossible {
		// logique pour supprimer un genre
		genreService.deleteGenre(idGenre);
		return "redirect:/genres";
	}

}
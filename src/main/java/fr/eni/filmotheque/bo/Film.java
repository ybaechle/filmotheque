package fr.eni.filmotheque.bo;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Film {
	private Integer id;
	
	@NotBlank
	private String titre;
	
	@NotNull
	@Min(1800)
	private Integer anneeSortie;
	
	@Pattern(regexp = "|.{10,}",message = "Doit soit être vide soit contenir plus de 10 caractères")
	private String synopsis;
	
	@NotNull
	private Genre genre;
	
	@NotNull
	@Min(0)
	private Integer duree;
	
	@NotNull
	private Participant realisateur;
	
	private List<Participant> acteurs;
	
	public Film() {
		this.acteurs = new ArrayList<>();
	}
	

	public Film(String titre, int anneeSortie, String synopsis
			, Genre genre
			,int duree
			,Participant realisateur) {
		this();
		this.titre = titre;
		this.anneeSortie = anneeSortie;
		this.synopsis = synopsis;
		this.genre = genre;
		this.realisateur = realisateur;
		this.duree = duree;
	}
	
	public Film(int id, String titre, int anneeSortie, String synopsis, Genre genre
			,int duree, Participant realisateur) {
		this(titre, anneeSortie, synopsis, genre, duree, realisateur);
		this.id = id;
	}
	
	public void addActeur(Participant acteur) {
		acteurs.add(acteur);
	}

	public Participant getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(Participant realisateur) {
		this.realisateur = realisateur;
	}

	/* Methodes */
	public String getSynopsisCourt() {
		return synopsis.length()>40?synopsis.substring(0, 40):synopsis;
	}
	
	
	/* Getters et setters */
	public Integer getId() {
		return id;
	}

	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Integer getAnneeSortie() {
		return anneeSortie;
	}

	
	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}


	public Integer getDuree() {
		return duree;
	}


	public void setDuree(Integer duree) {
		this.duree = duree;
	}


	public List<Participant> getActeurs() {
		return acteurs;
	}


	public void setActeurs(List<Participant> acteurs) {
		this.acteurs = acteurs;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setAnneeSortie(Integer anneeSortie) {
		this.anneeSortie = anneeSortie;
	}


	public void addActeurs(Participant... participants) {
		for(Participant p: participants) {
			acteurs.add(p);
		}
		
	}

}

package fr.eni.filmotheque.bo;

public class Genre {
	private Integer id;
	private String libelle;
	
	public Genre() {
		// TODO Auto-generated constructor stub
	}
	
	public Genre(Integer id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	public Genre(String libelle) {
		this.libelle = libelle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	@Override
	public String toString() {
		return "Genre [id=" + id + ", libelle=" + libelle + "]";
	}
	
	
}

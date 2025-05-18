package fr.eni.filmotheque.bo;

public class Participant {
	private int id;
	private String nom;
	private String prenom;
	
	public Participant() {
		// TODO Auto-generated constructor stub
	}
	
	public Participant( String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}
	

	public Participant(int id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	@Override
	public String toString() {
		return "Participant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}


		
	
}

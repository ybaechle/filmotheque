package fr.eni.filmotheque.bo;

public class Membre {

	private Integer id;
	private String pseudo;
	private String motDePasse;
	private boolean isAdmin;
	
	public Membre() {
	}
	
	public Membre(Integer id, String pseudo, String motDePasse, boolean isAdmin) {
		this.id = id;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.isAdmin = isAdmin;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "Membre [id=" + id + ", pseudo=" + pseudo + ", motDePasse=" + motDePasse + ", isAdmin=" + isAdmin + "]";
	}
	
}

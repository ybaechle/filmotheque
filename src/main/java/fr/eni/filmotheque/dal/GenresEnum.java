package fr.eni.filmotheque.dal;

public enum GenresEnum {
	
	
	ANIMATION(1), AVENTURE(2), COMEDIE(3), HORREUR(4);
	
	private final int id;
	
	private GenresEnum (int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
}

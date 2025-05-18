package fr.eni.filmotheque.bll;

import java.util.Optional;

import fr.eni.filmotheque.bo.Membre;

public interface MembreService {
	
	Optional<Membre> findMembreByPseudo(String pseudo);

}

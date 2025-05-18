package fr.eni.filmotheque.dal;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;

import fr.eni.filmotheque.bo.Membre;

public interface MembreRepository {
	
	
	
	public Optional<Membre> findMembreByPseudo(String pseudo) ;

}

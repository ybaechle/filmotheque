package fr.eni.filmotheque.bll;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.dal.MembreRepository;

@Service
public class MembreServiceImpl implements MembreService{
	
	private MembreRepository membreRepository;
	
	public MembreServiceImpl(MembreRepository membreRepository) {
		this.membreRepository = membreRepository;
	}


	@Override
	public Optional<Membre> findMembreByPseudo(String pseudo) {
		
		return membreRepository.findMembreByPseudo(pseudo);
	}

}

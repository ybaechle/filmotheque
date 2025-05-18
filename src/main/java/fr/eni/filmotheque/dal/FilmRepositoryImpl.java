package fr.eni.filmotheque.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Participant;

@Repository
@Primary
public class FilmRepositoryImpl implements FilmRepository {

	private static String SELECT_FILM = 
			"select f.id as filmid, titre, annee_sortie, duree, "
			+ " synopsis, genre_id, realisateur_id,"
			+ " libelle, nom, prenom "
			+ " from films f inner join genres g "
			+ "	on  g.id = f.genre_id "
			+ "	inner join participants p "
			+ "	on p.id = f.realisateur_id ";
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public FilmRepositoryImpl(NamedParameterJdbcTemplate namedJdbcTemplate,
			ParticipantRepository participantRepo) {
		this.namedJdbcTemplate = namedJdbcTemplate;
		this.jdbcTemplate = namedJdbcTemplate.getJdbcTemplate();
	}

	
	private class FilmRowMapper implements RowMapper<Film> {

		@Override
		public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
			Film film = new Film();
			film.setId(rs.getInt("filmid"));
			film.setTitre(rs.getString("titre"));
			film.setAnneeSortie(rs.getInt("annee_sortie"));
			film.setDuree(rs.getInt("duree"));
			film.setSynopsis(rs.getString("synopsis"));
			
			Genre genre = new Genre(rs.getInt("genre_id"), 
					                rs.getString("libelle"));
			
			film.setGenre(genre);
			
			Participant realisateur = new Participant(
					                     rs.getInt("realisateur_id"),
					                     rs.getString("nom"),
					                     rs.getString("prenom")
					                     );
			film.setRealisateur(realisateur);
			
			return film;

		}

	}
	
	@Override
	public List<Film> findAllFilms() {
		
		List<Film> films = jdbcTemplate.query(SELECT_FILM, new FilmRowMapper());
		
		return films;
	}

	
	public List<Participant> findActeursByFilm(int idFilm){
		String sql = "select p.id as id, nom, prenom "
				+ "from participants p inner join acteurs a "
				+ " on p.id = a.participant_id and a.film_id = ?";
		
		List<Participant> acteurs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Participant>(Participant.class), idFilm);
		
		return acteurs;
	}
	
	@Override
	public Optional<Film> findFilmById(int idFilm) {
		String sql = SELECT_FILM + " where f.id=?";
		
		Optional<Film> optFilm  ;
		
		try {
			Film film = jdbcTemplate.queryForObject(sql, new FilmRowMapper(), idFilm);
			optFilm = Optional.of(film);
		}catch(EmptyResultDataAccessException exc) {
			optFilm = Optional.empty();
		}

		
		return optFilm;
	}

	@Override	
	public void saveFilm(Film film) {
		String sql = "insert into films (titre, annee_sortie, duree, synopsis, genre_id, realisateur_id) "
				+ " values (:titre, :annee_sortie, :duree, :synopsis, :genre_id, :realisateur_id)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("titre", film.getTitre());
		parameters.addValue("annee_sortie", film.getAnneeSortie());
		parameters.addValue("duree", film.getDuree());
		parameters.addValue("synopsis", film.getSynopsis());
		parameters.addValue("genre_id", film.getGenre().getId());
		parameters.addValue("realisateur_id", film.getRealisateur().getId());
		
		namedJdbcTemplate.update(sql, parameters, keyHolder ,new String[] { "id"} );

		film.setId(keyHolder.getKey().intValue());
				
		if(film.getActeurs().size()>0) {
			sql = "insert into acteurs (film_id, participant_id) values (:film_id, :participant_id)";
			
			MapSqlParameterSource[] acteursParameters = new MapSqlParameterSource[film.getActeurs().size()];
			for(int i=0;i<film.getActeurs().size();i++) {
				acteursParameters[i]=new MapSqlParameterSource();
				acteursParameters[i].addValue("film_id", film.getId());
				acteursParameters[i].addValue("participant_id", film.getActeurs().get(i).getId());
			}
			
			namedJdbcTemplate.batchUpdate(sql, acteursParameters);
			
		}
		
		
	}

	@Override
	public int countFilmWithGenre(int idFilm) {
		String sql = "select count(*) from films where genre_id=?";
		
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idFilm);
		
		return count;
	}
	
	


}

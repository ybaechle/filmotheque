package fr.eni.filmotheque.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.filmotheque.bo.Genre;

@Repository
@Primary
public class GenreRepositoryImpl implements GenreRepository {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public GenreRepositoryImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
		this.jdbcTemplate = namedJdbcTemplate.getJdbcTemplate();
	}

	@Override
	public List<Genre> findAllGenres() {
		String sql = "select id, libelle from genres";

		List<Genre> genres = jdbcTemplate.query(sql, new GenreRowMapper());
		return genres;
	}

	@Override
	public Optional<Genre> findGenreById(int id) {

		String sql = "select id, libelle from genres where id = ?";
		
		Optional<Genre> optGenre  ;
		
		try {
			Genre genre = jdbcTemplate.queryForObject(sql, new GenreRowMapper(), id);
			optGenre = Optional.of(genre);
		}catch(EmptyResultDataAccessException exc) {
			optGenre = Optional.empty();
		}
		
		return optGenre;
	}

	// classe interne
	private class GenreRowMapper implements RowMapper<Genre> {

		@Override
		public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Genre(rs.getInt("id"), rs.getString("libelle"));

		}

	}

	@Override
	public void save(Genre genre) {
		
		if (genre.getId()==null) {
			String sql = "insert into Genres (libelle) values (:libelle)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("libelle", genre.getLibelle());
			
			namedJdbcTemplate.update(sql, parameters, keyHolder ,new String[] { "id"} );
			
			genre.setId(keyHolder.getKey().intValue());
			
		}else {
			String sql = "update Genres set libelle=? where id=?";
			jdbcTemplate.update(sql, genre.getLibelle(), genre.getId());
		}
		
	}

	@Override
	public void delete(int idGenre) {
		String sql = "delete from  Genres where id=?";
		jdbcTemplate.update(sql, idGenre);
		
	}

}

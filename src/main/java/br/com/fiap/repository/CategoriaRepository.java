package br.com.fiap.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.CategoriaModel;

@Repository
public class CategoriaRepository {

	private static final String GET_ALL = "SELECT * FROM TB_CATEGORIA";
	private static final String GET = "SELECT * FROM TB_CATEGORIA WHERE ID_CATEGORIA = ?";
	private static final String SAVE = "INSERT INTO TB_CATEGORIA (NOME_CATEGORIA) VALUES (?)";
	private static final String UPDATE = "UPDATE TB_CATEGORIA SET NOME_CATEGORIA = ?  WHERE ID_CATEGORIA = ?";
	private static final String DELETE = "DELETE FROM TB_CATEGORIA WHERE ID_CATEGORIA = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<CategoriaModel> findAll() {
		return jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<CategoriaModel>(CategoriaModel.class));
	}

	public CategoriaModel findById(long id) {

		CategoriaModel produto = jdbcTemplate.queryForObject(GET,
				new BeanPropertyRowMapper<CategoriaModel>(CategoriaModel.class), id);

		return produto;
	}
	
	public void save(CategoriaModel categoriaModel) {
		jdbcTemplate.update(SAVE, categoriaModel.getNomeCategoria());
	}
	
	public void update(CategoriaModel categoriaModel) {
		jdbcTemplate.update(UPDATE, 
							categoriaModel.getNomeCategoria(),
							categoriaModel.getIdCategoria());
	}
	
	public void delete(long id) {
		jdbcTemplate.update(DELETE, id);
	}
}

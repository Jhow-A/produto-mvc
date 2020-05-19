package br.com.fiap.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.ProdutoModel;

@Repository
public class ProdutoRepository {

	private static final String GET_ALL = "SELECT * FROM TB_PRODUTO P INNER JOIN TB_CATEGORIA C ON ( P.ID_CATEGORIA = C.ID_CATEGORIA)";
	private static final String GET = "SELECT * FROM TB_PRODUTO WHERE ID = ?";
	private static final String SAVE = "INSERT INTO TB_PRODUTO (NOME, SKU, DESCRICAO, CARACTERISTICAS, PRECO, ID_CATEGORIA) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE TB_PRODUTO SET NOME = ?, SKU = ?, DESCRICAO = ?, CARACTERISTICAS = ?, PRECO = ? WHERE ID = ?";
	private static final String DELETE = "DELETE FROM TB_PRODUTO WHERE ID = ?";
	
	@Autowired // Injeta a dependencia do Spring do tipo JdbcTemplate, ele mesmo busca a instância
	private JdbcTemplate jdbcTemplate;
	
	public ProdutoRepository() {

	}

	public List<ProdutoModel> findAll() {

		List<ProdutoModel> produtos = this.jdbcTemplate.query(GET_ALL, new ProdutoRowMapper());
		return produtos;
	}

	public ProdutoModel findById(long id) {

		ProdutoModel produto = this.jdbcTemplate.queryForObject(GET, new BeanPropertyRowMapper<ProdutoModel>(ProdutoModel.class), id);
		return produto;
	}

	public void save(ProdutoModel produtoModel) {
		this.jdbcTemplate.update(SAVE, 
				produtoModel.getNome(), 
				produtoModel.getSku(), 
				produtoModel.getDescricao(),
				produtoModel.getCaracteristicas(), 
				produtoModel.getPreco(),
				produtoModel.getCategoria().getIdCategoria());
	}

	public void update(ProdutoModel produtoModel) {
		this.jdbcTemplate.update(UPDATE, 
				produtoModel.getNome(), 
				produtoModel.getSku(), 
				produtoModel.getDescricao(),
				produtoModel.getCaracteristicas(), 
				produtoModel.getPreco(), 
				produtoModel.getId());
	}

	public void deleteById(long id) {
		this.jdbcTemplate.update(DELETE, id);
	}
}

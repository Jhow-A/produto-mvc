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

	@Autowired // Injeta a dependencia do Spring do tipo JdbcTemplate
	private JdbcTemplate jdbcTemplate;
	private static Map<Long, ProdutoModel> produtos;

	public ProdutoRepository() {

	}

	public List<ProdutoModel> findAll() {

		List<ProdutoModel> produtos = jdbcTemplate.query("SELECT * FROM TB_PRODUTO",
				new BeanPropertyRowMapper<ProdutoModel>(ProdutoModel.class));
		return produtos;
	}

	public ProdutoModel findById(long id) {
		return produtos.get(id);
	}

	public void save(ProdutoModel produto) {
		jdbcTemplate.update("INSERT INTO TB_PRODUTO (NOME, SKU, DESCRICAO, PRECO, CARACTERISTICAS) VALUES (?,?,?,?,?)",
				produto.getNome(), produto.getSku(), produto.getDescricao(), produto.getPreco(),
				produto.getCaracteristicas());

	}

	public void update(ProdutoModel produto) {
		produtos.put(produto.getId(), produto);
	}

	public void deleteById(long id) {
		produtos.remove(id);
	}
}

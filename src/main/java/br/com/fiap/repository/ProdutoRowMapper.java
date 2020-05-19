package br.com.fiap.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import br.com.fiap.model.CategoriaModel;
import br.com.fiap.model.ProdutoModel;

public class ProdutoRowMapper implements RowMapper<ProdutoModel>{

	@Override
	public ProdutoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ProdutoModel produto = new BeanPropertyRowMapper<ProdutoModel>(ProdutoModel.class).mapRow(rs, rowNum);
		CategoriaModel categoria = new BeanPropertyRowMapper<CategoriaModel>(CategoriaModel.class).mapRow(rs, rowNum);
		
		produto.setCategoria(categoria);
		
		return produto;
	}
				
}

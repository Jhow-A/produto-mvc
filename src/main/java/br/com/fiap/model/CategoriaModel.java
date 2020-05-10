package br.com.fiap.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoriaModel {
	private Long idCategoria;
	
	@NotBlank(message = "Por favor, preencha o nome")
	@Size(min = 3, max = 20, message = "Tamanho tem que ser de 3 até 20 caracteres!!")
	private String nomeCategoria;

	public CategoriaModel() {
	}

	public CategoriaModel(Long idCategoria, String nomeCategoria) {
		super();
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
}

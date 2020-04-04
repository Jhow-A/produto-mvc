package br.com.fiap.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	private ProdutoRepository produtoRepository = new ProdutoRepository();
	
	// Busca
	@RequestMapping(value = "/produto", method = RequestMethod.GET)
	public String findAll(Model model) {
		
		List<ProdutoModel> listaDosProdutos = produtoRepository.findAll();
		
		model.addAttribute("produtos", listaDosProdutos);
		
		return "produtos";
	}
	
	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public String findById(@PathVariable("id") long id, Model model) {
		
		ProdutoModel produtoEncontrado = produtoRepository.findById(id);
		
		model.addAttribute("produto", produtoEncontrado);
		
		return "produto-detalhe";
	}
	
	@RequestMapping(value = "/produto/new", method = RequestMethod.GET)
	public String form() {
		return "produto-novo";
	}
	
	@RequestMapping(value = "/produto/new", method = RequestMethod.POST)
	public String save(ProdutoModel produtoModel) {
		
		produtoRepository.save(produtoModel);
		
		return "produto-novo-sucesso";
	}
	
	@RequestMapping(value = "/produto/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") long id, Model model) {
		
		model.addAttribute("produto", produtoRepository.findById(id));
		
		return "produto-editar";
	}
	
	@RequestMapping(value = "/produto/update", method = RequestMethod.POST)
	public String updateProduto(ProdutoModel produtoModel, Model model) {
		
		produtoRepository.update(produtoModel);
		
		model.addAttribute("produtos", produtoRepository.findAll());
		
		return "produtos";
	}
	
	@RequestMapping(value = "/produto/delete/{id}")
	public String deleteProduto(@PathVariable("id") long id, Model model) {
		
		produtoRepository.delete(id);			
		
		model.addAttribute("produtos", produtoRepository.findAll());
		
		return "produtos";
	}

}

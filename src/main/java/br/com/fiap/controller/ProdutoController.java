package br.com.fiap.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	private ProdutoRepository produtoRepository = ProdutoRepository.getInstance();
	
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
	public String save(ProdutoModel produtoModel, RedirectAttributes redirectAttributes ) {
		
		produtoRepository.save(produtoModel);
		redirectAttributes.addFlashAttribute("messages", "Produto cadastrado com sucesso");
		
		return "redirect:/produto";
	}
	
	@RequestMapping(value = "/produto/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") long id, Model model) {
		
		model.addAttribute("produto", produtoRepository.findById(id));
		
		return "produto-editar";
	}
	
	@RequestMapping(value = "/produto/update", method = RequestMethod.POST)
	public String updateProduto(ProdutoModel produtoModel, RedirectAttributes redirectAttributes) {
		
		produtoRepository.update(produtoModel);
		redirectAttributes.addFlashAttribute("messages", "Produto alterado com sucesso");
		
		return "redirect:/produto";
	}
	
	@RequestMapping(value = "/produto/delete/{id}", method = RequestMethod.DELETE)
	public String deleteProduto(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		
		produtoRepository.deleteById(id);			
		
		// Sem mais necessidade, pois há um redirect	
		//model.addAttribute("produtos", produtoRepository.findAll());
		
		redirectAttributes.addFlashAttribute("messages", "Produto deletado com sucesso");
		
		return "redirect:/produto";
	}

}
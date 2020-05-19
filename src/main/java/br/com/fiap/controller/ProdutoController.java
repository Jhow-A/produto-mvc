package br.com.fiap.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.CategoriaRepository;
import br.com.fiap.repository.ProdutoRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	// ABERTURA DE FORMULÁRIO
	@GetMapping("/form")
	public String open(@RequestParam String page, @RequestParam(required = false) Long id,
			@ModelAttribute("produtoModel") ProdutoModel produtoModel, Model model) {

		// page.equals("produto-editar")
		if ("produto-editar".equals(page)) {
			model.addAttribute("produto", produtoRepository.findById(id));
		}
		
		model.addAttribute("categorias", categoriaRepository.findAll());

		return page;
	}
	
	// CONSULTAS
	@GetMapping()
	public String findAll(Model model) {

		List<ProdutoModel> listaDosProdutos = produtoRepository.findAll();

		model.addAttribute("produtos", listaDosProdutos);

		return "produtos";
	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@GetMapping(value = "/{id}")
	public String findById(@PathVariable("id") long id, Model model) {

		ProdutoModel produtoEncontrado = produtoRepository.findById(id);

		model.addAttribute("produto", produtoEncontrado);

		return "produto-detalhe";
	}

	// INSERÇÕES E ALTERAÇÕES
	@PostMapping()
	public String save(@Valid ProdutoModel produtoModel, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "produto-novo";
		}

		produtoRepository.save(produtoModel);
		redirectAttributes.addFlashAttribute("messages", "Produto cadastrado com sucesso");

		return "redirect:/produto";
	}

	@PutMapping()
	public String updateProduto(@PathVariable("id") long id, @Valid ProdutoModel produtoModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
			return "produto-editar";
		}
		
		produtoModel.setId(id);
		produtoRepository.update(produtoModel);
		redirectAttributes.addFlashAttribute("messages", "Produto alterado com sucesso!");

		return "redirect:/produto";
	}

	// EXCLUSÕES
	@DeleteMapping("/{id}")
	public String deleteProduto(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {

		produtoRepository.deleteById(id);

		// Sem mais necessidade, pois há um redirect
		// model.addAttribute("produtos", produtoRepository.findAll());

		redirectAttributes.addFlashAttribute("messages", "Produto deletado com sucesso");

		return "redirect:/produto";
	}

}

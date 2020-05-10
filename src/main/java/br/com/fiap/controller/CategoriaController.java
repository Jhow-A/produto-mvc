package br.com.fiap.controller;

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

import br.com.fiap.model.CategoriaModel;
import br.com.fiap.repository.CategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;

	@GetMapping("/form")
	public String openForm(@RequestParam String page,
							@RequestParam(required = false) Long id,
							@ModelAttribute("categoriaModel") CategoriaModel categoriaModel,
							Model model) {
		
		if ("categoria-editar".equals(page)) {
			model.addAttribute("categoriaModel", repository.findById(id));
		}
		
		return page;
	}
	
	@GetMapping()
	public String findAll(Model model) {

		model.addAttribute("categorias", repository.findAll());

		return "categorias";
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable Long id, Model model) {

		CategoriaModel categoriaModel = repository.findById(id);
		model.addAttribute("categoria", categoriaModel);

		return "categoria-detalhe";
	}
	
	@PostMapping
	public String save(@Valid CategoriaModel categoriaModel, 
						BindingResult bindingResult,
						RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return "categoria-nova";
		}
		
		repository.save(categoriaModel);
		redirectAttributes.addFlashAttribute("messages", "Categoria salva com sucesso!");
		
		return "redirect:/categoria";		
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id,
						 @Valid CategoriaModel categoriaModel,
						 BindingResult bindingResult,
						 RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return "categoria-editar";
		}
		
		categoriaModel.setIdCategoria(id);
		repository.update(categoriaModel);
		
		redirectAttributes.addFlashAttribute("messages", "Categoria atualizada com sucesso!");
		
		return "redirect:/categoria";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		
		repository.delete(id);
		redirectAttributes.addFlashAttribute("messages", "Categoria removida com sucesso!");
		
		return "redirect:/categoria";
	}

}

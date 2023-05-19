package br.edu.atitus.pooavancado.CadUsuario.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.pooavancado.CadUsuario.entities.Produto;
import br.edu.atitus.pooavancado.CadUsuario.services.ProdutoService;


@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

	final ProdutoService produtoService;
	
	public ProdutoController(ProdutoService produtoService) {
		super();
		this.produtoService = produtoService;
	}

	@PostMapping
	public ResponseEntity<Object> postProduto(@RequestBody Produto produto){
		try {
			produtoService.save(produto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(produto);
	}
	
	@GetMapping
	public ResponseEntity<Object> getUsuarios(@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable paginacao,  
			@RequestParam(required = false) String nome, @RequestParam(required = false) String email){
		try {
			Page<Produto> lista = produtoService.findByNome(nome, paginacao);
			return ResponseEntity.status(HttpStatus.OK).body(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUsuarioById(@PathVariable long id){
		Produto produto;
		try {
			produto = this.produtoService.findById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(produto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> putProduto(@PathVariable long id, @RequestBody Produto produto){
		produto.setId(id);
		try {
			produtoService.save(produto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(produto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUsuario(@PathVariable long id) {
		try {
			produtoService.deleteById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso!");
	}
}
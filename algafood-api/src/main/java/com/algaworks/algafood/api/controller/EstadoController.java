package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRespository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@GetMapping
	public List<Estado> listar() {
		return estadoRespository.findAll();
	}
	
	@GetMapping("/{id}")
	public Estado findById(@PathVariable Long id){
		return cadastroEstado.findById(id); 

	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado salvar( @RequestBody Estado estado) {
		return estadoRespository.save(estado);
	}
	
	@PutMapping("/{id}")
	public Estado atualizar(@PathVariable Long id,
							@RequestBody Estado estado){
		
		Estado estadoAtual = cadastroEstado.findById(id);
			
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		 
		return cadastroEstado.salvar(estadoAtual);
		
	}
	

	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		
			cadastroEstado.excluir(id);
			
	} 
}

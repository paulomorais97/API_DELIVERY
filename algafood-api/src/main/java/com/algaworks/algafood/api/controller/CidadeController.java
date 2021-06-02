package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegociosException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@GetMapping
	public List<Cidade> search() {
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Cidade findById(@PathVariable Long id){
		return cadastroCidade.findById(id);
	}
	
	@PostMapping
	public Cidade salvar(@RequestBody Cidade cidade){
		try {
			return cidade = cadastroCidade.salvar(cidade);
		} catch(EstadoNaoEncontradoException e) {
			throw new NegociosException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	public Cidade atualizar(@RequestBody Cidade cidade,
							@PathVariable Long id){
					
		try {
			Cidade cidadeAtual = cadastroCidade.findById(id);		
			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			return cadastroCidade.salvar(cidadeAtual);
			
		} catch(EstadoNaoEncontradoException e) {
				throw new NegociosException(e.getMessage(), e);
		  }
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id){
			cadastroCidade.excluir(id);
	}
	
}

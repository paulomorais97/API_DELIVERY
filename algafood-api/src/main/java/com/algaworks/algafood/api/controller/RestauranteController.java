package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@GetMapping
	public List<Restaurante>search(){
		return restauranteRepository.findAll();
		
	}
	@GetMapping("/{id}")
	public Restaurante findById(@PathVariable Long id){
		return cadastroRestaurante.findById(id);
		
	}
	
	
	@PostMapping
	// O "?" é um coringa, aceita dar um responseEntity sendo que o corpo pode se de qualquer tipo
	public Restaurante salvar(@RequestBody Restaurante restaurante){
		return cadastroRestaurante.salvar(restaurante);
		
	}
	
	@PutMapping("/{id}")
	public Restaurante atualizar( @PathVariable Long id,
			@RequestBody Restaurante restaurante){
		
	
			Restaurante restauranteAtual = cadastroRestaurante.findById(id);
		
			BeanUtils.copyProperties(restaurante, restauranteAtual,
					"id", "formaPagamento", "endereco", "dataCadastro", "produtos");
			
			return cadastroRestaurante.salvar(restauranteAtual);
		
	}
	@PatchMapping("/{id}/partial")
	public Restaurante atualizarParcial(@PathVariable Long id,
										@RequestBody Map<String, Object> campos){
		
		Restaurante restauranteAtual = cadastroRestaurante.findById(id);
		
		merge(campos, restauranteAtual);
		
		return atualizar(id, restauranteAtual);
		
		
	}
	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();//instanciando ObjectMapper 
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class); 
		//convertendo os valos do dadosOrigem para o mesmo tipo da classe Restaurante
		
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade); // retorna a instancia de um campo
			field.setAccessible(true); // torna uma variavel privada, acessivel
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);//busca o valor do campo
			
		//	System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}
	
}

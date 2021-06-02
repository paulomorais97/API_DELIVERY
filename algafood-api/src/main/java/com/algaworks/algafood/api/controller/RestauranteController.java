package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegociosException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/restaurantes")
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
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante salvar(@RequestBody @Valid Restaurante restaurante){
		try {
			return cadastroRestaurante.salvar(restaurante);
		} catch(CozinhaNaoEncontradaException e) {
			throw new NegociosException(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public Restaurante atualizar( @PathVariable Long id,
			@RequestBody Restaurante restaurante){
		
			Restaurante restauranteAtual = cadastroRestaurante.findById(id);
			BeanUtils.copyProperties(restaurante, restauranteAtual,
					"id", "formaPagamento", "endereco", "dataCadastro", "produtos");
			try {
				return cadastroRestaurante.salvar(restauranteAtual);
			} catch(CozinhaNaoEncontradaException e) {
				throw new NegociosException(e.getMessage());
			}
	}
	
	@PatchMapping("/{id}/partial")
	public Restaurante atualizarParcial(@PathVariable Long id,
										@RequestBody Map<String, Object> campos, HttpServletRequest request){
		
		Restaurante restauranteAtual = cadastroRestaurante.findById(id);
		merge(campos, restauranteAtual, request);
		return atualizar(id, restauranteAtual);
	}
	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
		
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();//instanciando ObjectMapper 
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			
			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class); 
			//convertendo os valos do dadosOrigem para o mesmo tipo da classe Restaurante
			
			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade); // retorna a instancia de um campo
				field.setAccessible(true); // torna uma variavel privada, acessivel
				
				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);//busca o valor do campo
				
				ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});
		}catch(IllegalArgumentException ex) {
			Throwable rootCause = ExceptionUtils.getRootCause(ex);
			throw new HttpMessageNotReadableException(ex.getMessage(), rootCause, serverHttpRequest);
		}
	}
	
}

package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {


	private static final String MSG_ESTADO_EM_USO = "Estado de %d não pode ser removida, pois está em uso!";
	private static final String MSG_ESTADO_NAO_ENCONTRADA = "Não existe um cadastro de estado com o código %d";
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void excluir(Long id) {
		try {
			estadoRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e ) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_ESTADO_NAO_ENCONTRADA,
							id));
		}
		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			String.format(MSG_ESTADO_EM_USO,
					id));
		}
		
	}
	
	public Estado findById(Long id) {
		return estadoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADA,
							id)));
	}
	
}

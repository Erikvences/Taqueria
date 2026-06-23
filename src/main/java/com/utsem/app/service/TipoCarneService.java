package com.utsem.app.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsem.app.dto.TipoCarneDTO;
import com.utsem.app.model.TipoCarne;
import com.utsem.app.repo.TipoCarneRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TipoCarneService {

	@Autowired
	TipoCarneRepo tipoCarneRepo;

	@Autowired
	ModelMapper mapper;

	public List<TipoCarneDTO> listar() {
		return tipoCarneRepo.findAll().stream()
				.map(tipoCarne -> mapper.map(tipoCarne, TipoCarneDTO.class))
				.toList();
	}

	public void guardar(TipoCarneDTO tipoCarneDTO) {
		tipoCarneRepo.save(mapper.map(tipoCarneDTO, TipoCarne.class));
	}

	public void actualiza(TipoCarneDTO tipoCarneDTO) {
		Optional<TipoCarne> optTipoCarne = tipoCarneRepo.findByUuid(tipoCarneDTO.getUuid());
		if (optTipoCarne.isPresent()) {
			mapper.map(tipoCarneDTO, optTipoCarne.get());
			tipoCarneRepo.save(optTipoCarne.get());
		} else {
			throw new EntityNotFoundException("Tipo de Carne no encontrado con el UUID: " + tipoCarneDTO.getUuid());
		}
	}

	public void borrar(UUID uuid) {
		Optional<TipoCarne> optTipoCarne = tipoCarneRepo.findByUuid(uuid);
		if (optTipoCarne.isPresent()) {
			tipoCarneRepo.delete(optTipoCarne.get());
		} else {
			throw new EntityNotFoundException("Tipo de Carne no encontrado con el UUID: " + uuid);
		}
	}
	
	public TipoCarneDTO obtenerTipoCarneUUID(UUID uuid) {
		Optional<TipoCarne> optTipoCarne = tipoCarneRepo.findByUuid(uuid);
		if (optTipoCarne.isPresent()) {
			return mapper.map(optTipoCarne.get(), TipoCarneDTO.class);
		} else {
			throw new EntityNotFoundException("Tipo de Carne no encontrado con el UUID: " + uuid);
		}
	}
}

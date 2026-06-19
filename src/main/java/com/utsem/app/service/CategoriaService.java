package com.utsem.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsem.app.model.Categoria;
import com.utsem.app.repo.CategoriaRepo;


@Service
public class CategoriaService {
	@Autowired
	CategoriaRepo categoriaRepo;

	@Autowired
	ModelMapper mapper;
	
	public List<Categoria> listar(){
		return categoriaRepo.findAll();
	}
}

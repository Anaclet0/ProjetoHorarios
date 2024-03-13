package br.com.horarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.horarios.entity.SetorEntity;
import br.com.horarios.repository.SetorRepository;

@Service
public class SetorServiceImpl implements SetorService {

	@Autowired
	private SetorRepository setorRepository;
	
	@Override
	public List<SetorEntity> findAll() {
		
		return setorRepository.findAll();
	}

	

}

package br.com.horarios.service;

import java.util.List;

import br.com.horarios.entity.DocenteEntity;

public interface DocenteService {
	
	String save(DocenteEntity docenteEntity) throws Exception;
	List<DocenteEntity> findAll();
	DocenteEntity getOnebyIdDocente(long idDocente) throws Exception;
	//Começa exclusão
	String deleteById(Long idDocente) throws Exception;
	//Termina exclusão
	//UPDATE
	String update(DocenteEntity docenteEntity) throws Exception;
}

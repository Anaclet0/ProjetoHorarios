package br.com.horarios.service;

import java.util.List;

import br.com.horarios.entity.CursoEntity;

public interface CursoService {

	String save(CursoEntity cursoEntity) throws Exception;

	List<CursoEntity> findAll();

	CursoEntity getOnebyIdCurso(long idCurso) throws Exception;

	// Começa exclusão
	String deleteById(Long idCurso) throws Exception;
	// Termina exclusão
}

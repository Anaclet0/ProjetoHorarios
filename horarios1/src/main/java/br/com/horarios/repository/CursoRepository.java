package br.com.horarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.horarios.entity.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> 
{
	CursoEntity getOneByIdCurso(Long idCurso);
}

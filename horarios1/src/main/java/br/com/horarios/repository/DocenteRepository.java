package br.com.horarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.horarios.entity.DocenteEntity;

@Repository
public interface DocenteRepository extends JpaRepository<DocenteEntity, Long> {
	DocenteEntity getOneByIdDocente(Long idDocente);

	@Query(value = "SELECT * FROM horario.docente;", nativeQuery = true)
	List<DocenteEntity> listarDocentes();

	@Modifying
	@Transactional
	@Query(value = "delete from horario.docente d where d.id_docente=:id_docente", nativeQuery = true)
	void excluirDocente(@Param("id_docente") long idDocente);

	@Modifying
	@Transactional
	@Query(value = "insert into horario.docente (nome,sobrenome,cpf,email,setor_id) VALUES (:nome,:sobrenome,:cpf,:email,:setor_id)", nativeQuery = true)
	void incluirDocente(@Param("nome") String nome, 
						@Param("sobrenome") String sobrenome, 
						@Param("cpf") String cpf,
						@Param("email") String email, 
						@Param("setor_id") Long id);
	
	@Modifying
	@Transactional
	@Query(value = "update horario.docente " 
	+ "set nome = ?1,"
	+ "sobrenome = ?2," 
	+ "cpf = ?3," 
	+ "email + ?4," 
	+ "setor_id + ?5" 
	+ "where id_docente", nativeQuery = true)
	
	void alterarDocente(String nome, String sobrenome, String cpf, String email,Long setorId,Long idDocente);
	
}

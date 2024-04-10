package br.com.horarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.horarios.entity.DocenteEntity;
import br.com.horarios.repository.DocenteRepository;

@Service
public class DocenteServiceImpl implements DocenteService {
	
	@Autowired
	private DocenteRepository docenteRepository;
	
	private String mensagem;

	@Override
	public String save(DocenteEntity docenteEntity) throws Exception {
		
		if (docenteEntity.getNome() == null) {
			this.mensagem = "Digite o nome do docente.";
			throw new Exception("Preencha o nome do docente.");
		}
		else if (docenteEntity.getSobrenome() == null) {
			this.mensagem = "Preencha o sobrenome do docente.";
			throw new Exception("Preencha o sobrenome do docente.");	
		}
		else if (docenteEntity.getEmail() == null) {
			this.mensagem ="Preencha o email";
			throw new Exception("Preencha o email do docente.");
		}
		else
		{
			docenteRepository.incluirDocente(docenteEntity.getNome(),
											 docenteEntity.getSobrenome(),
											 docenteEntity.getCpf(),
											 docenteEntity.getEmail(),
											 docenteEntity.getSetor().getIdSetor());
			this.mensagem ="Docente Cadastrado com Sucesso";
		}
		return mensagem;
		
	}
	@Override
	public String update(DocenteEntity docenteEntity) throws Exception {
		
		if (docenteEntity.getNome() == null) {
			this.mensagem = "Digite o nome do docente.";
			throw new Exception("Preencha o nome do docente.");
		}
		else if (docenteEntity.getSobrenome() == null) {
			this.mensagem = "Preencha o sobrenome do docente.";
			throw new Exception("Preencha o sobrenome do docente.");	
		}
		else if (docenteEntity.getEmail() == null) {
			this.mensagem ="Preencha o email";
			throw new Exception("Preencha o email do docente.");
		}
		else
		{
			docenteRepository.alterarDocente(docenteEntity.getNome(),
											 docenteEntity.getSobrenome(),
											 docenteEntity.getCpf(),
											 docenteEntity.getEmail(),
											 docenteEntity.getSetor().getIdSetor(),
											 docenteEntity.getIdDocente());
			this.mensagem ="Docente alterado com Sucesso";
		}
		return mensagem;
		
	}

	@Override
	public List<DocenteEntity> findAll() {
		
		return docenteRepository.listarDocentes();
	}

	@Override
	public DocenteEntity getOnebyIdDocente(long idDocente) throws Exception {
		
		return docenteRepository.getOneByIdDocente(idDocente);
	}

	@Override
	public String deleteById(Long idDocente) throws Exception {
		try
		{
			docenteRepository.excluirDocente(idDocente);
			this.mensagem = "Docente excluído com sucesso.";
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return mensagem;
	}


	



}

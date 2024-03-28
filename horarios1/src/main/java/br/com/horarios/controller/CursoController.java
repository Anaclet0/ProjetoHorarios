package br.com.horarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.horarios.entity.CursoEntity;
import br.com.horarios.service.CursoService;

@Controller
public class CursoController {

	@Autowired
	private CursoService cursoService;

	@GetMapping("/curso") //nome que eu quiser colocar
	public String docente(ModelMap model)
	{
		model.addAttribute("cursos",cursoService.findAll());
		
		return "curso"; //caminho real do arquivo
				
	}
	@PostMapping("/salvar_curso")
	public ModelAndView save(
			ModelMap model,
			@ModelAttribute("cursoEntity")CursoEntity cursoEntity,
			RedirectAttributes atributes) throws Exception
	{
		ModelAndView mv = new ModelAndView("redirect:/curso");
		atributes.addFlashAttribute("mansagem",cursoService.save(cursoEntity));
		
		return mv;
		
	}
	@GetMapping("alterar_curso/{idCurso}")
	public ModelAndView update(ModelMap model,@PathVariable("idCurso") Long idCurso) throws Exception
	{
		ModelAndView mv = new ModelAndView("alterar_curso");
		model.addAttribute("idCurso",idCurso);
		model.addAttribute("curso",cursoService.getOnebyIdCurso(idCurso));
		
		return mv;
	
			
	}
	@PostMapping("/alterar_curso")
	public ModelAndView update(
			ModelMap model,
			@ModelAttribute("cursoEntity")CursoEntity cursoEntity,
			RedirectAttributes atributes) throws Exception
	{
		ModelAndView mv = new ModelAndView("redirect:/curso");
		atributes.addFlashAttribute("mansagem",cursoService.save(cursoEntity));
		
		return mv;
		
	}
	//começa Exclusão
	@GetMapping("/excluir_curso/{idCurso}")
	public ModelAndView delete(ModelMap model,@PathVariable ("idCurso") Long idCurso,RedirectAttributes atributes) throws Exception 
	{
		ModelAndView mv = new ModelAndView("curso");
		model.addAttribute("mensagem",cursoService.deleteById(idCurso));
		//após a exclusão de um docente eu preciso atualizar a listagem na pagina
		//por isso eu realizo uma nova consulta findall
		model.addAttribute("curso",cursoService.findAll());
		
		return mv;
	}
	
	}


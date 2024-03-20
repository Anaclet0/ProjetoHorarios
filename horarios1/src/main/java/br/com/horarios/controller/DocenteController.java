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

import br.com.horarios.entity.DocenteEntity;
import br.com.horarios.service.DocenteService;
import br.com.horarios.service.SetorService;

@Controller
public class DocenteController {
	
	@Autowired
	private DocenteService docenteService;
	
	@Autowired
	private SetorService setorService;
	
	@GetMapping("/docente") //nome que eu quiser colocar
	public String docente(ModelMap model)
	{
		model.addAttribute("setores",setorService.findAll());
		model.addAttribute("docentes",docenteService.findAll());
		return "docente"; //caminho real do arquivo
				
	}
	
	@PostMapping("/salvar_docente")
	public ModelAndView save(
			ModelMap model,
			@ModelAttribute("docenteEntity")DocenteEntity docenteEntity,
			RedirectAttributes atributes) throws Exception
	{
		System.out.println("entrei");
		ModelAndView mv = new ModelAndView("redirect:/docente");
		atributes.addFlashAttribute("mansagem",docenteService.save(docenteEntity));
		return mv;
		
	}
	@GetMapping("alterar_docente/{idDocente}")
	public ModelAndView update(ModelMap model,@PathVariable("idDocente") Long idDocente) throws Exception
	{
		ModelAndView mv = new ModelAndView("alterar-docente");
		model.addAttribute("setores",setorService.findAll());
		model.addAttribute("idDocente",idDocente);
		model.addAttribute("docente",docenteService.getOnebyIdDocente(idDocente));
		
		return mv;
	
			
	}
}
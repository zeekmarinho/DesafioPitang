package net.agenda.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.agenda.dao.UsuarioDAO;
import net.agenda.model.Usuario;

public class HomeController {
	
	@Autowired
    private UsuarioDAO usuarioDAO;
	
	@RequestMapping(value="/")
	public ModelAndView listUsuario(ModelAndView model) throws IOException{
	    List<Usuario> listUsuario = usuarioDAO.list();
	    model.addObject("listContact", listUsuario);
	    model.setViewName("home");
	 
	    return model;
	}
	
	@RequestMapping(value = "/newUsuario", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
	    Usuario newUsuario = new Usuario();
	    model.addObject("usuario", newUsuario);
	    model.setViewName("UsuarioForm");
	    return model;
	}
	
	@RequestMapping(value = "/saveUsuario", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Usuario usuario) {
		usuarioDAO.saveOrUpdate(usuario);
	    return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/deleteUsuario", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
	    int usuarioId = Integer.parseInt(request.getParameter("id"));
	    usuarioDAO.delete(usuarioId);
	    return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
	    int usuarioId = Integer.parseInt(request.getParameter("id"));
	    Usuario usuario = usuarioDAO.get(usuarioId);
	    ModelAndView model = new ModelAndView("UsuarioForm");
	    model.addObject("usuario", usuario);
	 
	    return model;
	}

}

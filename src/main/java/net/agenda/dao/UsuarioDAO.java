package net.agenda.dao;

import java.util.List;

import net.agenda.model.Usuario;


public interface UsuarioDAO {
	
	public void saveOrUpdate(Usuario usuario);
     
	public void delete(int usuarioId);
	     
	public Usuario get(int usuarioId);
	     
	public List<Usuario> list();

}

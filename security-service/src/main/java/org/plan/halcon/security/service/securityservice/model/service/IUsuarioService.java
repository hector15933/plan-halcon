package org.plan.halcon.security.service.securityservice.model.service;


import org.plan.halcon.security.service.securityservice.model.entity.Usuario;

import java.util.List;



public interface IUsuarioService {
	
	public Usuario findByUsername(String username);
	
	public List<Usuario> findAll();
	
	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void delete(Long id);
}

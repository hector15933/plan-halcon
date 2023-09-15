package org.plan.halcon.security.service.securityservice.model.dao;

import org.plan.halcon.security.service.securityservice.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String usuarioString);
}

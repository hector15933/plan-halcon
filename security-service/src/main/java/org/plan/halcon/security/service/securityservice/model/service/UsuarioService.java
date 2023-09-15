package org.plan.halcon.security.service.securityservice.model.service;


import org.plan.halcon.security.service.securityservice.model.dao.IUserDao;
import org.plan.halcon.security.service.securityservice.model.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsuarioService implements IUsuarioService,UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	
	
	@Autowired
	private IUserDao usuarioDao;
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioDao.findByUsername(username);
		
		
		if(usuario==null) {
			logger.error("Error , no existe el usuario en el sistema");
			throw new UsernameNotFoundException("Error , no existe el usuario en el sistema");
		}{
			
		}
		
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getAuthority()))
				.peek(authority->logger.info("Role: " +authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getUsername(), usuario.getPassword(),usuario.getEnabled(), true, true, true, authorities);
	}
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findAll();
	}
	@Override
	public Usuario findById(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id).orElse(null);
	}
	@Override
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioDao.save(usuario);
	}
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		usuarioDao.deleteById(id);
	}
	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		// TODO Auto-generated method stub
		return usuarioDao.findByUsername(username);
	}

}

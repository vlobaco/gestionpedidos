package es.mdef.gestionpedidos.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.mdef.gestionpedidos.repositorios.EmpleadoRepositorio;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private EmpleadoRepositorio repositorio;
	
	public UserDetailsServiceImpl(EmpleadoRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repositorio.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(
						"Empleado " + username + " no encontrado"));
	}

}

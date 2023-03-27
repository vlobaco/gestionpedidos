package es.mdef.gestionpedidos.entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="empleados")
public class Empleado extends es.mdef.ejemplo001.support.Empleado implements UserDetails {
	private static final long serialVersionUID = 1L;
	@NotBlank(message="username es un campo obligatorio de la clase empleado")
	@Column(unique=true, name = "nombre_usuario")
	private String username;
	@NotBlank(message="contraseña es un campo obligatorio de la clase empleado")
	@Column(name="contrasena")
	private String password;
	@OneToMany(mappedBy = "empleado")
	private List<Pedido> pedidos;
	@Column(name="cuenta_activa")
	private boolean accountNonExpired = true;
	@Column(name="cuenta_desbloqueada")
	private boolean accountNonLocked = true;
	@Column(name="credenciales_activas")
	private boolean credentialsNonExpired = true;
	@Column(name="habilitada")
	private boolean enabled = true;
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Empleado [username=" + username + ", password=" + password + "]";
	}

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<SimpleGrantedAuthority>(
				Arrays.asList(new SimpleGrantedAuthority(getRole().toString()))
				);
	}
	
}

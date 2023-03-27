package es.mdef.gestionpedidos.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionpedidos.entidades.Empleado;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
	Optional<Empleado> findByUsername(String username);
}

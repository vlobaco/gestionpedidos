package es.mdef.gestionpedidos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionpedidos.entidades.Articulo;

public interface ArticuloRepositorio extends JpaRepository<Articulo, Long> {

}

package es.mdef.gestionpedidos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionpedidos.entidades.Pedido;
import es.mdef.gestionpedidos.entidades.Pedido.PedidoEstado;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
	List<Pedido> findPedidoByEstado(PedidoEstado estado);
	List<Pedido> findByEmpleadoId(Long id);
}

package es.mdef.gestionpedidos.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionpedidos.entidades.Articulo;
import es.mdef.gestionpedidos.entidades.Empleado;
import es.mdef.gestionpedidos.entidades.Pedido.PedidoEstado;

@Relation(itemRelation="pedido")
public class PedidoModel extends RepresentationModel<PedidoModel>{

	private String descripcion;
	private PedidoEstado estado = PedidoEstado.Realizado;
	private Articulo articulo;
	private Empleado empleado;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public PedidoEstado getEstado() {
		return estado;
	}
	public void setEstado(PedidoEstado estado) {
		this.estado = estado;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	@Override
	public String toString() {
		return "PedidoModel [descripcion=" + descripcion + ", estado=" 
				+ estado + "]";
	}
	
}

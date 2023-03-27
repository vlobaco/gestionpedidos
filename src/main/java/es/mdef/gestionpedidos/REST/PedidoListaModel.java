package es.mdef.gestionpedidos.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionpedidos.entidades.Pedido.PedidoEstado;

@Relation(collectionRelation = "pedidos")
public class PedidoListaModel extends RepresentationModel<PedidoListaModel> {
	private String descripcion;
	private PedidoEstado estado;
	
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
	
	@Override
	public String toString() {
		return "PedidoModel [descripcion=" + descripcion + ", estado=" + estado + "]";
	}

}

package es.mdef.gestionpedidos.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.gestionpedidos.entidades.Articulo.Tipo;

@Relation(collectionRelation = "articulos")
public class ArticuloListaModel extends RepresentationModel<ArticuloListaModel>{
	private String nombre;
	private Tipo tipo;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "ArticuloListaModel [nombre=" + nombre + ", tipo=" + tipo + "]";
	}
	
}

package es.mdef.gestionpedidos.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@Relation(itemRelation = "empleado", collectionRelation = "empleados")
public class EmpleadoListaModel  extends RepresentationModel<EmpleadoListaModel>{
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "EmpleadoListaModel [nombre=" + nombre + "]";
	}

}

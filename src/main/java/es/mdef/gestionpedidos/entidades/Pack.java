package es.mdef.gestionpedidos.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("P")
public class Pack extends Articulo {
	private Integer unidades;

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}
	
	public Tipo getTipo() {
		return Tipo.Pack;
	}

	@Override
	public String toString() {
		return "Pack [unidades=" + unidades + ", id=" + getId() + ", nombre=" + getNombre() + "]";
	}
	
}

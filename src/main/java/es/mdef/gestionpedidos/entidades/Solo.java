package es.mdef.gestionpedidos.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("S")
public class Solo extends Articulo {
	private Float peso;

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}
	
	public Tipo getTipo() {
		return Tipo.Solo;
	}

	@Override
	public String toString() {
		return "Solo [peso=" + peso + ", id=" + getId() + ", nombre=" + getNombre() + "]";
	}
	
}

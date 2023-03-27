package es.mdef.gestionpedidos.REST;

import org.springframework.hateoas.RepresentationModel;

import es.mdef.gestionpedidos.entidades.Articulo.Tipo;
import jakarta.validation.constraints.NotBlank;


public class ArticuloModel extends RepresentationModel<ArticuloModel>{

	private String nombre;
	private Float peso;
	private Integer unidades;
	private Tipo tipo;
	@NotBlank(message="username es un campo obligatorio de la clase empleado")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Float getPeso() {
		return peso;
	}
	public void setPeso(Float peso) {
		this.peso = peso;
	}
	public Integer getUnidades() {
		return unidades;
	}
	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "ArticuloPostModel [nombre=" + nombre + ", peso=" + peso + ", unidades=" + unidades
				+ ", tipo=" + tipo + "]";
	}
	
}

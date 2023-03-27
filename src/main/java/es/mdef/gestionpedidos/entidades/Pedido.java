package es.mdef.gestionpedidos.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="PEDIDOS")
public class Pedido {
	public static enum PedidoEstado {
		Realizado,
		Preparado,
		Enviado,
		Recibido
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="descripción es un campo obligatorio de la clase empleado")
	private String descripcion;
	private PedidoEstado estado = PedidoEstado.Realizado;
	@NotBlank(message="articulo es un campo obligatorio de la clase empleado")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ArticuloId")
	private Articulo articulo;
	@NotBlank(message="empleado es un campo obligatorio de la clase empleado")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EmpleadoId")
	private Empleado empleado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
		return "Pedido [id=" + id + ", descripcion=" + descripcion + ", estado=" + estado
				+ "]";
	}
	
	
	
}

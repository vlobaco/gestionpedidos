package es.mdef.gestionpedidos.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Pedido;

@Component
public class PedidoAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>>{
	
	@Override
	public EntityModel<Pedido> toModel(Pedido entity) {
		EntityModel<Pedido> model = EntityModel.of(entity);
		model.add(
				linkTo(methodOn(PedidoController.class).one(entity.getId())).withSelfRel(),
				linkTo(methodOn(ArticuloController.class).one(entity.getArticulo().getId())).withRel("articulo"),
				linkTo(methodOn(EmpleadoController.class).one(entity.getEmpleado().getId())).withRel("empleado")
				);
		return model;
	}
	
	public Pedido toEntity(PedidoModel model) {
		Pedido pedido = new Pedido();
		pedido.setDescripcion(model.getDescripcion());
		pedido.setEstado(model.getEstado());
		pedido.setArticulo(model.getArticulo());
		pedido.setEmpleado(model.getEmpleado());
		return pedido;
	}
}

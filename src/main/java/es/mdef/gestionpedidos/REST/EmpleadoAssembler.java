package es.mdef.gestionpedidos.REST;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Empleado;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;

@Component
public class EmpleadoAssembler implements RepresentationModelAssembler<Empleado, EntityModel<Empleado>> {

	@Override
	public EntityModel<Empleado> toModel(Empleado entity) {
		entity.setPassword(null);
		EntityModel<Empleado> model = EntityModel.of(entity);
		WebMvcLinkBuilder selfLink = linkTo(methodOn(EmpleadoController.class).one(entity.getId()));
		model.add(
			selfLink.withSelfRel(),
			selfLink.slash("pedidos").withRel("pedidos")
		);
		return model;
	}

}

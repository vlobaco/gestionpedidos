package es.mdef.gestionpedidos.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Empleado;

@Component
public class EmpleadoListaAssembler implements RepresentationModelAssembler<Empleado, EmpleadoListaModel> {

	@Override
	public EmpleadoListaModel toModel(Empleado entity) {
		EmpleadoListaModel model = new EmpleadoListaModel();
		model.setNombre(entity.getNombre());
		model.add(
			linkTo(methodOn(EmpleadoController.class).one(entity.getId())).withSelfRel()
		);
		return model;
	}
	
	public CollectionModel<EmpleadoListaModel> toCollection(List<Empleado> lista) {
		CollectionModel<EmpleadoListaModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);
		collection.add(
			linkTo(methodOn(EmpleadoController.class).all()).withRel("empleados")
		);
		return collection;
	}

}

package es.mdef.gestionpedidos.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Pedido;

@Component
public class PedidoListaAssembler implements RepresentationModelAssembler<Pedido, PedidoListaModel>{

	@Override
	public PedidoListaModel toModel(Pedido entity) {
		PedidoListaModel model = new PedidoListaModel();
		model.setDescripcion(entity.getDescripcion());
		model.setEstado(entity.getEstado());
		model.add(
				linkTo(methodOn(PedidoController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	
	public CollectionModel<PedidoListaModel> toCollectionModel(List<Pedido> lista) {
		CollectionModel<PedidoListaModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);
		collection.add(
				linkTo(methodOn(PedidoController.class).all()).withRel("pedidos")
				);
		return collection;
	}

}

package es.mdef.gestionpedidos.REST;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Articulo;

@Component
public class ArticuloListaAssembler implements RepresentationModelAssembler<Articulo, ArticuloListaModel>{

	@Override
	public ArticuloListaModel toModel(Articulo entity) {
		ArticuloListaModel model = new ArticuloListaModel();
		model.setNombre(entity.getNombre());
		model.setTipo(entity.getTipo());
		model.add(
				linkTo(methodOn(ArticuloController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	
	public CollectionModel<ArticuloListaModel> toCollection(List<Articulo> lista) {
		CollectionModel<ArticuloListaModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);
		collection.add(
				linkTo(methodOn(ArticuloController.class).all()).withRel("articulos")
				);
		return collection;
	}

}

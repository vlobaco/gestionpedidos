package es.mdef.gestionpedidos.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionpedidos.entidades.Articulo;
import es.mdef.gestionpedidos.entidades.Pack;
import es.mdef.gestionpedidos.entidades.Solo;

@Component
public class ArticuloAssembler implements RepresentationModelAssembler<Articulo, EntityModel<Articulo>>{

	@Override
	public EntityModel<Articulo> toModel(Articulo entity) {
		EntityModel<Articulo> model = EntityModel.of(entity);
		model.add(
				linkTo(methodOn(ArticuloController.class).one(entity.getId())).withSelfRel(),
				linkTo(methodOn(ArticuloController.class).pedidos(entity.getId())).withRel("pedidos")
				);
		return model;
	}
	
	public Articulo toEntity(ArticuloModel model) {
		Articulo articulo;
		switch (model.getTipo()) {
		case Pack:
			Pack pack = new Pack();
			pack.setUnidades(model.getUnidades());
			articulo = pack;
			break;
		case Solo: 
			Solo solo = new Solo();
			solo.setPeso(model.getPeso());
			articulo = solo;
			break;
		default:
			articulo = new Articulo();
		}
		articulo.setNombre(model.getNombre());
		return articulo;
	}

}

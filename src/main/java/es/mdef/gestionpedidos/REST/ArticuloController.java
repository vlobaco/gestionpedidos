package es.mdef.gestionpedidos.REST;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.mdef.gestionpedidos.GestionPedidosApplication;
import es.mdef.gestionpedidos.entidades.Articulo;
import es.mdef.gestionpedidos.entidades.Articulo.Tipo;
import es.mdef.gestionpedidos.entidades.Pack;
import es.mdef.gestionpedidos.entidades.Pedido;
import es.mdef.gestionpedidos.entidades.Solo;
import es.mdef.gestionpedidos.repositorios.ArticuloRepositorio;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/articulos")
public class ArticuloController {
	private final ArticuloRepositorio repositorio;
	private final ArticuloAssembler assembler;
	private final ArticuloListaAssembler listaAssembler;
	private final PedidoListaAssembler pedidoListaAssembler;
	private final Logger log;
	
	public ArticuloController(ArticuloRepositorio repositorio, 
			ArticuloAssembler assembler, ArticuloListaAssembler listaAssembler,
			PedidoListaAssembler pedidoListaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.pedidoListaAssembler = pedidoListaAssembler;
		this.log = GestionPedidosApplication.log;
	}
	
	@PostMapping
	public EntityModel<Articulo> add(@Valid @RequestBody ArticuloModel model) {
		Articulo articulo = repositorio.save(assembler.toEntity(model));
		log.info("Añadido " + articulo);
		return assembler.toModel(articulo);
	}
	
	@GetMapping("{id}")
	public EntityModel<Articulo> one (@PathVariable Long id) {
		Articulo articulo = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "articulo"));
		log.info("Recuperado " + articulo);
		return assembler.toModel(articulo);
	}
	
	@GetMapping
	public CollectionModel<ArticuloListaModel> all () {
		List<Articulo> lista = repositorio.findAll();
		return listaAssembler.toCollection(lista);
	}
	
	@GetMapping("{id}/pedidos")
	public CollectionModel<PedidoListaModel> pedidos(@PathVariable Long id) {
		List<Pedido> pedidos = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "articulo"))
				.getPedidos();
		return CollectionModel.of(
				pedidos.stream().map(pedido -> pedidoListaAssembler.toModel(pedido)).collect(Collectors.toList()),
				linkTo(methodOn(ArticuloController.class).one(id)).slash("pedidos").withSelfRel()
				);
	}
	
	@PutMapping("{id}")
	public EntityModel<Articulo> edit (@PathVariable Long id, @Valid @RequestBody ArticuloModel model) {
		Articulo articulo = repositorio.findById(id).map(art -> {
			art.setNombre(model.getNombre());
			if (art.getTipo() == Tipo.Pack) {
				Pack pack = (Pack) art;
				pack.setUnidades(model.getUnidades());
			} else if (art.getTipo() == Tipo.Solo) {
				Solo solo = (Solo) art;
				solo.setPeso(model.getPeso());
			}
			return repositorio.save(art);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "artículo"));
		log.info("Actualizado " + articulo);
		return assembler.toModel(articulo);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrado artículo " + id);
		repositorio.deleteById(id);
	}

}

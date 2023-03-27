package es.mdef.gestionpedidos.REST;

import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.mdef.gestionpedidos.GestionPedidosApplication;
import es.mdef.gestionpedidos.entidades.Articulo;
import es.mdef.gestionpedidos.entidades.Pedido;
import es.mdef.gestionpedidos.entidades.Pedido.PedidoEstado;
import es.mdef.gestionpedidos.repositorios.PedidoRepositorio;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	private final PedidoRepositorio repositorio;
	private final PedidoAssembler assembler;
	private final PedidoListaAssembler listaAssembler;
	private final ArticuloAssembler articuloAssembler;
	private final Logger log;
	
	PedidoController(PedidoRepositorio repositorio, PedidoAssembler assembler, 
			PedidoListaAssembler listaAssembler, ArticuloAssembler articuloAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.articuloAssembler = articuloAssembler;
		log = GestionPedidosApplication.log;
	}
	
	@GetMapping("{id}")
	public EntityModel<Pedido> one(@PathVariable Long id) {
		Pedido pedido = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "pedido"));
		log.info("Recuperado " + pedido);
		return assembler.toModel(pedido);
	}
	
	@GetMapping
	public CollectionModel<PedidoListaModel> all() {
		return listaAssembler.toCollectionModel(repositorio.findAll());
	}
	
	@GetMapping("porEstado")
	public CollectionModel<PedidoListaModel> pedidosPorEstado(@RequestParam PedidoEstado estado) {
		return listaAssembler.toCollectionModel(
				repositorio.findPedidoByEstado(estado)
				);
	}
	
	@GetMapping("{id}/articulo")
	public EntityModel<Articulo> articulo(@PathVariable Long id) {
		Pedido pedido = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "pedido"));
		return articuloAssembler.toModel(pedido.getArticulo());
	}
	
	@PostMapping
	public EntityModel<Pedido> add(@Valid @RequestBody PedidoModel model) {
		Pedido pedido = repositorio.save(assembler.toEntity(model));
		log.info("Añadido " + pedido);
		return assembler.toModel(pedido);
	}
	
	@PutMapping("{id}")
	public EntityModel<Pedido> edit(@Valid @PathVariable Long id, @RequestBody PedidoModel model) {
		Pedido pedido = repositorio.findById(id).map(ped -> {
			ped.setDescripcion(model.getDescripcion());
			ped.setEstado(model.getEstado());
			ped.setArticulo(model.getArticulo());
			return repositorio.save(ped);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "pedido"));
		log.info("Actualizado " + pedido);
		return assembler.toModel(pedido);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrado pedido " + id);
		repositorio.deleteById(id);
	}
}

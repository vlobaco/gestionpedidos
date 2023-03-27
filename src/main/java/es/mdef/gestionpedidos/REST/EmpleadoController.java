package es.mdef.gestionpedidos.REST;

import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.mdef.gestionpedidos.GestionPedidosApplication;
import es.mdef.gestionpedidos.entidades.Empleado;
import es.mdef.gestionpedidos.repositorios.EmpleadoRepositorio;
import es.mdef.gestionpedidos.repositorios.PedidoRepositorio;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/empleados")
public class EmpleadoController {
	private final EmpleadoRepositorio repositorio;
	private final PedidoRepositorio pedidoRepositorio;
	private final EmpleadoAssembler assembler;
	private final EmpleadoListaAssembler listaAssembler;
	private final PedidoListaAssembler pedidoListaAssembler;
	private final Logger log;
	
	EmpleadoController(EmpleadoRepositorio repositorio, PedidoRepositorio pedidoRepositorio, 
			EmpleadoAssembler assembler, PedidoAssembler pedidoAssembler,
			EmpleadoListaAssembler listaAssembler, PedidoListaAssembler pedidoListaAssembler) {
		this.repositorio = repositorio;
		this.pedidoRepositorio = pedidoRepositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.pedidoListaAssembler = pedidoListaAssembler;
		this.log = GestionPedidosApplication.log;
	}
	
	@GetMapping("{id}")
	public EntityModel<Empleado> one(@PathVariable Long id) {
		Empleado empleado = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "empleado"));
		log.info("Recuperado " + empleado);
		return assembler.toModel(empleado);
	}
	
	@GetMapping("{id}/pedidos") 
	public CollectionModel<PedidoListaModel> pedidos (@PathVariable Long id) {
		return CollectionModel.of(
				pedidoRepositorio.findByEmpleadoId(id).stream().map(pedido->
						pedidoListaAssembler.toModel(pedido)).collect(Collectors.toList()),
				linkTo(methodOn(EmpleadoController.class).one(id)).slash("pedidos").withSelfRel()
				);
	}
	
	@GetMapping
	public CollectionModel<EmpleadoListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}
	
	@PostMapping
	public EntityModel<Empleado> add(@Valid @RequestBody Empleado empleado) {
		empleado.setPassword(new BCryptPasswordEncoder().encode(empleado.getPassword()));
		Empleado nuevoEmpleado = repositorio.save(empleado);
		log.info("Añadido " + nuevoEmpleado);;
		return assembler.toModel(nuevoEmpleado);
	}
	
	@PutMapping("{id}")
	public EntityModel<Empleado> edit(@PathVariable Long id, @Valid @RequestBody Empleado empleado) {
		Empleado nuevoEmpleado = repositorio.findById(id).map(emp -> {
			emp.setNombre(empleado.getNombre());
			emp.setRole(empleado.getRole());
			emp.setUsername(empleado.getUsername());
			emp.setAccountNonExpired(empleado.isAccountNonExpired());
			emp.setAccountNonLocked(empleado.isAccountNonLocked());
			emp.setCredentialsNonExpired(empleado.isCredentialsNonExpired());
			emp.setEnabled(empleado.isEnabled());
			return repositorio.save(emp);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "empleado"));
		log.info("Actualizado " + nuevoEmpleado);
		return assembler.toModel(nuevoEmpleado);
	}
	
	@PatchMapping("{id}/cambiarContrasena")
	public EntityModel<Empleado> edit(@PathVariable Long id, @RequestBody String newPassword) {
		Empleado nuevoEmpleado = repositorio.findById(id).map(emp -> {
			emp.setPassword(new BCryptPasswordEncoder().encode(newPassword));
			return repositorio.save(emp);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "empleado"));
		log.info("Actualizado " + nuevoEmpleado);
		return assembler.toModel(nuevoEmpleado);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrado usuario " + id);
		repositorio.deleteById(id);
	}

}

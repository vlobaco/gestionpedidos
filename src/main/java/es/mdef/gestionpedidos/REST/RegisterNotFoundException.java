package es.mdef.gestionpedidos.REST;

public class RegisterNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	RegisterNotFoundException(Long id, String tipo) {
		super("No se ha encontrado el " + tipo + " " + id);
	}
}

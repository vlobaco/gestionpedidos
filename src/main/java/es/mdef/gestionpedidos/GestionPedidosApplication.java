package es.mdef.gestionpedidos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GestionPedidosApplication {
	public static final Logger log = LoggerFactory.getLogger(GestionPedidosApplication.class);
	
	public static void main(String[] args) {
		System.err.println(new BCryptPasswordEncoder().encode("password"));
		SpringApplication.run(GestionPedidosApplication.class, args);
	}

}

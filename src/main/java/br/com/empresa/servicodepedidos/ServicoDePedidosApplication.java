package br.com.empresa.servicodepedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "br.com.empresa.servicodepedidos.core.model")
@EnableJpaRepositories(basePackages = "br.com.empresa.servicodepedidos.core.repository")

public class ServicoDePedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoDePedidosApplication.class, args);
	}

}

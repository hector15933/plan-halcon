package org.plan.halcon.pagos.service.pagosservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PagosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagosServiceApplication.class, args);
	}

}

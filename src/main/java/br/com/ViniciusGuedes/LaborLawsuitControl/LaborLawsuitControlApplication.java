package br.com.ViniciusGuedes.LaborLawsuitControl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Labor Lawsuit management", version = "1", description =
		"Api developed for study purposes, based on a system from an institution where I interned"))
public class LaborLawsuitControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaborLawsuitControlApplication.class, args);
	}

}

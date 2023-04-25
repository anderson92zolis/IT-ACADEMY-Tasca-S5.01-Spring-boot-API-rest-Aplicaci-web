package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Flower's API to Api REST", version = "3.0.0", description = "Documentation RESTful API"))
public class S05T01N03ZolisChipantasigAndersonApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05T01N03ZolisChipantasigAndersonApplication.class, args);
	}

}

package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Flower's API", version = "3.0.0", description = "Documentation RESTful API"))
public class S05T01N02ZolisChipantasigAndersonApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(S05T01N02ZolisChipantasigAndersonApplication.class, args);
	}

}

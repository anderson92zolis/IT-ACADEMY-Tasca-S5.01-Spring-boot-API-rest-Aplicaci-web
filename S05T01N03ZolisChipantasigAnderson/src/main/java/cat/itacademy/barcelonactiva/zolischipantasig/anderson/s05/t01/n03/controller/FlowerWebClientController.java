package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.controller;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.model.dto.Message;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.model.service.WebClientFlowerServiceImplement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;


//code to work n3: https://mercyjemosop.medium.com/consume-external-api-spring-boot-7e2c684e3d00

// some important code n2: https://www.baeldung.com/swagger-set-example-description
// Transitioning Swagger 1.5 to 2.0 annotations n2 : https://www.david-merrick.com/2017/11/15/useful-regexes-for-transitioning-swagger-2-0-to-3-0-annotations/

@RestController
@RequestMapping("/flor")
@Tag(name = "WEB_CLIENT Flowers Management System", description = "CRUD operations from flowers database")
public class FlowerWebClientController {


    private WebClientFlowerServiceImplement webClientFlowerServiceImplement;

    @Autowired
    public FlowerWebClientController(WebClientFlowerServiceImplement flowerServiceImplement) {
        super();
        this.webClientFlowerServiceImplement = flowerServiceImplement;
    }


    //Add a new user
    @Operation(summary = "ADD", description = "Add a FLOWER to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SUCCESSFULLY ADDED FLOWER"),
            @ApiResponse(responseCode = "400", description = "ERROR. THE NAME IS REQUIRED."),
            @ApiResponse(responseCode = "400", description = "ERROR. THE COUNTRY IS REQUIRED."),
    })
    @PostMapping("/clientFlorsAdd")
    public ResponseEntity<Mono<Message>> clientFlorsAddWebClient(@RequestBody FlowerDTO flowerDTO) throws Exception {

        try {
            return new ResponseEntity<>(webClientFlowerServiceImplement.createFlower(flowerDTO)
                    .map(flower -> new Message(" FLOWER ADDED SUCCESSFULLY TO THE DATA BASE")),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception( "Internal Server Error while creating the flower", e.getCause());
        }

    }


    // Update an existing Flower

    @Operation(summary = "UPDATE",
            description = "update a FLOWER from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SUCCESSFULLY UPDATED FLOWER"),
            @ApiResponse(responseCode = "404", description = "FLOWER NOT FOUND, NOT FOUND WITH ID : 'X'")
    })
    @PutMapping("/clientFlorsUpdate/{id}")
    public ResponseEntity<?> updateFlowerWebClient(@PathVariable int id, @RequestBody FlowerDTO flowerDTO) throws Exception {
        // update the Flower
        try {
            return new ResponseEntity<>(webClientFlowerServiceImplement.updateFlower(id,flowerDTO)
                    .map(flower -> new Message("FLOWER UPDATED SUCCESSFULLY")),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception( "Internal Server Error while update the flower", e.getCause());
        }
    }

    // Delete an existing Flower

    //https://medium.com/swlh/restful-api-documentation-made-easy-with-swagger-and-openapi-6df7f26dcad


    @Operation(summary = "DELETE",
            description = "delete FLOWER from the database")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "FLOWER DELETED."),
            @ApiResponse(responseCode = "404", description = "ERROR. THE ID ENTERED DOES NOT EXIST."),
    })
    @DeleteMapping("/clientFlorsDelete/{id}")
    public ResponseEntity<?> deleteFlowerWebClient(@PathVariable int id) throws Exception {
        try {
            return new ResponseEntity<>(webClientFlowerServiceImplement.deleteFlower(id)
                    .map(flower -> new Message("FLOWER DELETED SUCCESSFULLY")),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception( "Internal Server Error while deleting the flower", e.getCause());
        }
    }


    //Read a Flower
    @Operation(summary = "GET ONE",
            description = "Get one FLOWERS from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "show a flower from database"),
            @ApiResponse(responseCode = "404", description = "FLOWER NOT FOUND, NOT FOUND WITH ID  : '0'"),
    })
    @GetMapping("/clientFlorsGetOne/{id}")
    public ResponseEntity<?> getFlowerByIdWebClient(@PathVariable(name = "id") int id) throws Exception {
        try {
            return new ResponseEntity<>(webClientFlowerServiceImplement.getFlowerDtoById(id)
                    .map(flower -> new Message("Get One Flower")),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception( "Internal Server Error while getting the flower", e.getCause());
        }
    }


    // Read all branches

    @Operation(summary = "GET ALL",
            description = "Get all flowers from the database")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Show all the Flowers available from database")})

    @GetMapping("/clientFlorsAll")
    public ResponseEntity<?> getAllFlowerWebClient() throws Exception {
        try {
            return new ResponseEntity<>(webClientFlowerServiceImplement.getAllFlowers()
                    .map(flower -> new Message("Get All Flower")),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception( "Internal Server Error while getting All Flower", e.getCause());
        }
    }
}


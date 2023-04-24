package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.controller;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.dto.Message;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.services.FlowerServiceImplement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

// some important code: https://www.baeldung.com/swagger-set-example-description
// Transitioning Swagger 1.5 to 2.0 annotations  : https://www.david-merrick.com/2017/11/15/useful-regexes-for-transitioning-swagger-2-0-to-3-0-annotations/
@RestController
@RequestMapping("/flowers")
@Tag(name = "Flowers Management System", description = "CRUD operations from flowers database")
public class FlowerController {


    private FlowerServiceImplement flowerServiceImplement;

    @Autowired
    public FlowerController(FlowerServiceImplement flowerServiceImplement) {
        super();
        this.flowerServiceImplement = flowerServiceImplement;
    }


    //Add a new user
    @Operation(summary = "ADD", description = "Add a FLOWER to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SUCCESSFULLY ADDED FLOWER"),
            @ApiResponse(responseCode = "400", description = "ERROR. THE NAME IS REQUIRED."),
            @ApiResponse(responseCode = "400", description = "ERROR. THE COUNTRY IS REQUIRED."),
    })
    @PostMapping("/add")
    public ResponseEntity<Message> addFruita(@RequestBody FlowerDTO flowerDTO) {
        ResponseEntity<Message> validationResult = flowerServiceImplement.validateFruitaDto(flowerDTO);
        if (validationResult.getStatusCode() == HttpStatus.OK) {
            flowerServiceImplement.createFlower(flowerDTO);
            return new ResponseEntity<>(new Message("SUCCESSFULLY ADDED FLOWER"), HttpStatus.CREATED);
        } else {
            return validationResult;
        }
    }



    // Update an existing Flower

    @Operation(summary = "UPDATE",
            description = "update a FLOWER from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SUCCESSFULLY UPDATED FLOWER"),
            @ApiResponse(responseCode = "404", description = "FLOWER NOT FOUND, NOT FOUND WITH ID : 'X'")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Message> updateFlower(@PathVariable int id, @RequestBody FlowerDTO flowerDTO) {
        // update the Flower
        ResponseEntity<Message> validationResult = flowerServiceImplement.validateFruitaDto(flowerDTO);
        if (validationResult.getStatusCode() == HttpStatus.OK) {
            flowerServiceImplement.updateFlower(id, flowerDTO);
            return new ResponseEntity<>(new Message("SUCCESSFULLY UPDATED FLOWER"), HttpStatus.CREATED);}
        return validationResult;
    }

    // Delete an existing Flower

    //https://medium.com/swlh/restful-api-documentation-made-easy-with-swagger-and-openapi-6df7f26dcad


    @Operation(summary = "DELETE",
            description = "delete FLOWER from the database")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "FLOWER DELETED."),
            @ApiResponse(responseCode = "404", description = "ERROR. THE ID ENTERED DOES NOT EXIST."),
            })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteFlower(@PathVariable int id) {
        ResponseEntity<Message> checkId = flowerServiceImplement.validateFruitaEntityId(id);
        if (checkId.getStatusCode() == HttpStatus.OK) {
            flowerServiceImplement.deleteFlower(id);
            return new ResponseEntity<>(new Message("FLOWER DELETED."), HttpStatus.OK);
        } else {
            return checkId;
        }
    }


    //Read a Flower
    @Operation(summary = "GET ONE",
            description = "Get one FLOWERS from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "show a flower from database"),
            @ApiResponse(responseCode = "404", description = "FLOWER NOT FOUND, NOT FOUND WITH ID  : '0'"),
    })
    @GetMapping("/getOne/{id}")
    public ResponseEntity<FlowerDTO> getFlowerById(@PathVariable(name = "id") int id) {
        FlowerDTO flowerDTO = flowerServiceImplement.getFlowerDtoById(id);
        return ResponseEntity.ok().body(flowerDTO);
    }


    // Read all branches

      @Operation(summary = "GET ALL",
            description = "Get all flowers from the database")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Show all the Flowers available from database")})

    @GetMapping("/getAll")
    public List<FlowerDTO> getAllFlower() {
        return flowerServiceImplement.getAllFlowers();
    }


}

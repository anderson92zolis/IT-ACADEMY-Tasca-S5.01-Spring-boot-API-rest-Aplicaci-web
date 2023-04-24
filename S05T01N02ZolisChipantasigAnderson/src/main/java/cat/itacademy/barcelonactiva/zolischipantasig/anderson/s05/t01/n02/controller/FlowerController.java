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

    //add a new user

    @Operation(summary = "ADD",description = "update a flower from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created a new donor"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found"),
            @ApiResponse(responseCode = "500", description = "Application failed to process the request")
            })
    @PostMapping("/add")
    public ResponseEntity<Message> addFruita(@RequestBody FlowerDTO flowerDTO) {
        ResponseEntity<Message> validationResult = flowerServiceImplement.validateFruitaDto(flowerDTO);

        if (validationResult.getStatusCode() == HttpStatus.OK) {
            flowerServiceImplement.createFlower(flowerDTO);
            return new ResponseEntity<>(new Message("Successfully added flower"), HttpStatus.CREATED);
        } else {
            return validationResult;
        }
    }

    // update an existing Flower

    @Operation(summary = "UPDATE",
            description = "update a flower from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated donor information"),
            @ApiResponse(responseCode = "204", description = "Created"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found"),
            @ApiResponse(responseCode = "500", description = "Application failed to process the request") })
    @PutMapping("/update/{id}")
    public ResponseEntity<FlowerDTO> updateFlower(@PathVariable int id, @RequestBody FlowerDTO flowerDTO) {
        // update the Flower
         flowerServiceImplement.updateFlower(id, flowerDTO);
        return ResponseEntity.ok().body(flowerDTO);
    }
    // delete an existing Flower

    //https://medium.com/swlh/restful-api-documentation-made-easy-with-swagger-and-openapi-6df7f26dcad


    @Operation(summary = "DELETE",
            description = "delete flower from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESSFULLY DELETES THE SPECIFIC DONOR"),
            @ApiResponse(responseCode = "204", description = "NO CONTENT"),
            @ApiResponse(responseCode = "401", description = "YOU ARE NOT AUTHORIZED TO VIEW THE RESOURCE"),
            @ApiResponse(responseCode = "403", description = "ACCESSING THE RESOURCE YOU WERE TRYING TO REACH IS FORBIDDEN"),
            @ApiResponse(responseCode = "404", description = "THE RESOURCE YOU WERE TRYING TO REACH IS NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "APPLICATION FAILED TO PROCESS THE REQUEST") })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteFlower(@PathVariable int id) {
        ResponseEntity<Message> checkId = flowerServiceImplement.validateFruitaEntityId(id);
        if (checkId.getStatusCode() == HttpStatus.OK) {
            flowerServiceImplement.deleteFlower(id);
            return new ResponseEntity<>(new Message("Flower deleted."), HttpStatus.OK);
        } else {
            return checkId;
        }
    }


    //read a Flower
    @Operation(summary = "GET ONE",
            description = "Get one flowers from the database")
    @GetMapping("/getOne/{id}")
    public ResponseEntity<FlowerDTO> getFlowerById(@PathVariable(name = "id") int id) {
        FlowerDTO flowerDTO = flowerServiceImplement.getFlowerDtoById(id);

        return ResponseEntity.ok().body(flowerDTO);
    }


    // read all branches

    //@ApiOperation(value = "Create a new donor", nickname = "createDonorUsingPOST", notes = "", response = FlowerDTO.class, tags={ "donor-controller", })

    @Operation(summary = "GET ALL",
            description = "Get all flowers from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode =  "200", description = "SUCEESS|OK"),
            @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED!"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN!!!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND!!!") })
    @GetMapping("/getAll")
    public List<FlowerDTO> getAllFlower() {
        return flowerServiceImplement.getAllFlowers();
    }


}

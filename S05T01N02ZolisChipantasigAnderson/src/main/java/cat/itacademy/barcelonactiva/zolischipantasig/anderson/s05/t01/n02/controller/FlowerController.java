package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.controller;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.dto.Message;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.services.FlowerServiceImplement;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping("/update/{id}")
    public ResponseEntity<FlowerDTO> updateFlower(@PathVariable int id, @RequestBody FlowerDTO flowerDTO) {
        // update the Flower
         flowerServiceImplement.updateFlower(id, flowerDTO);
        return ResponseEntity.ok().body(flowerDTO);
    }
    // delete an existing Flower
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
    @GetMapping("/getOne/{id}")
    @ApiOperation(value = "Obtenir un exemple per ID")
    public ResponseEntity<FlowerDTO> getFlowerById(@PathVariable(name = "id") int id) {
        FlowerDTO flowerDTO = flowerServiceImplement.getFlowerDtoById(id);

        return ResponseEntity.ok().body(flowerDTO);
    }


    // read all branches
    @ApiOperation(value = "Get list of Students in the System ", response = Iterable.class, tags = "getStudents")
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

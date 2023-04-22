package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.controller;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.domain.FlowerEntity;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.dto.Message;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.services.FlowerServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flowers")
public class FlowerController {


    private FlowerServiceImplement flowerServiceImplement;

    @Autowired
    public FlowerController(FlowerServiceImplement flowerServiceImplement) {
        this.flowerServiceImplement = flowerServiceImplement;
    }

    //add a new user
    @PostMapping("/add")
    public ResponseEntity<Message> addFruita(@RequestBody FlowerDTO flowerDTO) {
        ResponseEntity<Message> validationResult = flowerServiceImplement.validateFruitaDto(flowerDTO);

        if (validationResult.getStatusCode() == HttpStatus.OK) {
            flowerServiceImplement.createFlower(flowerDTO);
            return new ResponseEntity<>(new Message("Successfully created fruit"), HttpStatus.CREATED);
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


    //read an Flower
    @GetMapping("/getOne/{id}")
    public ResponseEntity<FlowerDTO> getFlowerById(@PathVariable(name = "id") int id) {
        FlowerDTO flowerDTO = flowerServiceImplement.getFlowerDtoById(id);

        return ResponseEntity.ok().body(flowerDTO);
    }


    // read all branches
    @GetMapping("/getAll")
    public List<FlowerDTO> getAllFlower() {
        return flowerServiceImplement.getAllFlowers();
    }





}

package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.model.service;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n03.model.dto.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface WebClientFlowerServiceInterface {

    // Get a single Flower by ID
    Mono<FlowerDTO> getFlowerDtoById(int id);

    // Get a list of all Flowers
    Flux<FlowerDTO> getAllFlowers();

    // Create a new Flower
    Mono<FlowerDTO> createFlower(FlowerDTO flowerDTO);

    // Update an existing Flower
    Mono<FlowerDTO> updateFlower(int id, FlowerDTO flowerDTO);

    // Delete a Flower by ID
    Mono<String> deleteFlower(int id);
}

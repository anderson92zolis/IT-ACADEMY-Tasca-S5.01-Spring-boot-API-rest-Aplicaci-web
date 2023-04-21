package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.services;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.dto.FlowerDTO;

import java.util.List;

public interface FlowerServiceInterface {


    FlowerDTO getFlowerDtoById(int id);

    List<FlowerDTO> getAllFlowers();


    void createFlower(FlowerDTO flowerDTO);


    void updateFlower(int id, FlowerDTO flowerDTO);

    void deleteFlower(int id);
}

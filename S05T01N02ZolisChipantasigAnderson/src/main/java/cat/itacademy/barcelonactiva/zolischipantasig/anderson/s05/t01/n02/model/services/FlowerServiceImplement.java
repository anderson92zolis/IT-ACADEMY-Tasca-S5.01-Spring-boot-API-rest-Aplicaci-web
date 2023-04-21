package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.services;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.repository.IFlowerRepository;

import java.util.List;

public class FlowerServiceImplement implements FlowerServiceInterface{

    private IFlowerRepository iFlowerRepository;

    public FlowerServiceImplement(IFlowerRepository iFlowerRepository) {
        super();
        this.iFlowerRepository = iFlowerRepository;
    }

    @Override
    public FlowerDTO getFlowerDtoById(int id) {
        return null;
    }

    @Override
    public List<FlowerDTO> getAllFlowers() {
        return null;
    }

    @Override
    public void createFlower(FlowerDTO flowerDTO) {

    }

    @Override
    public void updateFlower(int id, FlowerDTO flowerDTO) {

    }

    @Override
    public void deleteFlower(int id) {

    }
}

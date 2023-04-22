package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.services;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.domain.FlowerEntity;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.exceptions.ResourceNotFoundException;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.repository.IFlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerServiceImplement implements FlowerServiceInterface{

    @Autowired
    private IFlowerRepository iFlowerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public FlowerServiceImplement(IFlowerRepository iFlowerRepository) {
        super();
        this.iFlowerRepository = iFlowerRepository;
    }

    // using ModelMapper to convert
    private FlowerDTO convertoToFlowerDto(FlowerEntity flowerEntity) {
        return modelMapper.map(flowerEntity, FlowerDTO.class);
    }

    private FlowerEntity convertToFlowerEntity(FlowerDTO flowerDTO) {
        return modelMapper.map(flowerDTO, FlowerEntity.class);
    }

    @Override
    public FlowerDTO getFlowerDtoById(int id) {
        Optional<?> result = iFlowerRepository.findById(id);
        if (result.isPresent()) {
            return convertoToFlowerDto((FlowerEntity) result.get());
        } else {
            throw new ResourceNotFoundException("Flower NOT FOUND", "ID ", id);
        }
    }

    @Override
    public List<FlowerDTO> getAllFlowers() {
        List<FlowerEntity> flowerRepositoryFromDB = iFlowerRepository.findAll();
        return flowerRepositoryFromDB.stream().map(this::convertoToFlowerDto).collect(Collectors.toList());
    }

    @Override
    public void createFlower(FlowerDTO flowerDTO) {
        iFlowerRepository.save(convertToFlowerEntity(flowerDTO));
    }

    @Override
    public void updateFlower(int id, FlowerDTO flowerDTO) {

        FlowerDTO floweDtoFromDB = convertoToFlowerDto(iFlowerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch not found", "id", id)));

        floweDtoFromDB.setNameFlower(flowerDTO.getNameFlower());
        floweDtoFromDB.setCountryFlower(flowerDTO.getCountryFlower());

        iFlowerRepository.save(convertToFlowerEntity(floweDtoFromDB));

    }

    @Override
    public void deleteFlower(int id) {
        FlowerDTO flowerDTO = getFlowerDtoById(id);
        iFlowerRepository.delete(convertToFlowerEntity(flowerDTO));
    }
}

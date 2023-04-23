package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.services;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.domain.FlowerEntity;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.dto.Message;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.exceptions.ResourceNotFoundException;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n02.model.repository.IFlowerRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /*
     * This method validates the existence of a fruit by ID in the REST controller's methods.
     */

    public ResponseEntity<Message> validateFruitaEntityId(int id) {
        if (iFlowerRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("Fruit ID validated successfully."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("ERROR. The ID entered does not exist."), HttpStatus.NOT_FOUND);
        }
    }

    /*
     * Method to validate the entered data (name and quantityKg) of the fruit in the RestController methods.
     */

    public ResponseEntity<Message> validateFruitaDto(FlowerDTO flowerDTO) {

        if (StringUtils.isBlank(flowerDTO.getNameFlower())) {
            return new ResponseEntity<>(new Message("ERROR. The Name is required."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(flowerDTO.getCountryFlower())) {
            return new ResponseEntity<>(new Message("ERROR. The Country is required."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message("Flower validated successfully."), HttpStatus.OK);
    }



}

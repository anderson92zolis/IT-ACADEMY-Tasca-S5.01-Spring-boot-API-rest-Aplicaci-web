package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.domain.Branch;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.dto.BranchDto;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.exceptions.ResourceNotFoundException;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.repository.BranchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchServicesInterface {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BranchServiceImpl(BranchRepository branchRepository) {
        super();
        this.branchRepository = branchRepository;
    }


    // using ModelMapper to convert
    private BranchDto convertoToBranchDto(Branch branch) {
        return modelMapper.map(branch, BranchDto.class);
    }

    private Branch convertToBranchEntity(BranchDto branchDto) {
        return modelMapper.map(branchDto, Branch.class);
    }

    @Override
    public void createBranch(BranchDto branchDto) {
        branchRepository.save(convertToBranchEntity(branchDto));
    }


    @Override
    public List<BranchDto> getAllBranches() {
        List<Branch> branchRepositoryFromDB = branchRepository.findAll();
        return branchRepositoryFromDB.stream().map(this::convertoToBranchDto).collect(Collectors.toList());
    }


    @Override
    public BranchDto getBranchDtoById(int id) {
        Optional<?> result = branchRepository.findById(id);
        if (result.isPresent()) {
            return convertoToBranchDto((Branch) result.get());
        } else {
            throw new ResourceNotFoundException("Branch not found", "id", id);
        }
    }

    @Override
    public void updateBranch(int id, BranchDto branchDtoRequest) {
        BranchDto branckDtoFromDB = convertoToBranchDto(branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch not found", "id", id)));

        branckDtoFromDB.setBranchName(branchDtoRequest.getBranchName());
        branckDtoFromDB.setBranchCountry(branchDtoRequest.getBranchCountry());

        branchRepository.save(convertToBranchEntity(branckDtoFromDB));
    }

    @Override
    public void deleteBranch(int id) {

        BranchDto branchDto =getBranchDtoById(id);

        branchRepository.delete(convertToBranchEntity(branchDto));

    }



}


    /*



    @Override
    public void deleteBranch(int id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "id", id));

        branchRepository.delete(branch);

    }


    public ResponseEntity<Message> validateBranchId(int id) {
        if (branchRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("Fruit ID validated successfully."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("ERROR. The ID entered does not exist."), HttpStatus.NOT_FOUND);
        }
    }

     */


package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.domain.Branch;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.dto.BranchDto;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.dto.Message;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.exceptions.ResourceNotFoundException;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.repository.BranchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchServicesInterface{

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BranchServiceImpl (BranchRepository branchRepository){
        super();
        this.branchRepository= branchRepository;
    }





    // using ModelMapper to convert
    private BranchDto convertoToBranchDto(Branch branch){
        return modelMapper.map(branch, BranchDto.class);
    }
    private Branch convertToBranchEntity(BranchDto branchDto){
        return modelMapper.map(branchDto, Branch.class);
    }

    @Override
    public void createBranch(BranchDto branchDto) {
        branchRepository.save(convertToBranchEntity(branchDto));
    }


    @Override
    public List<BranchDto> getAllBranches() {
        List<Branch> branchRepositoryFromDB= branchRepository.findAll();
        return branchRepositoryFromDB.stream().map(this::convertoToBranchDto).collect(Collectors.toList());
    }


    @Override
    public Branch updateBranch(Integer id, BranchDto branchDtoRequest) {
        Branch branckFromDB = branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch", "id", id));

        branckFromDB.setBranchName(branchDtoRequest.getBranchName());
        branckFromDB.setBranchCountry(branchDtoRequest.getBranchCountry());

        return branchRepository.save(branckFromDB);
    }

    @Override
    public void deleteBranch(Integer id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "id", id));

        branchRepository.delete(branch);

    }

    @Override
    public Branch getBranchById(Integer id) {
        Optional<Branch> result = branchRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new ResourceNotFoundException("Post", "id", id);
        }
    }

    @Override
    public BranchDto getBranchDtoById(Integer id) {
        Optional<?> result = branchRepository.findById(id);
        if(result.isPresent()) {
            return (BranchDto) result.get();
        }else {
            throw new ResourceNotFoundException("Post", "id", id);
        }
    }




    public ResponseEntity<Message> validateBranchId(int id) {
        if (branchRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("Fruit ID validated successfully."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("ERROR. The ID entered does not exist."), HttpStatus.NOT_FOUND);
        }
    }
}

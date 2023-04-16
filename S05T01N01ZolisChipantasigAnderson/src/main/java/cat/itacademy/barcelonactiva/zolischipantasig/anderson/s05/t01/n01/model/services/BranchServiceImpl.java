package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.domain.Branch;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.dto.Message;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.exceptions.ResourceNotFoundException;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchServicesInterface{

    @Autowired
    private BranchRepository branchRepository;

    public BranchServiceImpl (BranchRepository branchRepository){
        super();
        this.branchRepository= branchRepository;
    }



    @Override
    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public Branch updateBranch(Integer id, Branch branchRequest) {
        Branch branckFromDB = branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch", "id", id));

        branckFromDB.setBranchName(branchRequest.getBranchName());
        branckFromDB.setBranchCountry(branchRequest.getBranchCountry());

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
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public ResponseEntity<Message> validateBranchId(int id) {
        if (branchRepository.existsById(id)) {
            return new ResponseEntity<>(new Message("Fruit ID validated successfully."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("ERROR. The ID entered does not exist."), HttpStatus.NOT_FOUND);
        }
    }
}

package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.domain.Branch;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.repository.BranchRepository;
import net.javaguides.springboot.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public class BranchServiceImpl implements BranchServicesInterface{

    private BranchRepository branchRepository;

    public BranchServiceImpl (BranchRepository branchRepository){
        super();
        this.branchRepository= branchRepository;
    }

    @Override
    public List<Branch> getAllPosts() {
        return branchRepository.findAll();
    }

    @Override
    public Branch createPost(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public Branch updatePost(Integer id, Branch branchDto) {
        Branch branckFromDB = branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch", "id", id));

        branckFromDB.setBranchName(branchDto.getBranchName());
        branckFromDB.setBranchCountry(branchDto.getBranchCountry());

        return branchRepository.save(branckFromDB);
    }

    @Override
    public void deletePost(Integer id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "id", id));

        branchRepository.delete(branch);

    }

    @Override
    public Branch getPostById(Integer id) {
        Optional<Branch> result = branchRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new ResourceNotFoundException("Post", "id", id);
        }
    }
}

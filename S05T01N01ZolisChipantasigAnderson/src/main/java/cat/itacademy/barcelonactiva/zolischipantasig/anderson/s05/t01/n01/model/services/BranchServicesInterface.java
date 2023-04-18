package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.domain.Branch;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.dto.BranchDto;

import java.util.List;

public interface BranchServicesInterface {
    List<Branch> getAllBranches();

    Branch createBranch(Branch branch);

    Branch updateBranch(Integer id, BranchDto branchDto);

    void deleteBranch(Integer id);

    Branch getBranchById(Integer id);
}

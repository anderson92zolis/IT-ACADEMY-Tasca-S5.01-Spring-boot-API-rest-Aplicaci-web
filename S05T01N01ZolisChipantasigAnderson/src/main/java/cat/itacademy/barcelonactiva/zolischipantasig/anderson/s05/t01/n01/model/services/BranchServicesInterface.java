package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.dto.BranchDto;

import java.util.List;

public interface BranchServicesInterface {

    void createBranch(BranchDto branchDto);

    List<BranchDto> getAllBranches();


    BranchDto getBranchDtoById(int id);

    void updateBranch(int id, BranchDto branchDto);

    void deleteBranch(int id);

}

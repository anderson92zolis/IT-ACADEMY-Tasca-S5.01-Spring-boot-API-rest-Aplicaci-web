package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.domain.Branch;
import java.util.List;

public interface BranchServicesInterface {
    List<Branch> getAllPosts();

    Branch createBranch(Branch branch);

    Branch updateBranch(Integer id, Branch branch);

    void deleteBranch(Integer id);

    Branch getPostById(Integer id);
}

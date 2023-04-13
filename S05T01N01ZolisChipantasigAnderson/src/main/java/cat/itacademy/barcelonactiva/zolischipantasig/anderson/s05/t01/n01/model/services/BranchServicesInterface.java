package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.services;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.domain.Branch;
import java.util.List;

public interface BranchServicesInterface {
    List<Branch> getAllPosts();

    Branch createPost(Branch branch);

    Branch updatePost(Integer id, Branch branch);

    void deletePost(Integer id);

    Branch getPostById(Integer id);
}

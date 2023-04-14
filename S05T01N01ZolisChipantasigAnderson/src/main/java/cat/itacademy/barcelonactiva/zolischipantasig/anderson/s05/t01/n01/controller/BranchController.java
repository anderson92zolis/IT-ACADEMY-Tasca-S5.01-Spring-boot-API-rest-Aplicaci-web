package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.controller;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.domain.Branch;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.dto.BranchDto;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.services.BranchServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BranchServiceImpl branchServiceImpl;

    public BranchController (BranchServiceImpl branchServiceImpl){
        this.branchServiceImpl=branchServiceImpl;
    }

    @PostMapping("/add")
    public ResponseEntity<BranchDto> createPost(@RequestBody BranchDto branchDto) {

        // convert DTO to entity
        Branch branchRequest = modelMapper.map(branchDto, Branch.class);

        Branch branch = branchServiceImpl.createPost(branchRequest);

        // convert entity to DTO
        BranchDto branchResponse = modelMapper.map(branch, BranchDto.class);

        return new ResponseEntity<BranchDto>(branchResponse, HttpStatus.CREATED);
    }
}

package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.controller;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.dto.BranchDto;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.services.BranchServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sucursal")
public class BranchController {

    @Autowired
    private BranchServiceImpl branchServiceImpl;


    public BranchController (BranchServiceImpl branchServiceImpl){
        this.branchServiceImpl=branchServiceImpl;
    }


    @GetMapping("/add")
    public String createBranch(Model model) {

        // create branch object to hold branch form data

        BranchDto neWBranchDto = new BranchDto();
        model.addAttribute("newbranchDto", neWBranchDto);
        return "newbranchdtopage";
    }

    @PostMapping("/saveBranch")
    public String saveBranch(@Valid @ModelAttribute("newbranchDto") BranchDto branchDto, BindingResult result) {
        if(result.hasErrors()) {
            return "newbranchdtopage";
    } else {
            branchServiceImpl.createBranch(branchDto);
            return "redirect:/sucursal/getAll";}
    }

    @GetMapping(value= {"/getAll","/",""})
    public String viewBranchOffices(Model model) {
        List<BranchDto> listBranchesDto= branchServiceImpl.getAllBranches(); // we can put directly to the addAttribute
        model.addAttribute("branchesDto", listBranchesDto);
        return "indexpage";
    }


    @GetMapping("/update/{id}")
    public String BranchForUpdate(@PathVariable int id, Model model) {

        // get employee from the service
        BranchDto editedBranchDto = branchServiceImpl.getBranchDtoById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("editedBranchDto", editedBranchDto);
        return "updateBranch";
    }

    @PostMapping("/branchUpdate/{id}")
    public String updateBranch(@PathVariable("id") int id, @ModelAttribute("editedBranchDto") BranchDto branchDto, Model model) {

        // get Branch from database by id
        BranchDto existingBranch = branchServiceImpl.getBranchDtoById(id);

        existingBranch.setBranchName(branchDto.getBranchName());
        existingBranch.setBranchCountry(branchDto.getBranchCountry());
        

        // save updated Branch object
        branchServiceImpl.updateBranch(existingBranch.getPk_BranchID(),existingBranch);
        return "redirect:/sucursal";
    }

    @GetMapping("/delete/{id}")
    public String deleteBranch(@PathVariable(value = "id") Integer id) {
        // call delete employee method
        this.branchServiceImpl.deleteBranch(id);
        return "redirect:/sucursal";
    }

    @GetMapping("/getOne")
    public String viewOneBranch(@RequestParam("id") int id,Model model) {

        BranchDto oneBranchDto= branchServiceImpl.getBranchDtoById(id); // we can put directly to the addAttribute

        if (oneBranchDto != null) {
            model.addAttribute("oneBranch", oneBranchDto);
            return "oneBranchPage";
        } else {
            return "branchNotFound";
        }
    }


    /*

    using modelMapper in the controller

    // update an existing branch
    @PutMapping("/update/{id}")
    public ResponseEntity<BranchDto> updateBranch(@PathVariable int id, @RequestBody BranchDto branchDto) {

        // convert DTO to Entity
        Branch branchRequest = modelMapper.map(branchDto, Branch.class);

        Branch branch = branchServiceImpl.updateBranch(id, branchRequest);

        // entity to DTO
        BranchDto branchResponse = modelMapper.map(branch, BranchDto.class);

        return ResponseEntity.ok().body(branchResponse);
    }


    // delete an existing branch
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteBranch(@PathVariable int id) {
        ResponseEntity<Message> checkId = branchServiceImpl.validateBranchId(id);

        if (checkId.getStatusCode() == HttpStatus.OK) {
            branchServiceImpl.deleteBranch(id);
            return new ResponseEntity<>(new Message("Branch deleted."), HttpStatus.OK);
        } else {
            return checkId;
        }
    }


    //read an branch
    @GetMapping("/getOne/{id}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable(name = "id") int id) {
        Branch branch = branchServiceImpl.getBranchById(id);

        // convert entity to DTO
        BranchDto postResponse = modelMapper.map(branch, BranchDto.class);

        return ResponseEntity.ok().body(postResponse);
    }

    // read all branches
    @GetMapping("/getAll")
    public List<BranchDto> getAllBranch() {

        return branchServiceImpl.getAllBranches().stream()
                .map(post -> modelMapper.map(post, BranchDto.class))
                .collect(Collectors.toList());
    }
    */
}


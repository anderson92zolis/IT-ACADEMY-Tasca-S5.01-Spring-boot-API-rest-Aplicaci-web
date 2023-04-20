package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.controller;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.dto.BranchDto;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.services.BranchServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping(value= {"/getAll",""})
    public String viewBranchOffices(Model model) {
        List<BranchDto> listBranchesDto= branchServiceImpl.getAllBranches(); // we can put directly to the addAttribute
        model.addAttribute("branchesDto", listBranchesDto);
        return "indexpage";
    }

    /*



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

    // to the web

    */


    // video   and github https://github.com/RameshMF/student-management-system-springboot/blob/main/src/main/java/net/javaguides/sms/controller/StudentController.java



    /*










    @GetMapping("/deleteBranch/{id}")
    public String deleteBranch(@PathVariable(value = "id") Integer id) {
        // call delete employee method
        this.branchServiceImpl.deleteBranch(id);
        return "redirect:/sucursal";
    }

    @GetMapping("/branch/update/{id}")
    public String showBranchForUpdate(@PathVariable(value = "id") Integer id, Model model) {

        // get employee from the service
        Branch branch = branchServiceImpl.getBranchById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("branch", branch);
        return "updateBranch";
    }

    @PostMapping("/branchUpdate/{id}")
    public String branchUpdate(@PathVariable Integer id,
                                @ModelAttribute("branch") Branch branch,
                                Model model) {

        // get Branch from database by id
        BranchDto existingBranch = branchServiceImpl.getBranchDtoById(id);
        existingBranch.setPk_BranchID(id);
        existingBranch.setBranchName(branch.getBranchName());
        existingBranch.setBranchCountry(branch.getBranchCountry());

        //existingBranch.setEmail(studbranchent.getEmail());

        // save updated Branch object
        branchServiceImpl.updateBranch(existingBranch.getPk_BranchID(),existingBranch);
        return "redirect:/branches";
    }


    */






}


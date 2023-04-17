package cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.controller;

import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.domain.Branch;
import cat.itacademy.barcelonactiva.zolischipantasig.anderson.s05.t01.n01.model.services.BranchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursal")
public class BranchController {

    /*
    @Autowired
    private ModelMapper modelMapper;
     */

    @Autowired
    private BranchServiceImpl branchServiceImpl;

    public BranchController (BranchServiceImpl branchServiceImpl){
        this.branchServiceImpl=branchServiceImpl;
    }

    /*


    //add a new branch
    @PostMapping("/add")
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) {

        // convert DTO to entity
        Branch branchRequest = modelMapper.map(branchDto, Branch.class);

        Branch branch = branchServiceImpl.createBranch(branchRequest);

        // convert entity to DTO
        BranchDto branchResponse = modelMapper.map(branch, BranchDto.class);

        return new ResponseEntity<BranchDto>(branchResponse, HttpStatus.CREATED);
    }

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

    // endpoints for thymeleaf

    @GetMapping(value = "/listEmployees")
    public String listEmployees(Model model) {
        List<Branch> listBranches= branchServiceImpl.getAllBranches(); // we can put directly to the addAttribute
        model.addAttribute("listEmployees", listBranches);
        model.addAttribute("listEmployees", listBranches);
        return "listEmployees.html";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Branch branch = new Branch();
        model.addAttribute("employee", branch);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Branch branch) {
        // save employee to database
        branchServiceImpl.createBranch(branch);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {

        // get employee from the service
        Branch employee = branchServiceImpl.getBranchById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Integer id) {

        // call delete employee method
        this.branchServiceImpl.deleteBranch(id);
        return "redirect:/";
    }



}


package com.module2.module2MVC.controllers;

import com.module2.module2MVC.dto.EmployeeDTO;
import com.module2.module2MVC.exceptions.ResourceNotFoundException;
import com.module2.module2MVC.services.EmployeeService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Seccret message: hajsgajh%#^%Y";
//    }

//    private final EmployeeRepository employeeRepository;
//
//    public EmployeeController(EmployeeRepository employeeRepository){
//        this.employeeRepository = employeeRepository;
//    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeId(@PathVariable(name = "employeeId") Long id){
//        return new EmployeeDTO(id, "Harshit", "harshit@gmail.com", 28, LocalDate.of(2026, 05, 30), true);
//        return employeeRepository.findById(id).orElse(null);

        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
//                .orElse(ResponseEntity.notFound().build());
    }

    //All the "No such element" exception in this controller will be handled by this function
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> handleEmployeeNotFound(NoSuchElementException exception){
//        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
//    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);

        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                 @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
        if(employeeDTO == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(employeeDTO);
    }

//    @GetMapping(path = "/")
//    public String getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
//        return "Hi age " + age + " " + sortBy;
//    }

//    @PostMapping
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        inputEmployee.setId(100L);
//        return inputEmployee;
//    }
}

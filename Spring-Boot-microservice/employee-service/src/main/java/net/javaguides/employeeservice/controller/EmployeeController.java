package net.javaguides.employeeservice.controller;

import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.model.Employee;
import net.javaguides.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-employees")
public class EmployeeController {

private EmployeeService employeeService;
@Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }
@PostMapping("/save-employee")
    public ResponseEntity<?> saveNewEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto save=employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") Long id){
   APIResponseDto apiResponseDto =employeeService.getEmployeeById(id);
    return new ResponseEntity<>(apiResponseDto,HttpStatus.OK);
    }
}

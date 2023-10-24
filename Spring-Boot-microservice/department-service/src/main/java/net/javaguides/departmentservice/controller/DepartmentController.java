package net.javaguides.departmentservice.controller;

import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-departments")
public class DepartmentController {
    private DepartmentService departmentService;
@Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @PostMapping("/save-department")
    public ResponseEntity<?> saveDepartment(@RequestBody DepartmentDto departmentDto){
    DepartmentDto saveDepartment=departmentService.saveDepartment(departmentDto);
    return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }
    @GetMapping("{departmentCode}")
    public ResponseEntity<?> getByCodeDepartment(@PathVariable String departmentCode){
    DepartmentDto departmentDto=departmentService.getDepartmentByCode(departmentCode);
    return new ResponseEntity<>(departmentDto,HttpStatus.OK);
    }




}

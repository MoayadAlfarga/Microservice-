package net.javaguides.departmentservice.service;

import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.model.Department;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository,ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
    this.modelMapper=modelMapper;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department=modelMapper.map(departmentDto,Department.class);
        Department save=departmentRepository.save(department);
        DepartmentDto saveDepartmentDto=modelMapper.map(department,DepartmentDto.class);
        return saveDepartmentDto;
    }
    @Override
    public DepartmentDto getDepartmentByCode(String codeDepartment) {
        Department department=departmentRepository.findByDepartmentCode(codeDepartment);
        return modelMapper.map(department,DepartmentDto.class);
    }

//
//@Override
//public DepartmentDto getDepartmentByCode(String codeDepartment) {
//    Department Department=departmentRepository.findByDepartmentCode(codeDepartment);
//
//    DepartmentDto departmentDto=new DepartmentDto(Department.getId(), Department.getDepartmentName(), Department.getDepartmentDescription(), Department.getDepartmentCode());
//    return departmentDto;
//
//}
}

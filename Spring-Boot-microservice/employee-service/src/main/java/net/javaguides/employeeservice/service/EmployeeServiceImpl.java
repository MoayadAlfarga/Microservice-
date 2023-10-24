package net.javaguides.employeeservice.service;

import lombok.AllArgsConstructor;
import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.model.Employee;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private RestTemplate restTemplate;
@Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper,RestTemplate restTemplate) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.restTemplate=restTemplate;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee=modelMapper.map(employeeDto,Employee.class);
        Employee save=employeeRepository.save(employee);
        EmployeeDto saveEmployeeDto=modelMapper.map(employee,EmployeeDto.class);

        return saveEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long id) {
    Employee employee=employeeRepository.findById(id).get();

//EmployeeDto employeeDto=new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getDepartmentCode());
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
                  "http://localhost:8888/api-departments/" + employee.getDepartmentCode(),
                  DepartmentDto.class
        );

        DepartmentDto departmentDto=responseEntity.getBody();
        EmployeeDto employeeDto=new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getDepartmentCode());
APIResponseDto apiResponseDto=new APIResponseDto();
apiResponseDto.setDepartmentDto(departmentDto);
apiResponseDto.setEmployeeDto(employeeDto);
        return apiResponseDto;
    }


//    @Override
//    public APIResponseDto getEmployeeById(Long id) {
//    Employee employee=employeeRepository.findById(id).get();
//        ResponseEntity<DepartmentDto> responseEntity=restTemplate.getForEntity("http://localhost:8888/api-departments/get-Department/"
//                            +employee.getDepartmentCode(),
//                  DepartmentDto.class);
//        DepartmentDto departmentDto=responseEntity.getBody();
//    EmployeeDto employeeDto=modelMapper.map(employee,EmployeeDto.class);
//        APIResponseDto apiResponseDto=new APIResponseDto();
//        apiResponseDto.setEmployeeDto(employeeDto);
//        apiResponseDto.setDepartmentDto(departmentDto);
//    return apiResponseDto;
//    }
}

package net.javaguides.departmentservice.repository;

import net.javaguides.departmentservice.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository  extends JpaRepository<Department,Long>{
Department findByDepartmentCode(String codeDepartment);

}

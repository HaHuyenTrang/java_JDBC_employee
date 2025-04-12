package business.service.department;


import business.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
    Department findById(int id);
    boolean save(Department department);
    boolean update(Department department);
    boolean delete(Department department);
    boolean hasDepartmentEmployees(Department department);
    List<Department> searchByName(String name);
}

package business.dao.department;

import business.model.Department;
import java.util.List;

public interface DepartmentDAO {
    List<Department> getAll();

    Department getById(int id);

    boolean insert(Department department);

    boolean update(Department department);

    boolean delete(int id);

    List<Department> searchByName(String name);

    boolean hasDepartmentEmployees(Department department);

}

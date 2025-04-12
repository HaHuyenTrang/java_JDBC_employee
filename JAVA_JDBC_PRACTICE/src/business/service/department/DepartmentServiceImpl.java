package business.service.department;

import business.dao.department.DepartmentDAO;
import business.dao.department.DepartmentDAOImp;
import business.model.Department;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDAO departmentDAO;

    public DepartmentServiceImpl() {
        this.departmentDAO = new DepartmentDAOImp();
    }

    @Override
    public List<Department> findAll() {
        return departmentDAO.getAll();
    }

    @Override
    public Department findById(int id) {
        return departmentDAO.getById(id);
    }

    @Override
    public boolean save(Department department) {
        return departmentDAO.insert(department);
    }

    @Override
    public boolean update(Department department) {
        return departmentDAO.update(department);
    }

    @Override
    public boolean delete(Department department) {
        return departmentDAO.delete(department.getDepartmentId());
    }

    @Override
    public List<Department> searchByName(String name) {
        return departmentDAO.searchByName(name);
    }

    @Override
    public boolean hasDepartmentEmployees(Department department) {
        return departmentDAO.hasDepartmentEmployees(department);
    }

}

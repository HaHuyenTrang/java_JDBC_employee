package business.dao.department;

import business.config.ConnectionDB;
import business.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImp implements DepartmentDAO {

    @Override
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM department";

        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Department department = new Department();
                department.setDepartmentId(rs.getInt("id"));
                department.setDepartmentName(rs.getString("name"));
                department.setDescription(rs.getString("description"));
                department.setStatus(Department.DepartmentStatus.valueOf(rs.getString("status")));
                departments.add(department);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách phòng ban: " + e.getMessage());
        }

        return departments;
    }

    @Override
    public Department getById(int id) {
        String query = "SELECT * FROM department WHERE id = ?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Department department = new Department();
                    department.setDepartmentId(rs.getInt("id"));
                    department.setDepartmentName(rs.getString("name"));
                    department.setDescription(rs.getString("description"));
                    department.setStatus(Department.DepartmentStatus.valueOf(rs.getString("status")));
                    return department;
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm phòng ban theo ID: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean insert(Department department) {
        String query = "INSERT INTO department(name, description, status) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, department.getDepartmentName());
            stmt.setString(2, department.getDescription());
            stmt.setString(3, department.getStatus().name());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        department.setDepartmentId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm phòng ban: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(Department department) {
        String query = "UPDATE department SET name = ?, description = ?, status = ? WHERE id = ?";

        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, department.getDepartmentName());
            stmt.setString(2, department.getDescription());
            stmt.setString(3, department.getStatus().name());
            stmt.setInt(4, department.getDepartmentId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật phòng ban: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM department WHERE id = ?";

        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa phòng ban: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Department> searchByName(String name) {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM department WHERE name LIKE ?";

        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Department department = new Department();
                    department.setDepartmentId(rs.getInt("id"));
                    department.setDepartmentName(rs.getString("name"));
                    department.setDescription(rs.getString("description"));
                    department.setStatus(Department.DepartmentStatus.valueOf(rs.getString("status")));
                    departments.add(department);
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm phòng ban theo tên: " + e.getMessage());
        }

        return departments;
    }

    @Override
    public boolean hasDepartmentEmployees(Department department) {
        String query = "SELECT COUNT(*) FROM employee WHERE department_id = ?";

        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, department.getDepartmentId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra nhân viên của phòng ban: " + e.getMessage());
        }

        return false;
    }
}

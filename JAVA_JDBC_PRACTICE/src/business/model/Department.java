package business.model;
import business.validate.Validator;

public class Department {
    private Integer departmentId; // tự tăng
    private String departmentName;
    private String description;
    private DepartmentStatus status;

    public enum DepartmentStatus {
        ACTIVE, INACTIVE
    }

    // Constructors
    public Department() {
        this.status = DepartmentStatus.ACTIVE;
    }

    public Department(String departmentName, String description) {
        setDepartmentName(departmentName);
        setDescription(description);
        this.status = DepartmentStatus.ACTIVE;
    }

    // Getters and Setters
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        Validator.inputDepartmentName();
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        Validator.inputDepartmentDescription();
        this.description = description;
    }

    public DepartmentStatus getStatus() {
        return status;
    }

    public void setStatus(DepartmentStatus status) {
        Validator.inputDepartmentStatus();
        this.status = status;
    }
}
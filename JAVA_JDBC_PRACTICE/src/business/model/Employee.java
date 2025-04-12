package business.model;

import business.validate.Validator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {
    private String employeeId; // 5 kí tự bắt đầu = e
    private String employeeName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private Integer salaryGrade;
    private Double salary;
    private LocalDate dateOfBirth;
    private String address;
    private EmployeeStatus status;
    private Department department;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum EmployeeStatus {
        ACTIVE, INACTIVE, ONLEAVE, POLICYLEAVE
    }

    // định dàng ngày tháng năm
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Constructors
    public Employee() {
        this.status = EmployeeStatus.ACTIVE;
    }

    // Getters và Setters with validation
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        Validator.inputEmployeeId();
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        Validator.inputEmployeeName();
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Validator.inputEmail();
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        Validator.inputPhoneNumber();
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        Validator.inputGender();
        this.gender = gender;
    }

    public Integer getSalaryGrade() {
        return salaryGrade;
    }

    public void setSalaryGrade(Integer salaryGrade) {
        Validator.inputSalaryGrade();
        this.salaryGrade = salaryGrade;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        Validator.inputSalary();
        this.salary = salary;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        Validator.inputDateOfBirth();
        this.dateOfBirth = dateOfBirth;
    }

    // chuỗi -> ngày sinh
    public void setDateOfBirth(String dateOfBirthStr) {
        this.dateOfBirth = LocalDate.parse(dateOfBirthStr, DATE_FORMATTER);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        Validator.inputAddress();
        this.address = address;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        Validator.inputEmployeeStatus();
        this.status = status;
    }

    public Department getDepartment() {

        return department;
    }

    public void setDepartment(Department department) {
        Validator.createDepartment();
        this.department = department;
    }

    //chyển ngày sinh thành chuỗi
    public String getFormattedDateOfBirth() {
        return dateOfBirth != null ? dateOfBirth.format(DATE_FORMATTER) : null;
    }
}
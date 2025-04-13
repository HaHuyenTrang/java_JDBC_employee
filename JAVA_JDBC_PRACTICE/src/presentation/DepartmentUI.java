//package presentation;
//
//import business.model.Department;
//import business.service.department.DepartmentService;
//import business.service.department.DepartmentServiceImpl;
//import business.validate.Validator;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class DepartmentUI {
//    private final DepartmentService departmentService = new DepartmentServiceImpl();
//    private final Scanner scanner = new Scanner(System.in);
//    private static final int PAGE_SIZE = 5;
//
//    public void menu() {
//        int choice;
//        do {
//            System.out.println("\n===== QUẢN LÝ PHÒNG BAN =====");
//            System.out.println("1. Thêm phòng ban");
//            System.out.println("2. Hiển thị danh sách phòng ban (phân trang)");
//            System.out.println("3. Cập nhật phòng ban");
//            System.out.println("4. Xóa phòng ban (chỉ khi không có nhân viên)");
//            System.out.println("5. Tìm kiếm phòng ban theo tên");
//            System.out.println("0. Thoát");
//            System.out.print("Chọn chức năng: ");
//            choice = Integer.parseInt(scanner.nextLine());
//
//            switch (choice) {
//                case 1:
//                    addDepartment();
//                    break;
//                case 2:
//                    showAllDepartmentsPaginated();
//                    break;
//                case 3:
//                    updateDepartment();
//                    break;
//                case 4:
//                    deleteDepartment();
//                    break;
//                case 5:
//                    searchDepartmentByName();
//                    break;
//                case 0:
//                    System.out.println("Thoát chương trình.");
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ!");
//            }
//        } while (choice != 0);
//    }
//
//    private void addDepartment() {
//        System.out.println("\n=== THÊM PHÒNG BAN MỚI ===");
//
//        // Sử dụng Validator để lấy thông tin phòng ban
//        String name = Validator.inputDepartmentName();   // Lấy tên phòng ban
//        String description = Validator.inputDepartmentDescription();  // Lấy mô tả phòng ban
//        Department.DepartmentStatus status = Validator.inputDepartmentStatus();  // Lấy trạng thái phòng ban
//
//        // Tạo đối tượng phòng ban và lưu vào cơ sở dữ liệu
//        Department dept = new Department();
//        dept.setDepartmentName(name);
//        dept.setDescription(description);
//        dept.setStatus(status);
//
//        boolean success = departmentService.save(dept);
//        System.out.println(success ? "Thêm phòng ban thành công!" : "Thêm phòng ban thất bại!");
//    }
//
//
//    private void showAllDepartmentsPaginated() {
//        List<Department> departments = departmentService.findAll();
//        if (departments.isEmpty()) {
//            System.out.println("Chưa có phòng ban nào.");
//            return;
//        }
//
//        int totalPages = (int) Math.ceil((double) departments.size() / PAGE_SIZE);
//        int page = 1;
//
//        while (true) {
//            int start = (page - 1) * PAGE_SIZE;
//            int end = Math.min(start + PAGE_SIZE, departments.size());
//
//            System.out.printf("\n--- Trang %d/%d ---\n", page, totalPages);
//            for (int i = start; i < end; i++) {
//                Department d = departments.get(i);
//                System.out.printf("ID: %d | Tên: %s | Mô tả: %s | Trạng thái: %s%n",
//                        d.getDepartmentId(), d.getDepartmentName(), d.getDescription(), d.getStatus());
//            }
//
//            System.out.println("1: tiếp, 0: lùi, 00: thoát");
//            String cmd = scanner.nextLine().toLowerCase();
//            if (cmd.equals("1") && page < totalPages) page++;
//            else if (cmd.equals("0") && page > 1) page--;
//            else if (cmd.equals("00")) break;
//        }
//    }
//
//    private void updateDepartment() {
//        System.out.print("Nhập ID phòng ban cần cập nhật: ");
//        int id = Integer.parseInt(scanner.nextLine());
//
//        Department dept = departmentService.findById(id);
//        if (dept == null) {
//            System.out.println("Không tìm thấy phòng ban.");
//            return;
//        }
//
//        // Sử dụng Validator để lấy thông tin phòng ban mới
//        dept.setDepartmentName(Validator.inputDepartmentName());
//        dept.setDescription(Validator.inputDepartmentDescription());
//        dept.setStatus(Validator.inputDepartmentStatus());
//
//        boolean updated = departmentService.update(dept);
//        System.out.println(updated ? "Cập nhật thành công!" : "Cập nhật thất bại!");
//    }
//
//    private void deleteDepartment() {
//        System.out.print("Nhập ID phòng ban cần xóa: ");
//        int id = Integer.parseInt(scanner.nextLine());
//        Department dept = departmentService.findById(id);
//        if (dept == null) {
//            System.out.println("Không tìm thấy phòng ban.");
//            return;
//        }
//        if (departmentService.hasDepartmentEmployees(dept)) {
//            System.out.println("Không thể xóa vì phòng ban vẫn còn nhân viên!");
//            return;
//        }
//        boolean deleted = departmentService.delete(dept);
//        System.out.println(deleted ? "Xóa thành công!" : "Xóa thất bại!");
//    }
//
//    private void searchDepartmentByName() {
//        System.out.print("Nhập tên cần tìm: ");
//        String keyword = scanner.nextLine();
//        List<Department> results = departmentService.searchByName(keyword);
//        if (results.isEmpty()) {
//            System.out.println("Không tìm thấy phòng ban nào.");
//        } else {
//            for (Department d : results) {
//                System.out.printf("ID: %d | Tên: %s | Mô tả: %s | Trạng thái: %s%n",
//                        d.getDepartmentId(), d.getDepartmentName(), d.getDescription(), d.getStatus());
//            }
//        }
//    }
//}

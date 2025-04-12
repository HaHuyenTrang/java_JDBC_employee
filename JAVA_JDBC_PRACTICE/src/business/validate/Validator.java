    package business.validate;

    import business.model.Account;
    import business.model.Department;
    import business.model.Employee;

    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.time.format.DateTimeParseException;
    import java.util.Scanner;
    import java.util.regex.Pattern;

    public class Validator {
        private static final Scanner scanner = new Scanner(System.in);
        private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //biểu thức kiểm tra email
        private static final Pattern EMAIL_PATTERN =
                Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        //biểu thức kiểm tra số đt
        private static final Pattern PHONE_PATTERN =
                Pattern.compile("^(0|\\+84)(3|5|7|8|9)[0-9]{8}$");
        //biểu thức kiểm tra id
        private static final Pattern EMPLOYEE_ID_PATTERN =
                Pattern.compile("^E[0-9A-Za-z]{4}$");

        // validate tên  phòng ban
        public static String inputDepartmentName() {
            String input;
            do {
                System.out.print("Nhập tên phòng ban (10-100 ký tự): ");
                input = scanner.nextLine().trim();

                if (input.isEmpty()) {  // Không cần kiểm tra null nữa
                    System.out.println("Lỗi: Tên phòng ban không được để trống");
                    continue;
                }

                if (input.length() < 10 || input.length() > 100) {
                    System.out.println("Lỗi: Tên phòng ban phải có từ 10-100 ký tự");
                    continue;
                }

                break;
            } while (true);

            return input;
        }


        // validate mô tả
        public static String inputDepartmentDescription() {
            String input;
            do {
                System.out.print("Nhập mô tả phòng ban (tối đa 255 ký tự): ");
                input = scanner.nextLine().trim();

                if (input != null && input.length() > 255) {
                    System.out.println("Lỗi: Mô tả phòng ban không được vượt quá 255 ký tự");
                    continue;
                }

                break;
            } while (true);

            return input;
        }

        // validate trangj thái
        public static Department.DepartmentStatus inputDepartmentStatus() {
            int choice;
            do {
                System.out.println("Chọn trạng thái phòng ban:");
                System.out.println("1. Hoạt động");
                System.out.println("2. Không hoạt động");
                System.out.print("Lựa chọn của bạn: ");

                try {
                    choice = Integer.parseInt(scanner.nextLine().trim());  // Lấy lựa chọn
                    if (choice != 1 && choice != 2) {
                        System.out.println("Lỗi: Vui lòng nhập 1 hoặc 2");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ");
                }
            } while (true);

            return choice == 1 ? Department.DepartmentStatus.ACTIVE : Department.DepartmentStatus.INACTIVE;
        }

        // validate id nhân viên
        public static String inputEmployeeId() {
            String input;
            do {
                System.out.print("Nhập mã nhân viên (bắt đầu bằng E, 5 ký tự): ");
                input = scanner.nextLine().trim();

                if (input == null || !EMPLOYEE_ID_PATTERN.matcher(input).matches()) {
                    System.out.println("Lỗi: Mã nhân viên phải bắt đầu bằng 'E' và có đúng 5 ký tự");
                    continue;
                }

                break;
            } while (true);

            return input;
        }

        // vilidate tên nv
        public static String inputEmployeeName() {
            String input;
            do {
                System.out.print("Nhập tên nhân viên (15-150 ký tự): ");
                input = scanner.nextLine().trim();

                if (input == null || input.isEmpty()) {
                    System.out.println("Lỗi: Tên nhân viên không được để trống");
                    continue;
                }

                if (input.length() < 15 || input.length() > 150) {
                    System.out.println("Lỗi: Tên nhân viên phải có từ 15-150 ký tự");
                    continue;
                }

                break;
            } while (true);

            return input;
        }

        // validate email
        public static String inputEmail() {
            String input;
            do {
                System.out.print("Nhập email: ");
                input = scanner.nextLine().trim();

                if (input == null || !EMAIL_PATTERN.matcher(input).matches()) {
                    System.out.println("Lỗi: Email không hợp lệ");
                    continue;
                }

                break;
            } while (true);

            return input;
        }

        // validate sdt
        public static String inputPhoneNumber() {
            String input;
            do {
                System.out.print("Nhập số điện thoại (định dạng VN): ");
                input = scanner.nextLine().trim();

                if (input == null || !PHONE_PATTERN.matcher(input).matches()) {
                    System.out.println("Lỗi: Số điện thoại không đúng định dạng số điện thoại di động Việt Nam");
                    continue;
                }

                break;
            } while (true);

            return input;
        }

        // validate giới tính
        public static Employee.Gender inputGender() {
            int choice;
            do {
                System.out.println("Chọn giới tính:");
                System.out.println("1. Nam");
                System.out.println("2. Nữ");
                System.out.println("3. Khác");
                System.out.print("Lựa chọn của bạn: ");

                try {
                    choice = Integer.parseInt(scanner.nextLine().trim());
                    if (choice < 1 || choice > 3) {
                        System.out.println("Lỗi: Vui lòng nhập số từ 1 đến 3");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi: Vui lòng nhập một số nguyên");
                }
            } while (true);

            switch (choice) {
                case 1: return Employee.Gender.MALE;
                case 2: return Employee.Gender.FEMALE;
                default: return Employee.Gender.OTHER;
            }
        }

        // validate bậc lương
        public static int inputSalaryGrade() {
            int input;
            do {
                System.out.print("Nhập bậc lương (số nguyên > 0): ");

                try {
                    input = Integer.parseInt(scanner.nextLine().trim());
                    if (input <= 0) {
                        System.out.println("Lỗi: Bậc lương phải lớn hơn 0");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi: Vui lòng nhập một số nguyên");
                }
            } while (true);

            return input;
        }

        // validate lương
        public static double inputSalary() {
            double input;
            do {
                System.out.print("Nhập lương (số thực > 0): ");

                try {
                    input = Double.parseDouble(scanner.nextLine().trim());
                    if (input <= 0) {
                        System.out.println("Lỗi: Lương phải lớn hơn 0");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi: Vui lòng nhập một số thực");
                }
            } while (true);

            return input;
        }

        // validate ngày sinh
        public static LocalDate inputDateOfBirth() {
            LocalDate dateOfBirth;
            do {
                System.out.print("Nhập ngày sinh (định dạng dd/MM/yyyy): ");
                String input = scanner.nextLine().trim();

                try {
                    dateOfBirth = LocalDate.parse(input, DATE_FORMATTER);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Lỗi: Định dạng ngày không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy");
                }
            } while (true);

            return dateOfBirth;
        }

        // validate địa chỉ
        public static String inputAddress() {
            String input;
            do {
                System.out.print("Nhập địa chỉ: ");
                input = scanner.nextLine().trim();

                if (input == null || input.isEmpty()) {
                    System.out.println("Lỗi: Địa chỉ không được để trống");
                    continue;
                }

                break;
            } while (true);

            return input;
        }

        // trạng thái nv
        public static Employee.EmployeeStatus inputEmployeeStatus() {
            int choice;
            do {
                System.out.println("Chọn trạng thái nhân viên:");
                System.out.println("1. Đang làm việc (ACTIVE)");
                System.out.println("2. Nghỉ việc (INACTIVE)");
                System.out.println("3. Nghỉ phép (ONLEAVE)");
                System.out.println("4. Nghỉ chế độ (POLICYLEAVE)");
                System.out.print("Lựa chọn của bạn: ");

                try {
                    choice = Integer.parseInt(scanner.nextLine().trim());
                    if (choice < 1 || choice > 4) {
                        System.out.println("Lỗi: Vui lòng nhập số từ 1 đến 4");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi: Vui lòng nhập một số nguyên");
                }
            } while (true);

            switch (choice) {
                case 1: return Employee.EmployeeStatus.ACTIVE;
                case 2: return Employee.EmployeeStatus.INACTIVE;
                case 3: return Employee.EmployeeStatus.ONLEAVE;
                default: return Employee.EmployeeStatus.POLICYLEAVE;
            }
        }

        // tên đăng nhâoj
        public static String inputUsername() {
            String input;
            do {
                System.out.print("Nhập tên đăng nhập: ");
                input = scanner.nextLine().trim();

                if (input == null || input.isEmpty()) {
                    System.out.println("Lỗi: Tên đăng nhập không được để trống");
                    continue;
                }

                break;
            } while (true);

            return input;
        }

        //mật khẩu
        public static String inputPassword() {
            String input;
            do {
                System.out.print("Nhập mật khẩu: ");
                input = scanner.nextLine().trim();

                if (input == null || input.isEmpty()) {
                    System.out.println("Lỗi: Mật khẩu không được để trống");
                    continue;
                }

                break;
            } while (true);

            return input;
        }

        //validate số nguyên
        public static int inputInteger(String prompt) {
            int input;
            do {
                System.out.print(prompt);

                try {
                    input = Integer.parseInt(scanner.nextLine().trim());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi: Vui lòng nhập một số nguyên");
                }
            } while (true);

            return input;
        }


        public static Department createDepartment() {
            System.out.println("\n=== THÊM PHÒNG BAN MỚI ===");
            Department department = new Department();
            department.setDepartmentId(0);
            // Nhập tên phòng ban với kiểm tra
            department.setDepartmentName(inputDepartmentName());

            // Nhập mô tả phòng ban với kiểm tra
            department.setDescription(inputDepartmentDescription());

            // Chọn trạng thái phòng ban với kiểm tra
            department.setStatus(inputDepartmentStatus());

            System.out.println("Phòng ban đã được tạo thành công!");
            return department;
        }


        public static Employee createEmployee() {
            System.out.println("\n=== THÊM NHÂN VIÊN MỚI ===");
            Employee employee = new Employee();
            employee.setEmployeeId(inputEmployeeId());
            employee.setEmployeeName(inputEmployeeName());
            employee.setEmail(inputEmail());
            employee.setPhoneNumber(inputPhoneNumber());
            employee.setGender(inputGender());
            employee.setSalaryGrade(inputSalaryGrade());
            employee.setSalary(inputSalary());
            employee.setDateOfBirth(inputDateOfBirth());
            employee.setAddress(inputAddress());
            employee.setStatus(inputEmployeeStatus());

            System.out.println("Nhân viên đã được tạo thành công!");
            return employee;
        }


        public static Account createAccount() {
            System.out.println("\n=== TẠO TÀI KHOẢN MỚI ===");
            Account account = new Account();
            account.setUsername(inputUsername());
            account.setPassword(inputPassword());
            account.setStatus(Account.AccountStatus.ACTIVE);

            System.out.println("Tài khoản đã được tạo thành công!");
            return account;
        }
    }
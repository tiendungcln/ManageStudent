import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentManager {

    Scanner sc = new Scanner(System.in);
    List<Student> students = new ArrayList<>();

    public void findStudentById(){

        while (true){

            try {

                System.out.print("Nhập ID học sinh muốn xem thông tin: ");
                int id = sc.nextInt();
                sc.nextLine();

                if (id <= 0){
                    System.out.print("ID phải lớn hơn 0! Cần nhập lại ID: ");
                    continue;
                }

                boolean isFound = false;

                for (Student s : students){

                    if (id == s.getId()){

                        isFound = true;
                        System.out.println("Họ và tên: " + s.getName() + " - " +
                                "Lớp: " + s.getClassName() + " - " +
                                "GPA: " + s.getGpa());
                        break;

                    }

                }

                if (!isFound){

                    System.out.println("Không tìm thấy ID");

                }

                break;

            } catch (InputMismatchException e) {
                System.out.println("Lỗi: Vui lòng nhập ID là một số nguyên!");
                sc.nextLine(); // Xóa dữ liệu nhập sai khỏi bộ đệm Scanner
            }

        }

    }

    public void showAllStudent(){

        if (students.isEmpty()){
            System.out.println("Danh sách học sinh trống!");
            return;
        }

        System.out.println("===== DANH SÁCH HỌC SINH =====");

        for (Student s : students){

            System.out.println("ID: " + s.getId() + " - " +
                    "Họ và tên: " + s.getName() + " - " +
                    "Lớp: " + s.getClassName() + " - " +
                    "GPA: " + s.getGpa());

        }

    }

    public void addStudent(){

        int id;
        String name;
        String className;
        double gpa;

        while (true){

            try {

                System.out.print("Nhập ID: ");
                id = sc.nextInt();
                sc.nextLine();

                if (id <= 0){
                    System.out.println("ID phải lớn hơn 0");
                    continue;
                }

                boolean isDuplicate = false;

                for (Student s : students){
                    if (s.getId() == id){
                        isDuplicate = true;
                        break;
                    }
                }

                if (isDuplicate){
                    System.out.println("ID đã trùng!");
                    continue;
                }

                break;

            } catch (InputMismatchException e) {
                System.out.println("ID phải là số!");
                sc.nextLine(); // xóa input sai
            }

        }

        while (true){

            System.out.print("Nhập họ và tên: ");
            name = sc.nextLine();

            // Check name
            if (name.trim().isEmpty()) {
                // trim() dùng để: xoá khoảng trắng thừa, tránh user nhập "rỗng giả", làm validate chính xác hơn
                System.out.println("Họ và tên không được để trống!");
                continue;
            }

            break;

        }

        while (true){

            System.out.print("Nhập lớp: ");
            className = sc.nextLine();

            // Check clasName
            if (className.trim().isEmpty()){
                System.out.println("Lớp không được để trống!");
                continue;
            }

            break;

        }

        while (true){

            try {

                System.out.print("Nhập điểm trung bình: ");
                gpa = sc.nextDouble();

                if (gpa < 0 || gpa > 10){
                    System.out.println("GPA phải là 0 - 10");
                    continue;
                }

                break;

            } catch (InputMismatchException e) {
                System.out.println("GPA phải là số!");
                sc.nextLine(); // xóa input sai
            }

        }

        Student student = new Student(id, name, className, gpa);
        students.add(student);

        System.out.println("Thêm thông tin học sinh thành công!");

    }

    public void updateStudent(){

        int id;
        String name;
        String className;
        double gpa;

        boolean isFound = false;

        try {

            System.out.print("Nhập ID cần sửa thông tin học sinh: ");
            id = sc.nextInt();
            sc.nextLine();

            for (Student s : students){

                if (id == s.getId()){

                    isFound = true;

                    while (true){

                        System.out.print("Họ và tên: ");
                        name = sc.nextLine();

                        if (name.trim().isEmpty()){
                            System.out.println("Họ và tên không được để trống");
                            continue;
                        }

                        break;

                    }

                    while (true){

                        System.out.print("Lớp: ");
                        className = sc.nextLine();

                        if (className.trim().isEmpty()){
                            System.out.println("Lớp không được để trống");
                            continue;
                        }

                        break;

                    }

                    while (true){

                        try {

                            System.out.print("GPA: ");
                            gpa = sc.nextDouble();
                            sc.nextLine();

                            if (gpa < 0 || gpa > 10){
                                System.out.println("GPA 0 - 10");
                                continue;
                            }

                            break;

                        } catch (InputMismatchException e){

                            System.out.println("Vui lòng nhập số!");
                            sc.nextLine();

                        }

                    }

                    s.setName(name);
                    s.setClassName(className);
                    s.setGpa(gpa);

                    System.out.println("Sửa thông tin thành công");
                    break;

                }

            }

            if (!isFound){
                System.out.println("ID không tồn tại!");
            }

        } catch (InputMismatchException e){

            System.out.println("Lỗi: Vui lòng nhập ID là 1 số nguyên");
            sc.nextLine();

        }

    }

    public void deleteStudent(){

    }

}

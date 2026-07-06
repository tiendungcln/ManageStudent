import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentManager {

    Scanner sc = new Scanner(System.in);
    List<Student> students = new ArrayList<>();

    public void findStudentById(){

        try {

            System.out.print("Nhập ID học sinh muốn xem thông tin: ");
            int id = sc.nextInt();
            sc.nextLine();

            // check ID phải lớn hơn 0
            if (id <= 0){
                System.out.print("ID phải lớn hơn 0! Cần nhập lại ID: ");
                sc.nextLine();
            }

            boolean isFound = false;

            for (int i = 0; i < students.size(); i++){

                if (id == students.get(i).getId()){
                    // students.get(i) trả về một đối tượng Student. Ví dụ: Student s1 = new Student(1, "An", "CNTT1");
                    // -> Muốn lấy ID của đối tượng đó thì dùng: students.get(i).getId(). Ví dụ: s1.getId() = 1 (ID của s1)

                    isFound = true;
                    System.out.println("Họ và tên: " + students.get(i).getName() + " - " + "Lớp: " + students.get(i).getClassName());
                    break;

                }

            }

            if (!isFound){

                System.out.println("Không tìm thấy ID");

            }


        } catch (InputMismatchException e) {
            System.out.println("Lỗi: Vui lòng nhập ID là một số nguyên!");
            sc.nextLine(); // Xóa dữ liệu nhập sai khỏi bộ đệm Scanner
        }

    }

    public void showAllStudent(){

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

    }

    public void deleteStudent(){

    }

}

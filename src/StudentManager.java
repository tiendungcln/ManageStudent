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
                System.out.println("ID phải lớn hơn 0");
            }

            boolean isFound = false;

            for (int i = 0; i < listId.size(); i++){

                if (id == listId.get(i)){
                    // listId.get(i) là giá trị ID được lưu ở vị trí i

                    isFound = true;
                    System.out.println("Họ và tên: " + listName.get(i) + " - " + "Lớp: " + listClassName.get(i));
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

        try {

            System.out.print("Nhập ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            // Check ID
            if (id <= 0){
                System.out.println("ID phải lớn hơn 0");
            }

            System.out.print("Nhập họ và tên: ");
            String name = sc.nextLine();

            // Check name
            if (name.isEmpty()){
                System.out.println("Họ và tên không được để trống!");
            }

            System.out.print("Nhập lớp: ");
            String className = sc.nextLine();

            // Check clasName
            if (className.isEmpty()){
                System.out.println("Lớp không được để trống!");
            }

            listId.add(id);
            listName.add(name);
            listClassName.add(className);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void updateStudent(){

    }

    public void deleteStudent(){

    }

}

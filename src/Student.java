import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Student {

    Scanner sc = new Scanner(System.in);
    List<Integer> listId = new ArrayList<>();
    List<String> listName = new ArrayList<>();
    List<String> listClassName = new ArrayList<>();

    public void findStudentById(){

        try {

            System.out.println("Nhập ID học sinh muốn xem thông tin: ");
            int id = sc.nextInt();
            sc.nextLine();

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

}

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){

        StudentManager sm = new StudentManager();

        int choice;

        do {

            System.out.println("1. Xem thông tin 1 học sinh");
            System.out.println("2. Xem thông tin tất cả học sinh");
            System.out.println("3. Thêm thông tin học sinh");
            System.out.println("4. Sửa thông tin học sinh");
            System.out.println("5. Xoá thông tin học sinh");
            System.out.println("0. Thoát chương trình");

            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    sm.findStudentById();
                    break;
                case 2:
                    sm.showAllStudent();
                    break;
                case 3:
                    sm.addStudent();
                    break;
                case 4:
                    sm.updateStudent();
                    break;
                case 5:
                    sm.deleteStudent();
                    break;
                default:
                    System.out.println("Thoát chương trình");
            }

        } while (choice != 0); // Nếu đúng đk nó sẽ quay lại chạy khối do, còn khác đk nó sẽ thoát vòng lặp

        sc.close();

    }

}

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManager {

    Scanner sc = new Scanner(System.in);

    public void findStudentById(){

        try {

            System.out.print("Nhập ID học sinh muốn kiểm tra thông tin: ");
            int id = sc.nextInt();
            sc.nextLine();

            try (Connection connection = DBConnection.getConnection()){

                // ===== Kiểm tra ID =====

                String checkSql = "SELECT id FROM student WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(checkSql)){

                    preparedStatement.setInt(1, id);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (!resultSet.next()){
                        System.out.println("ID không tồn tại!");
                        return;
                    }

                }

                // ===== Lấy thông tin =====

                String sql = "SELECT id, name, className, gpa FROM student WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){

                    preparedStatement.setInt(1, id);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()){

                        int studentId = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String className = resultSet.getString("className");
                        double gpa = resultSet.getDouble("gpa");

                        Student student = new Student(studentId, name, className, gpa);

                        System.out.println(student);

                    }

                }

            } catch (SQLException | IOException e){

                System.out.println("Lấy thông tin học sinh thất bại!");
                e.printStackTrace();

            }

        } catch (InputMismatchException e) {

            System.out.println("Lỗi: Vui lòng nhập ID là một số nguyên!");
            sc.nextLine(); // Xóa dữ liệu nhập sai khỏi bộ đệm Scanner

        }

    }

    public void showAllStudent(){

        boolean dataIsFound = false;

        System.out.println("===== DANH SÁCH HỌC SINH =====");

        String sql = "SELECT id, name, className, gpa FROM student";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                dataIsFound = true;

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String className = resultSet.getString("className");
                double gpa = resultSet.getDouble("gpa");

                Student student = new Student(id, name, className, gpa);

                System.out.println(student);

            }

            if (!dataIsFound){
                System.out.println("Danh sách học sinh trống!");
            }

        } catch (SQLException | IOException e){

            System.out.println("Lấy danh sách học sinh thất bại!");
            e.printStackTrace();

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

                // ===== Kiểm tra ID =====

                String checkSql = "SELECT id FROM student WHERE id = ?";

                try (
                        Connection connection = DBConnection.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(checkSql)
                ){

                    preparedStatement.setInt(1, id);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()){
                        System.out.println("ID đã trùng!");
                        continue;
                    }

                } catch (SQLException | IOException e){

                    System.out.println("Nhập ID thất bại!");
                    e.printStackTrace();

                }

                break;

            } catch (InputMismatchException e) {

                System.out.println("ID phải là số!");
                sc.nextLine(); // Xóa dữ liệu nhập sai khỏi bộ đệm Scanner

            }

        }

        while (true){

            System.out.print("Nhập họ và tên: ");
            name = sc.nextLine();

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
                sc.nextLine(); // Xóa dữ liệu nhập sai khỏi bộ đệm Scanner
            }

        }

        Student student = new Student(id, name, className, gpa);

        String sql = "INSERT INTO student(id, name, className, gpa) VALUES (?, ?, ?, ?)";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getClassName());
            preparedStatement.setDouble(4, student.getGpa());

            int rows = preparedStatement.executeUpdate();

            if (rows > 0){
                System.out.println("Thêm thông tin học sinh thành công");
            }else{
                System.out.println("Thêm thông tin học sinh thất bại!");
            }

        } catch (SQLException | IOException e){

            System.out.println("Thêm thông tin học sinh thất bại!");
            e.printStackTrace();

        }

    }

    public void updateStudent() {

        int id;
        String name;
        String className;
        double gpa;

        try {

            System.out.print("Nhập ID cần sửa: ");
            id = sc.nextInt();
            sc.nextLine();

            try (Connection connection = DBConnection.getConnection()) {

                // ===== Kiểm tra ID =====

                String checkSql = "SELECT id FROM student WHERE id = ?";

                try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

                    checkStatement.setInt(1, id);

                    ResultSet resultSet = checkStatement.executeQuery();

                    if (!resultSet.next()) {
                        System.out.println("ID không tồn tại!");
                        return;
                    }

                }

                while (true) {
                    System.out.print("Họ và tên: ");
                    name = sc.nextLine();

                    if (name.trim().isEmpty()) {
                        System.out.println("Họ và tên không được để trống!");
                        continue;
                    }
                    break;
                }

                while (true) {
                    System.out.print("Lớp: ");
                    className = sc.nextLine();

                    if (className.trim().isEmpty()) {
                        System.out.println("Lớp không được để trống!");
                        continue;
                    }
                    break;
                }

                while (true) {

                    try {

                        System.out.print("GPA: ");
                        gpa = sc.nextDouble();
                        sc.nextLine();

                        if (gpa < 0 || gpa > 10) {
                            System.out.println("GPA phải từ 0 đến 10");
                            continue;
                        }

                        break;

                    } catch (InputMismatchException e) {
                        System.out.println("GPA phải là số!");
                        sc.nextLine(); // Xóa dữ liệu nhập sai khỏi bộ đệm Scanner
                    }
                }

                // ===== UPDATE =====

                String updateSql = "UPDATE student SET name = ?, className = ?, gpa = ? WHERE id = ?";

                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {

                    updateStatement.setString(1, name);
                    updateStatement.setString(2, className);
                    updateStatement.setDouble(3, gpa);
                    updateStatement.setInt(4, id);

                    int rows = updateStatement.executeUpdate();

                    if (rows > 0) {
                        System.out.println("Cập nhật thông tin học sinh thành công!");
                    } else {
                        System.out.println("Cập nhật thất bại!");
                    }
                }

            } catch (SQLException | IOException e) {

                System.out.println("Lỗi khi cập nhật học sinh!");
                e.printStackTrace();

            }

        } catch (InputMismatchException e) {

            System.out.println("ID phải là số nguyên!");
            sc.nextLine(); // Xóa dữ liệu nhập sai khỏi bộ đệm Scanner

        }
    }

    public void deleteStudent(){

        try {

            System.out.print("Nhập ID cần xoá thông tin học sinh: ");
            int id = sc.nextInt();
            sc.nextLine();

            try (Connection connection = DBConnection.getConnection()) {

                // ===== Kiểm tra ID =====

                String checkSql = "SELECT id FROM student WHERE id = ?";

                try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {

                    checkStatement.setInt(1, id);

                    ResultSet resultSet = checkStatement.executeQuery();

                    if (!resultSet.next()) {
                        System.out.println("ID không tồn tại!");
                        return;
                    }

                }

                String deleteSql = "DELETE FROM student WHERE id = ?";

                try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)){

                    deleteStatement.setInt(1, id);

                    int rows = deleteStatement.executeUpdate();

                    if (rows > 0) {
                        System.out.println("Xóa thông tin học sinh thành công!");
                    } else {
                        System.out.println("Xóa thông tin học sinh thất bại!");
                    }

                }

            } catch (SQLException | IOException e){
                System.out.println("Lỗi xoá thông tin học sinh!");
                e.printStackTrace();

            }

        } catch (InputMismatchException e) {

            System.out.println("ID phải là số nguyên!");
            sc.nextLine(); // Xóa dữ liệu nhập sai khỏi bộ đệm Scanner

        }

    }

}

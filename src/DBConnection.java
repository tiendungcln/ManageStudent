import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() {

        try {
            // Đọc file cấu hình
            Properties properties = new Properties();
            properties.load(new FileInputStream("database.properties"));

            // Lấy thông tin kết nối
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            // Kết nối database
            return DriverManager.getConnection(url, user, password);

        } catch (IOException e) {
            System.out.println("Không tìm thấy file database.properties");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại!");
            e.printStackTrace();
        }

        return null;
    }
}
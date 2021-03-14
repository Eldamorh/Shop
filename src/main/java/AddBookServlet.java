import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;


@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        String dbUrl = "jdbc:sqlite:C:\\Sqlite\\Shop.db";

        try (Connection connection = DriverManager.getConnection(dbUrl)) {
            ShopService.addBook(parameterMap.get("nameOfBook")[0],
                    parameterMap.get("author")[0],
                    Integer.valueOf(parameterMap.get("yearOfWriting")[0]),
                    Integer.valueOf(parameterMap.get("price")[0]),
                    Integer.valueOf(parameterMap.get("amount")[0]),
                    connection);
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


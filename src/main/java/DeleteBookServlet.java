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

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        String dbUrl = "jdbc:sqlite:C:\\Sqlite\\Shop.db";
        try (Connection connection = DriverManager.getConnection(dbUrl)) {

            if(parameterMap.get("nameOfColumn")[0].equals("all")){
                ShopService.deleteAllBooks(connection);
            }else{
                ShopService.deleteBook(parameterMap.get("nameOfColumn")[0],parameterMap.get("value")[0],connection);
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

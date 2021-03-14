import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/selectBook")
public class SelectBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        String dbUrl = "jdbc:sqlite:C:\\Sqlite\\Shop.db";
        List<List<String>> lists = null;
        try (Connection connection = DriverManager.getConnection(dbUrl)) {
            if(parameterMap.get("nameOfColumn")[0].equals("all")){
                lists = ShopService.selectAllBooks(connection);
            }else{
                lists = ShopService.selectBook(parameterMap.get("nameOfColumn")[0],parameterMap.get("value")[0],connection);
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(List<String> list : lists){
            for(String s : list){
                resp.getWriter().print(s + "|");
            }
            resp.getWriter().println();
        }
    }
}

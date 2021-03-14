import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopService {


    static void addBook(String nameOfBook, String author, Integer yearOfWriting, Integer price, Integer amount, Connection connection) throws SQLException {
        String sql = "INSERT INTO books VALUES('"
                + UUID.randomUUID().toString() + "', '"
                + nameOfBook + "', '"
                + author + "',"
                + yearOfWriting + ","
                + price + ","
                + amount
                + ");";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    static void deleteAllBooks(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM books;");
        }
    }


    static void deleteBook(String nameOfColumn, String value, Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            if (nameOfColumn.equals("nameOfBook") || nameOfColumn.equals("author")) {
                statement.execute("DELETE FROM books WHERE " + nameOfColumn + " = '" + value + "';");
            } else {
                statement.execute("DELETE FROM books WHERE " + nameOfColumn + " = " + value + ";");
            }

        }
    }

    static void updateBook(String nameOfColumn, String newValue, Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {

            if (nameOfColumn.equals("nameOfBook") || nameOfColumn.equals("author")) {
                statement.execute("UPDATE books SET " + nameOfColumn + " = '" + newValue + "';");
            } else {
                statement.execute("UPDATE books SET " + nameOfColumn + " = " + newValue + ";");
            }


        }
    }

    static List<List<String>> selectBook(String nameOfColumn, String value, Connection connection) throws SQLException {
        List<List<String>> books = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            if (!nameOfColumn.equals("yearOfWriting")) {
                resultSet = statement.executeQuery("SELECT * FROM books WHERE "
                        + nameOfColumn + " = '" + value + "';");
            } else {
                resultSet = statement.executeQuery("SELECT * FROM books WHERE" +
                        "yearOfWriting = " + value + ";");
            }
            while (resultSet.next()) {
                List<String> book = new ArrayList<>();
                    book.add(resultSet.getString("nameOfBook"));
                    book.add(resultSet.getString("author"));
                    book.add(String.valueOf(resultSet.getString("yearOfWriting")));
                    book.add(String.valueOf(resultSet.getString("price")));
                    book.add(String.valueOf(resultSet.getString("amount")));
                books.add(book);
            }
        }
        return books;
    }

    static List<List<String>> selectAllBooks(Connection connection) throws SQLException {
        List<List<String>> books = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                List<String> book = new ArrayList<>();
                book.add(resultSet.getString("nameOfBook"));
                book.add(resultSet.getString("author"));
                book.add(String.valueOf(resultSet.getString("yearOfWriting")));
                book.add(String.valueOf(resultSet.getString("price")));
                book.add(String.valueOf(resultSet.getString("amount")));
                books.add(book);
            }
        }
        return books;
    }
}

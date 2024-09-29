import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// neaizmirsti izdzēst paroli pirms rādi savu ekrānu
public class DatabaseManager {
    private static final String DB = "jdbc:mysql://localhost:3306/Izdevumi";
    private static final String USER = "root";
    private static final String PASS = "";

    // savienojums ar db
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB, USER, PASS);
    }

    // izdevumu pievienošana
    public void addExpense(Expense expense) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO expenses (datums, summa, apraksts) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, expense.getDate());
            pstmt.setDouble(2, expense.getAmount());
            pstmt.setString(3, expense.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // izdevumi
    public List<Expense> getExpenses(String orderBy) {
        List<Expense> expenses = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM expenses";

            if ("datums".equals(orderBy)) {
                sql += " ORDER BY datums DESC";
            } else if ("summa".equals(orderBy)) {
                sql += " ORDER BY summa DESC";
            }

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                expenses.add(new Expense(
                        rs.getString("datums"),
                        rs.getDouble("summa"),
                        rs.getString("apraksts")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    public double getTotalExpenses() {
        double total = 0;
        try (Connection conn = getConnection()) {
            String sql = "SELECT SUM(summa) AS total FROM expenses";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}
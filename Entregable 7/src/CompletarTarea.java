import java.sql.*;
import java.util.Scanner;

public class CompletarTarea {
    static final String JDBC_Driver = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/to-do app";

    static final String User = "root";
    static final String Pass = "";

    static Statement stmt;
    static Connection conn;

    static Scanner sc = new Scanner(System.in);

    public static void CompletarTareas() throws SQLException {
        try {
            Class.forName(JDBC_Driver);
            System.out.println("Introduce el id de la tarea a completar");
            int ID_tarea = sc.nextInt();
            conn = DriverManager.getConnection(DB_URL, User, Pass);
            stmt = conn.createStatement();
            try {
                String consulta = "update tarea set Estado=1 where ID_tarea=? ;";

                PreparedStatement preparedStatement = conn.prepareStatement(consulta);
                preparedStatement.setInt(1, ID_tarea);
                preparedStatement.executeQuery();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("Tarea Completada con Éxito");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void VerTareas() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, User, Pass);
        stmt = conn.createStatement();
        String sql = "SELECT * FROM tarea WHERE Estado=1";
        PreparedStatement Stame = conn.prepareStatement(sql);
        ResultSet rs = Stame.executeQuery();
        while (rs.next()) {
            System.out.println("Tarea: " + rs.getString("Titulo"));
            System.out.println("Fecha: " + rs.getDate("Fecha"));
            System.out.println("ID: " + rs.getInt("ID_tarea"));
            System.out.println();
        }
        rs.close();
        stmt.close();
    }
}


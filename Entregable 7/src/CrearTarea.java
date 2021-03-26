import java.sql.*;
import java.text.*;
import java.util.Scanner;
import java.util.Date;

public class CrearTarea {
    static final String JDBC_Driver = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/to-do app";
    static final String User = "root";
    static final String Pass = "";

    static Connection conn;
    static Statement stmt;

    public static void CrearTareas() throws SQLException {

        Date date = new Date();
        DateFormat fechanueva = new SimpleDateFormat("yyyy-MM-dd");

        Scanner sc = new Scanner(System.in);
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_URL, User, Pass);
            stmt = conn.createStatement();

            System.out.println(" ");

            System.out.println("Introduce el nombre de la tarea");
            String Titulo = sc.nextLine();

            System.out.println(" Fecha de la tarea(yyyy-mm-dd)");
            String fecha = sc.nextLine();

            try {
                date = fechanueva.parse(fecha);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Date Fecha = new java.sql.Date(date.getTime());

            try {
                String consulta = "INSERT INTO tarea(Titulo, Fecha, Estado) VALUES(?, ?, 0)";

                PreparedStatement preparedStatement = conn.prepareStatement(consulta);

                preparedStatement.setString(1, Titulo);

                preparedStatement.setDate(2, Fecha);

                preparedStatement.executeQuery();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("Tarea Añadida con Éxito");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void VerTareas() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, User, Pass);
        stmt = conn.createStatement();
        String sql = "SELECT * FROM tarea WHERE Estado=0";
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
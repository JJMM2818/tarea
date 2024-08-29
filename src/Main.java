import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3307/personas";
    private static final String USUARIO = "root"; 
    private static final String CONTRASEÑA = ""; 
    
    public static void main(String[] args) {
        
        Pair<Integer, String> datosPersona = new Pair<>(18, "Juan Perez");
        Pair<String, String> datosSalud = new Pair<>("EPS Sanitas", "2000-02-22");

        insertarDatos(datosPersona, datosSalud);

    }
    private static void insertarDatos(Pair<Integer, String> datosPersona, Pair<String, String> datosSalud) {
        String consultaSQL = "INSERT INTO personas (edad, nombre, eps, fecha nacimiento) VALUES (?, ?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
             PreparedStatement declaracionPreparada = conexion.prepareStatement(consultaSQL)) {

            declaracionPreparada.setInt(1, datosPersona.getFirst());
            declaracionPreparada.setString(2, datosPersona.getSecond());
            declaracionPreparada.setString(3, datosSalud.getFirst());
            declaracionPreparada.setString(4, datosSalud.getSecond());

            declaracionPreparada.executeUpdate();
            System.out.println("Información guardada con exito");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}

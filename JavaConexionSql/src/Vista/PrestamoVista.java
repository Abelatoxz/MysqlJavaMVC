package Vista;
import Modelo.Libro;
import Modelo.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import Modelo.Prestamo;
public class PrestamoVista {
    private final Scanner scanner;
    public PrestamoVista() { scanner = new Scanner(System.in); }

    public void mostrarPrestamos(List<Prestamo> prestamos){
        if (prestamos.isEmpty()){
            System.out.println("No hay prestamos para mostrar.");
        }else {
            System.out.println("Lista de prestamos: ");
            for (Prestamo prestamo : prestamos){
                System.out.println(prestamo);
            }
        }
    }
    public void mostrarMensajeError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}

package Vista;

import Modelo.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UsuarioVista {
    private final Scanner scanner;
    public UsuarioVista(){
        scanner = new Scanner(System.in);
    }
    public void mostrarUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios para mostrar.");
        } else {
            System.out.println("Lista de Usuarios:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }
    public Usuario obtenerDatosNuevoUsuario(){
        System.out.println("Mete los datos del usuario");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("email: ");
        String email = scanner.nextLine();
        System.out.print("Telefono: ");
        String telefono = scanner.nextLine();
        System.out.print("Rol: ");
        String rol = scanner.nextLine();
        System.out.println("Date: ");
        System.out.print("Fecha de registro (yyyy-MM-dd): ");
        String fechaRegistroStr = scanner.nextLine();
        Date fechaRegistro = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            fechaRegistro = dateFormat.parse(fechaRegistroStr);
        }catch (ParseException e){
            System.out.println("Error" + e.getMessage());
            fechaRegistro = new Date();
        }
        return  new Usuario(0,nombre,apellido,email,telefono,rol,fechaRegistro);
    }
    public void mostrarMensajeError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}

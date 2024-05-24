package excepciones;

public class CamposVacios extends Exception{

    public CamposVacios(String mensajeError) {
        super(mensajeError);
    }
}

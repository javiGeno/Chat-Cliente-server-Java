
import java.io.Serializable;
import java.util.ArrayList;


public class PersonaChat implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String mensaje;
    private boolean conectado;
   
    
    public PersonaChat(String n, String m) {
        
        conectado=true;
        nombre=n;
        mensaje=m;
        
        
    }

   
    
    
    
    public String getNombre() {
        return nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }
    
    
    
}


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;


public class Main {

    static final int PUERTO = 6000;
    public static ServerSocket servidor;
    public static LlegadaNuevosMensajes conversacion;
    public static nuevosContactos conectados;
    
    public static void main(String[] arg) throws IOException {
        
        
        conversacion=new LlegadaNuevosMensajes();
        conectados=new nuevosContactos();
        try {
            
             servidor = new ServerSocket(PUERTO);
            while(true){
                
                System.out.println("Esperando al cliente..."+servidor.getLocalPort());
                Socket cliente = servidor.accept(); // Crea objeto
                SocketParticipante conversadorNuevo=new SocketParticipante(cliente, conversacion);//se le pasa el socket y el objeto observable
                conversacion.addObserver(conversadorNuevo);//se añade a la clase observable 
                conectados.addObserver(conversadorNuevo);
                conversadorNuevo.start();
               
                
            }
            
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            servidor.close();
        }
        servidor.close();
    }
    
   
    
    
}

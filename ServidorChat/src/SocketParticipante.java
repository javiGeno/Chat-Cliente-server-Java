
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SocketParticipante extends Thread implements Observer {
    
    private Socket participante;
    private PersonaChat envio;
    private PersonaChat recibo;
    private ObjectInputStream entrada = null;
    private ObjectOutputStream salida=null;
    private  LlegadaNuevosMensajes centralMensajes;//objeto observable que notificará de cambios
   
    
    public SocketParticipante(Socket participa, LlegadaNuevosMensajes cM){
        
        participante=participa;
        centralMensajes=cM;
        
        
    }

    public Socket getParticipante() {
        return participante;
    }


    public void setParticipante(Socket participante) {
        this.participante = participante;
    }


    public PersonaChat getEnvio() {
        return envio;
    }

    public PersonaChat getRecibo() {
        return recibo;
    }

    public void setEnvio(PersonaChat envio) {
        this.envio = envio;
    }

    public void setRecibo(PersonaChat recibo) {
        this.recibo = recibo;
    }

    
    
    public void run(){
	
        try{
             
            do{
                
                // Flujo de entrada
                entrada = new ObjectInputStream(participante.getInputStream());
                recibo = (PersonaChat) entrada.readObject();
                
                
                if(recibo.isConectado() && recibo.getMensaje()!=null)//si ha recibido un objeto mandamos mensaje a todos
                {
                    centralMensajes.nuevoMensaje(recibo.getNombre().toUpperCase()+":\n    "+recibo.getMensaje()+"\n\n");
                    
                    System.out.println("Datos recibidos del Cliente: " +recibo.getNombre()+":\n\t"+recibo.getMensaje()+"\n");
                }
                else
                {
                    if(recibo.isConectado())
                    {
                       
                       Main.conectados.nuevoConectado(recibo.getNombre());
                        
                    }
                    else
                    {
                       Main.conectados.borrarConectado(recibo.getNombre());
                       Main.conectados.deleteObserver(this);
                    }
                }
                
             
            }while(recibo.isConectado());
            
            // cerramos streams 
             entrada.close();
        
        }catch(IOException | ClassNotFoundException e)
        {
            System.err.println("Se ha desconectado algún cliente");
            
            try {
                participante.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                Logger.getLogger(SocketParticipante.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        }
        
    }

    
    public void update(Observable o, Object mensaje) {
       
        
        try
        {
            
           
            if(o==Main.conversacion && mensaje instanceof String)
            {
                // Flujo de salida
                salida = new ObjectOutputStream(participante.getOutputStream());
                envio = new PersonaChat(recibo.getNombre(), (String)mensaje);// Se prepara el nuevo mensaje el conversador
                salida.writeObject(envio);// enviando el objeto SALTA EXCEPTION!
                System.out.println("Envío realizado al conversador: " + envio.getNombre() + "-" 
                                                                      + envio.getMensaje());
           }
            else
            {
                if(o==Main.conectados && mensaje instanceof ArrayList)
                {
                    salida = new ObjectOutputStream(participante.getOutputStream());
                    salida.writeObject((ArrayList<String>)mensaje);
                    System.out.println("Entra en envio lista");
                }
            }

            
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.err.println("Ha ocurrido un problema a enviar el mensaje a los cliente");
            try {
                salida.close();
            } catch (IOException ex) {
                Logger.getLogger(SocketParticipante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conversacion extends Thread {
    
    private Socket personaServ;
    private PersonaChat persona;
    private VentanaPrincipal_1 venP;
    private ObjectInputStream entrada = null;
    
    public Conversacion(Socket s, PersonaChat per, VentanaPrincipal_1 venP)
    {
        personaServ=s;
        persona=per;
        this.venP=venP;
    }
    
    
    
    
    @Override
    public void run() {
       
        javax.swing.JTextArea zonaEscritura = venP.getjPanelChat().getjTextArea2();//obtenemos referencia del text area
        String conversacionAnterior;//para concatenar lo que había antes con lo nuevo que entra
        
        
        try
        {
           
            /*
            mientras la persona esta conectada se van recibiendo mensajes desde el servidor, cuando se desconecte,
            al intentar leer saltará la excepcion.
            */
            while(persona.isConectado())
            {
                conversacionAnterior=zonaEscritura.getText();
                entrada = new ObjectInputStream(personaServ.getInputStream());
                
                Object obj=entrada.readObject();
                
                if(obj instanceof PersonaChat)
                {
                
                   
                    System.out.println(conversacionAnterior);
                    zonaEscritura.setText(conversacionAnterior+((PersonaChat) obj).getMensaje());
                }
                else
                {
                    if(obj instanceof ArrayList)
                    {
                        System.out.println("Pero si entra");
                        String[] lista=llenarLista((ArrayList) obj);
                        System.out.println(obj);
                        venP.getjPanelChat().getjList1().setListData(lista);
                        for(int i=0; i<lista.length;i++)
                       {
                           System.out.println(lista[i]);
                       }
                    }
                }
                
                
            }
            
            entrada.close();
            
            
        }
        catch(Exception e)
        {
            
            System.err.println("Ha ocurrido algún problema al recibir el mensaje o la persona se ha desconectado");
            try {
                entrada.close();
            } catch (IOException ex) {
                Logger.getLogger(Conversacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    private String[] llenarLista(ArrayList l) {
        
        
        String[] lista = new String[l.size()];
        
        for(int i=0; i<l.size();i++)
        {
            lista[i] = (String) l.get(i);
        }
        
        return lista;
    }
    
    
    
    
}

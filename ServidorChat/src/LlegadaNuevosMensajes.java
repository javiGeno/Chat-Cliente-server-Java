
import java.util.Observable;
import java.util.Observer;


public class LlegadaNuevosMensajes extends Observable {

    private String conversacion;
    
    public LlegadaNuevosMensajes() {
        
        conversacion="";
    }
    
    public void nuevoMensaje(String mensaje)
    {
        conversacion=mensaje;//un mensaje nuevo de un conversador se añade
        this.setChanged();//ha habido una actualizacion en este objeto, los que lo observan lo sabran
        this.notifyObservers(conversacion);//se notifica a todos los observadoresy se le envia nuevo mensaje
    }
    
    @Override
    protected synchronized void setChanged() {
        super.setChanged(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers(Object o) {
        super.notifyObservers(o); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void deleteObserver(Observer obsrvr) {
        super.deleteObserver(obsrvr); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void addObserver(Observer obsrvr) {
        super.addObserver(obsrvr); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

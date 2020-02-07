
import java.util.ArrayList;
import java.util.Observable;


public class nuevosContactos extends Observable{
     private ArrayList <String>personasConectadas;
     
     public nuevosContactos()
     {
         personasConectadas=new ArrayList<>();
     }
     
     void nuevoConectado(String nombre) {
        personasConectadas.add(nombre);
        this.setChanged();//ha habido una actualizacion en este objeto, los que lo observan lo sabran
        this.notifyObservers(personasConectadas);//se notifica a todos los observadoresy se le envia nuevo mensaje
    }

    void borrarConectado(String nombre) {
        personasConectadas.remove(nombre);
        this.setChanged();//ha habido una actualizacion en este objeto, los que lo observan lo sabran
        this.notifyObservers(personasConectadas);//se notifica a todos los observadoresy se le envia nuevo mensaje
    }
}

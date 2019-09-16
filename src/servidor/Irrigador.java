package servidor;

import java.io.*;
import java.util.*;
import java.time.LocalTime;
/**
 *
 * @author i5-6500
 */
public class Irrigador implements Serializable{
    
    private int TipoClase=5;
    private int id;
    private String horaRiego;
    private int tiempRiego;
    
    public Irrigador(){
        System.out.println("Creando irrigador vacia");
    }
    
    Irrigador(int ID,String u, int p){
        System.out.println("Creando irrigador("+ID+" ,"+ u +", "+ p +")");
        id=ID;
        horaRiego = u;
        tiempRiego = p;
    }
    public int getTipoClase()
    {
        return TipoClase;
    }
    
    public void writeExternal(ObjectOutput out) throws IOException{
        System.out.println("Irrigador.writeExternal");
        //Explicitamente indicamos cuales atributos se almacenan
        out.writeObject(horaRiego);
        out.writeObject(tiempRiego);
    }
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
        System.out.println("Irrigador.readExternal");
        //Explicitamente indicamos cuales atributos vamos a recuperar
        horaRiego = (String) in.readObject();
        tiempRiego = (int) in.readObject();
        
    }
}

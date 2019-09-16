package servidor;

import java.io.*;
import java.util.*;
import java.time.LocalTime;
/**
 *
 * @author i5-6500
 */
public class Termostato implements Serializable{
    
    private int TipoClase=3;
    private int id;
    private int temperatura;
    
    public Termostato(){
        System.out.println("Creando termostato vacia");
    }
    
    Termostato(int ID,int u){
        System.out.println("Creando termostato("+ID+" ,"+ u +")");
        temperatura = u;
        id=ID;
    }

    public int getTipoClase()
    {
        return TipoClase;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    
    
    public void writeExternal(ObjectOutput out) throws IOException{
        System.out.println("Termostato.writeExternal");
        //Explicitamente indicamos cuales atributos se almacenan
        out.writeObject(temperatura);
    }
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
        System.out.println("Termostato.readExternal");
        //Explicitamente indicamos cuales atributos vamos a recuperar
        temperatura = (int) in.readObject();
        
    }
}

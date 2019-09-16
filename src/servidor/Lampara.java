package servidor;

import java.io.*;
import java.util.*;
import java.time.LocalTime;
/**
 *
 * @author i5-6500
 */
public class Lampara implements Serializable{
    
    private int TipoClase=7;
    private int id;
    private String ubicacion;
    private boolean estado;
    
    public Lampara(){
        System.out.println("Creando lampara vacia");
    }
    
    Lampara(int ID,String u, boolean p){
        System.out.println("Creando lampara("+ID+" ,"+ u +", "+ p +")");
        id=ID;
        ubicacion = u;
        estado = p;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    
    public void writeExternal(ObjectOutput out) throws IOException{
        System.out.println("Luminaria.writeExternal");
        //Explicitamente indicamos cuales atributos se almacenan
        out.writeObject(ubicacion);
        out.writeObject(estado);
    }
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
        System.out.println("Luminaria.readExternal");
        //Explicitamente indicamos cuales atributos vamos a recuperar
        ubicacion = (String) in.readObject();
        estado = (boolean) in.readObject();
        
    }
}

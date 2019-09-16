package servidor;

import java.io.*;
import java.util.*;
import java.time.LocalTime;
/**
 *
 * @author i5-6500
 */
public class Luminaria implements Serializable{
    
    private int TipoClase=8;
    private int id;
    private String ubicacion;
    private int intensidad;
    private boolean estado;
    
    public Luminaria(){
        System.out.println("Creando luminaria vacia");
    }
    
    Luminaria(int ID,String u, int p, boolean q){
        System.out.println("Creando luminaria("+ID+" ,"+ u +", "+ p +", "+ q +")");
        id=ID;
        ubicacion = u;
        intensidad = p;
        estado = q;
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

    public int getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
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
        out.writeObject(intensidad);
        out.writeObject(estado);
    }
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
        System.out.println("Luminaria.readExternal");
        //Explicitamente indicamos cuales atributos vamos a recuperar
        ubicacion = (String) in.readObject();
        intensidad = (int) in.readObject();
        estado = (boolean) in.readObject();
        
    }
}

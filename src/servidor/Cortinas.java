package servidor;

import java.io.*;
import java.util.*;
import java.time.LocalTime;
/**
 *
 * @author i5-6500
 */
public class Cortinas implements Serializable{
    
    private int TipoClase=2;
    private int id;
    private String ubicacion;
    private String posicion;
    private String horaAper;
    private String horaCierre;
    
    public Cortinas(){
        System.out.println("Creando cortina vacia");
    }
    
    Cortinas(int ID,String u, String p, String q, String g){
        System.out.println("Creando cortina("+ID+", "+u +", "+ p +", "+ q +", "+ g + ")");
        id=ID;
        ubicacion = u;
        posicion = p;
        horaAper = q;
        horaCierre = g;
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

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getHoraAper() {
        return horaAper;
    }

    public void setHoraAper(String horaAper) {
        this.horaAper = horaAper;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }
    
    
    
    public void writeExternal(ObjectOutput out) throws IOException{
        System.out.println("Refrigerador.writeExternal");
        //Explicitamente indicamos cuales atributos se almacenan
        out.writeObject(ubicacion);
        out.writeObject(posicion);
        out.writeObject(horaAper);
        out.writeObject(horaCierre);
    }
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
        System.out.println("Refrigerador.readExternal");
        //Explicitamente indicamos cuales atributos vamos a recuperar
        ubicacion = (String) in.readObject();
        posicion = (String) in.readObject();
        horaAper = (String) in.readObject();
        horaCierre = (String) in.readObject();
        
    }
}

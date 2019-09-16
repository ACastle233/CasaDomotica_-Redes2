package servidor;

import java.io.*;
import java.time.LocalTime;
/**
 *
 * @author i5-6500
 */
public class Alarma implements Serializable{
    
    private int TipoClase=6;
    private int id;
    private String horaInicio;
    private String horaTermino;
    private String estado;
    
    public Alarma(){
        System.out.println("Creando alarma vacia");
    }
    
    Alarma(int ID,String u, String p, String q){
        System.out.println("Creando cortina("+ u +", "+ p +", "+ q +")");
        id=ID;
        horaInicio = u;
        horaTermino = p;
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

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(String horaTermino) {
        this.horaTermino = horaTermino;
    }

    public String isEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void writeExternal(ObjectOutput out) throws IOException{
        System.out.println("Alarma.writeExternal");
        //Explicitamente indicamos cuales atributos se almacenan
        out.writeObject(horaInicio);
        out.writeObject(horaTermino);
        out.writeObject(estado);
    }
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
        System.out.println("Alarma.readExternal");
        //Explicitamente indicamos cuales atributos vamos a recuperar
        horaInicio = (String) in.readObject();
        horaTermino = (String) in.readObject();
        estado = (String) in.readObject();
        
    }
}

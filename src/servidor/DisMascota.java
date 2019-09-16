package servidor;

import java.io.*;
import java.util.*;
import java.time.LocalTime;
/**
 *
 * @author i5-6500
 */
public class DisMascota implements Serializable{
    
    private int TipoClase=4;
    private int id;
    private String horaDes;
    private int cantDes;
    private String horaCom;
    private int cantCom;
    private String horaCena;
    private int cantCena;
    
    public DisMascota(){
        System.out.println("Creando mascota vacia");
    }
    
    DisMascota(int ID,String u, int p, String q, int g, String c, int r){
        System.out.println("Creando mascota("+ID+" ,"+ u +", "+ p +", "+ q +", "+ g + ", "+ c + ", "+ r + ")");
        id=ID;
        horaDes = u;
        cantDes = p;
        horaCom = q;
        cantCom = g;
        horaCena = c;
        cantCena = r;
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

    public String getHoraDes() {
        return horaDes;
    }

    public void setHoraDes(String horaDes) {
        this.horaDes = horaDes;
    }

    public int getCantDes() {
        return cantDes;
    }

    public void setCantDes(int cantDes) {
        this.cantDes = cantDes;
    }

    public String getHoraCom() {
        return horaCom;
    }

    public void setHoraCom(String horaCom) {
        this.horaCom = horaCom;
    }

    public int getCantCom() {
        return cantCom;
    }

    public void setCantCom(int cantCom) {
        this.cantCom = cantCom;
    }

    public String getHoraCena() {
        return horaCena;
    }

    public void setHoraCena(String horaCena) {
        this.horaCena = horaCena;
    }

    public int getCantCena() {
        return cantCena;
    }

    public void setCantCena(int cantCena) {
        this.cantCena = cantCena;
    }
    
    
    
    public void writeExternal(ObjectOutput out) throws IOException{
        System.out.println("DisMascota.writeExternal");
        //Explicitamente indicamos cuales atributos se almacenan
        out.writeObject(horaDes);
        out.writeObject(cantDes);
        out.writeObject(horaCom);
        out.writeObject(cantCom);
        out.writeObject(horaCena);
        out.writeObject(cantCena);
    }
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
        System.out.println("DisMascota.readExternal");
        //Explicitamente indicamos cuales atributos vamos a recuperar
        horaDes = (String) in.readObject();
        cantDes = (int) in.readObject();
        horaCom = (String) in.readObject();
        cantCom = (int) in.readObject();
        horaCena = (String) in.readObject();
        cantCena = (int) in.readObject();
        
    }
}

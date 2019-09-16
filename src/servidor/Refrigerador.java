/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package servidor;

import java.io.*;
import java.util.*;

/**
 *
 * @author i5-6500
 */
public class Refrigerador implements Serializable {
    
    private int TipoClase=1;
    private int id;
    private float tempFrigo;
    private float temCentro;
    private float temCharola;
    
    public Refrigerador(){
        System.out.println("Creando refrigerador vacio");
    }
    
    Refrigerador(int ID,float u, float p, float q){
        System.out.println("Creando refrigerador("+ID+", "+u +", "+ p +", "+ q +")");
        id=ID;
        tempFrigo = u;
        temCentro = p;
        temCharola = q;
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

    public float getTempFrigo() {
        return tempFrigo;
    }

    public void setTempFrigo(float tempFrigo) {
        this.tempFrigo = tempFrigo;
    }

    public float getTemCentro() {
        return temCentro;
    }

    public void setTemCentro(float temCentro) {
        this.temCentro = temCentro;
    }

    public float getTemCharola() {
        return temCharola;
    }

    public void setTemCharola(float temCharola) {
        this.temCharola = temCharola;
    }
    
    public void writeExternal(ObjectOutput out) throws IOException{
        System.out.println("Refrigerador.writeExternal");
        //Explicitamente indicamos cuales atributos se almacenan
        out.writeObject(tempFrigo);
        out.writeObject(temCentro);
        out.writeObject(temCharola);
    }
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
        System.out.println("Refrigerador.readExternal");
        //Explicitamente indicamos cuales atributos vamos a recuperar
        tempFrigo = (float) in.readObject();
        temCentro = (float) in.readObject();
        temCharola = (float) in.readObject();
        
    }
}

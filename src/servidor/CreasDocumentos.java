/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author ferna
 */
 public class CreasDocumentos {
    public static void main(String[]args) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        System.out.println("Creando objetos");
        String[] TipoUsuarios = {"Administrador","Usuario","Usuario"};
        int []ids={3,2,1};
        String[] passwords = {"12345","678910","111213"};
        String[] alias={"Fernanda","Alexillo","Morgado"};
        String[] nombres={"Fernanda Martínez Trujillo","Alejandro Castillo","Morgado"};
        
        ///Documento con los usuarios
        ListaUsuarios lp = new ListaUsuarios(ids,TipoUsuarios,alias,passwords,nombres);
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Usuarios.txt"));
            int TipoU=0;//tipo de usuario
            o.writeInt(TipoU);
            o.flush();
            int Cant=TipoUsuarios.length;//cantidad de usuarios
            o.writeInt(Cant);
            o.flush();
            o.writeObject(lp);//escribir lista de usuarios
            o.flush();
        //Documento con los objetos de la casa
        o=new ObjectOutputStream(new FileOutputStream("Objetos.txt")); //abrir archivo objetos.txt
        
        System.out.println("Creando refrigeradores");
           int TipoO=1;
           o.writeInt(TipoO);
           o.flush();
           Cant=2;
           o.writeInt(Cant);
           o.flush();
           Refrigerador R=new Refrigerador(1,(float)3,(float)10,(float)5);//id,tempFigo(entre -3 y 5 °C),temCentro(entre 0 y 10 °C),tampCharola(entre 5 y el maximo de temCentro)
           o.writeObject(R);
           o.flush();
           R=new Refrigerador(2,(float)-5,(float)5,(float)5);
           o.writeObject(R);
           o.flush();
           
         System.out.println("Creando cortinas");
           TipoO=2;
           o.writeInt(TipoO);
           o.flush();
           Cant=2;
           o.writeInt(Cant);
           o.flush();
           Cortinas C=new Cortinas(1,"Sala","Abierta","8:00","17:00");//id,ubucacion,posicio,hora de apertura,hora de cierre
           o.writeObject(C);
           o.flush();
           C=new Cortinas(2,"Habitacion 1","Cerrada","8:00","19:00");
           o.writeObject(C);
           o.flush();
           
         System.out.println("Creando termostatos");
           TipoO=3;
           o.writeInt(TipoO);
           o.flush();
           Cant=2;
           o.writeInt(Cant);
           o.flush();
           Termostato T=new Termostato(1,12);//id,temp(entre 18 y 30°C)
           o.writeObject(T);
           o.flush();
           T=new Termostato(2,25);
           o.writeObject(T);
           o.flush();
         
           System.out.println("Creando Dispensador de Comida para Mascota");
           TipoO=4;
           o.writeInt(TipoO);
           o.flush();
           Cant=1;
           o.writeInt(Cant);
           o.flush();
           DisMascota M=new DisMascota(1,"8:00",120,"14:00",120,"20:00",120);//id,HDesayuna,CantDesayuno,HComida,CantComida,HCena,CantCena
           o.writeObject(M);
           o.flush();
           
         System.out.println("Creando Irrigadores");
           TipoO=5;
           o.writeInt(TipoO);
           o.flush();
           Cant=3;
           o.writeInt(Cant);
           o.flush();
           Irrigador I=new Irrigador(1,"7:30",20);//id,hora,tiempo de uso
           o.writeObject(I);
           o.flush();
           I=new Irrigador(2,"14:00",15);
           o.writeObject(I);
           o.flush();
           I=new Irrigador(3,"18:10",10);
           o.writeObject(I);
           o.flush();
         
         System.out.println("Creando Alarmas");
           TipoO=6;
           o.writeInt(TipoO);
           o.flush();
           Cant=4;
           o.writeInt(Cant);
           o.flush();
           Alarma A=new Alarma(1,"4:00","4:15","Apagado");//id,hora de inicio,hora de apagado, estado(apgado/encendido)
           o.writeObject(A);
           o.flush();
           A=new Alarma(2,"4:30","4:45","Apagado");
           o.writeObject(A);
           o.flush();
           A=new Alarma(3,"5:00","5:15","Apagado");
           o.writeObject(A);
           o.flush();
           A=new Alarma(4,"5:30","6:00","Apagado");
           o.writeObject(A);
           o.flush();
           
         System.out.println("Creando lamparas");
           TipoO=7;
           o.writeInt(TipoO);
           o.flush();
           Cant=3;
           o.writeInt(Cant);
           o.flush();
           Lampara L=new Lampara(1,"Garage",false);//id,ubicacion,estado
           o.writeObject(L);
           o.flush();
           L=new Lampara(2,"Habitacion 1",false);
           o.writeObject(L);
           o.flush();
           L=new Lampara(3,"Cocina",true);
           o.writeObject(L);
           o.flush();
           
         System.out.println("Creando luminarias");
           TipoO=8;
           o.writeInt(TipoO);
           o.flush();
           Cant=4;
           o.writeInt(Cant);
           o.flush();
           Luminaria Lu=new Luminaria(1,"Sala",30,true);//id, ubicacion,intensidad(entre 0 y 100, va de 10 en 10),estado
           o.writeObject(Lu);
           o.flush();
           Lu=new Luminaria(2,"Comedor",40,false);
           o.writeObject(Lu);
           o.flush();
           Lu=new Luminaria(3,"Habitacion 2",10,true);
           o.writeObject(Lu);
           o.flush();
           Lu=new Luminaria(4,"Cuarto de Estudio",60,false);
           o.writeObject(Lu);
           o.flush();
           
        System.out.println("\nRecuperando elementos");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Usuarios.txt"));
        
        TipoU=in.readInt();
        Cant=in.readInt();
        lp = (ListaUsuarios) in.readObject();
        System.out.println("Tipo de Usuario: "+TipoU+"\nCantidad de usuario: "+Cant+"\nUsuarios:");
        lp.muestraUsuario();
        
        
        
        
        
    }
}

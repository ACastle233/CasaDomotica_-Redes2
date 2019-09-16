/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author ferna
 */
public class Servidor {
 public static void main(String []args) throws FileNotFoundException, IOException, ClassNotFoundException   
 {
     try{
        System.out.println("Recuperando elementos");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Usuarios.txt"));
        int TipoU=in.readInt();
        int Cant=in.readInt();
        ListaUsuarios lp = (ListaUsuarios) in.readObject();
        
        System.out.println("\nRecuperando objetos");
        ///crear listas de objetos
        ArrayList <Refrigerador> ListaR=new ArrayList <Refrigerador>();
        ArrayList <Cortinas> ListaC=new ArrayList <Cortinas>();
        ArrayList <Termostato> ListaT=new ArrayList <Termostato>();
        ArrayList <DisMascota> ListaM=new ArrayList <DisMascota>();
        ArrayList <Irrigador> ListaI=new ArrayList <Irrigador>();
        ArrayList <Alarma> ListaA=new ArrayList <Alarma>();
        ArrayList <Lampara> ListaL=new ArrayList <Lampara>();
        ArrayList <Luminaria> ListaLu=new ArrayList <Luminaria>();
        //abrir el archivo donde estan los objetos
        in = new ObjectInputStream(new FileInputStream("Objetos.txt"));
        int TipoO;
       
        for(int j=0;j<8;j++)
        {
            TipoO=in.readInt();
           switch(TipoO)
            {
                case 1:
                {
                    System.out.println("Leer Refrigeradores");
                    Cant=in.readInt();//leer cantidad de refrigeradores
                    for(int i=0;i<Cant;i++)
                    {
                        Refrigerador R=(Refrigerador)in.readObject();
                        ListaR.add(R);
                    }
                    break;
                }
                case 2:
                {
                    System.out.println("Leer Cortinas");
                    Cant=in.readInt();//leer cantidad de cortinas
                    for(int i=0;i<Cant;i++)
                    {
                        Cortinas C=(Cortinas)in.readObject();
                        ListaC.add(C);
                    }
                    break;
                }
                case 3:
                {
                    System.out.println("Leer Termostatos");
                    Cant=in.readInt();//leer cantidad de termostatos
                    for(int i=0;i<Cant;i++)
                    {
                        Termostato T=(Termostato)in.readObject();
                        ListaT.add(T);
                    }
                    break;
                }
                case 4:
                {
                    System.out.println("Leer DisMascota");
                    Cant=in.readInt();//leer cantidad de dismascota
                    for(int i=0;i<Cant;i++)
                    {
                        DisMascota M=(DisMascota)in.readObject();
                        ListaM.add(M);
                    }
                    break;
                }
                case 5:
                {
                    System.out.println("Leer Irrigadores");
                    Cant=in.readInt();//leer cantidad de irrigadores
                    for(int i=0;i<Cant;i++)
                    {
                        Irrigador I=(Irrigador)in.readObject();
                        ListaI.add(I);
                    }
                    break;
                }
                case 6:
                {
                    System.out.println("Leer Alarmas");
                    Cant=in.readInt();//leer cantidad de alarmas
                    for(int i=0;i<Cant;i++)
                    {
                        Alarma A=(Alarma)in.readObject();
                        ListaA.add(A);
                    }
                    break;
                }
                case 7:
                {
                    System.out.println("Leer Lamparas");
                    Cant=in.readInt();//leer cantidad de alarmas
                    for(int i=0;i<Cant;i++)
                    {
                        Lampara L=(Lampara)in.readObject();
                        ListaL.add(L);
                    }
                    break;
                }
                case 8:
                {
                    System.out.println("Leer Luminarias");
                    Cant=in.readInt();//leer cantidad de luminarias
                    for(int i=0;i<Cant;i++)
                    {
                        Luminaria Lu=(Luminaria)in.readObject();
                        ListaLu.add(Lu);
                    }
                    break;
                }
            }
            
        }
        System.out.println("Objetos leidos");
     
     
    //creamos el socket
    ServerSocket s=new ServerSocket(9000);
    for(;;)
    {
        
            Socket cl = s.accept(); //bloqueo:se tiene el programa hasta que llegue un cliente
            System.out.println("Conexión establecida:"+cl.getInetAddress()+":"+cl.getPort());
            
            in=new ObjectInputStream(cl.getInputStream());//fluejo de entrada
            ObjectOutputStream out=new ObjectOutputStream(cl.getOutputStream());//flujo de salida
            
            boolean verificar=true;//variable para verificar si existe el usuario o no
            int numU=0;//numero de usuario por si es aceptado
            Usuario u=(Usuario)in.readObject();//recibir usuario
            for(int i=0;i<lp.getTamLista();i++)
            {
                Usuario aux=lp.getUsuario(i);
                if(aux.getAlias().equals(u.getAlias()))
                {
                    if(aux.getPassword().equals(u.getPassword()))
                    {
                        numU=i;
                        String res="Ingreso Aceptado";
                        verificar=true;
                        out.writeBoolean(verificar);
                        out.flush();
                        out.writeUTF(res);
                        out.flush();
                        break;
                    }
                    else
                    {
                        String res="Contraseña Incorrecta";
                        verificar=false;
                        out.writeBoolean(verificar);
                        out.flush();
                        out.writeUTF(res);
                        out.flush();
                        break;
                    }
                }
                else
                {
                    if(i==(lp.getTamLista()-1))
                    {
                        String res="No existe el usuario";
                        verificar=false;
                        out.writeBoolean(verificar);
                        out.flush();
                        out.writeUTF(res);
                        out.flush();
                        break;
                    }
                }
            }
            if(verificar==false)
            {
                System.out.println("Fallo en la conexion");
                cl.close();
                verificar=true;
            }
            else
            {   
                System.out.println("Cliente aceptado");
                //enviar datos del usuario
                Usuario Usr=lp.getUsuario(numU);
                out.writeObject(Usr);
                out.flush();
                if(Usr.getTipoUsuario().equals("Administrador"))
                {
                    //enviar lista de usuarios
                    out.writeObject(lp);
                    out.flush();
                    //enviar listas de objetos
                    out.writeObject(ListaR);
                    out.flush();
                    out.writeObject(ListaC);
                    out.flush();
                    out.writeObject(ListaT);
                    out.flush();
                    out.writeObject(ListaM);
                    out.flush();
                    out.writeObject(ListaI);
                    out.flush();
                    out.writeObject(ListaA);
                    out.flush();
                    out.writeObject(ListaL);
                    out.flush();
                    out.writeObject(ListaLu);
                    out.flush();
                    
                    //Recibir respuesta
                    int Respuesta=in.readInt();
                    switch(Respuesta)
                    {
                        case 1://Dar de Alta
                        {
                            int TipoClase=in.readInt();
                            switch(TipoClase)
                            {
                                case 0:
                                {
                                    Usuario newUsuario=(Usuario)in.readObject();
                                    lp.AddUsuario(newUsuario);
                                    for(int i=0;i<lp.getTamLista();i++)
                                    {
                                        newUsuario=lp.getUsuario(i);
                                        System.out.println("U."+newUsuario.getNombre()+" id:"+newUsuario.getId());
                                    }
                                    break;
                                }
                                case 1:
                                {
                                    
                                    break;
                                }
                                case 2:
                                {
                                    break;
                                }
                                case 3:
                                {
                                    break;
                                }
                                case 4:
                                {
                                    break;
                                }
                                case 5:
                                {
                                    break;
                                }
                                case 6:
                                {
                                    break;
                                }
                                case 7:
                                {
                                    break;
                                }
                                case 8:
                                {
                                    break;
                                }
                            }
                            break;
                        }
                        case 2:
                        {
                            int TipoClase=in.readInt();
                            switch(TipoClase)
                            {
                                case 0:
                                {
                                    Usuario newUsuario=(Usuario)in.readObject();
                                    lp.AddUsuario(newUsuario);
                                    for(int i=0;i<lp.getTamLista();i++)
                                    {
                                        newUsuario=lp.getUsuario(i);
                                        System.out.println("U."+newUsuario.getNombre()+" id:"+newUsuario.getId());
                                    }
                                    break;
                                }
                                case 1:
                                {
                                    Usuario DeleteU=(Usuario)in.readObject();
                                    for(int i=0;i<lp.getTamLista();i++)
                                    {
                                        Usuario aux=lp.getUsuario(i);
                                        if(aux.getAlias().equals(DeleteU.getAlias()))
                                        {
                                            System.out.println("Usuario Eliminado\nUsuario:"+aux.getAlias());
                                            lp.DeleteUsuario(i);
                                        }
                                    }
                                    break;
                                }
                                case 2:
                                {
                                    break;
                                }
                                case 3:
                                {
                                    break;
                                }
                                case 4:
                                {
                                    break;
                                }
                                case 5:
                                {
                                    break;
                                }
                                case 6:
                                {
                                    break;
                                }
                                case 7:
                                {
                                    break;
                                }
                                case 8:
                                {
                                    break;
                                }
                            }
                            break;
                        }
                        case 3:
                        {
                            break;
                        }
                        case 4:
                        {
                            break;
                        }
                        case 5:
                        {
                            cl.close();
                            System.out.println("Conexion terminada");
                            break;
                        }
                    }
                }
                else
                {
                    //enviar listas de objetos
                    out.writeObject(ListaR);
                    out.flush();
                    out.writeObject(ListaC);
                    out.flush();
                    out.writeObject(ListaT);
                    out.flush();
                    out.writeObject(ListaM);
                    out.flush();
                    out.writeObject(ListaI);
                    out.flush();
                    out.writeObject(ListaA);
                    out.flush();
                    out.writeObject(ListaL);
                    out.flush();
                    out.writeObject(ListaLu);
                    out.flush();
                    //Recibir respuesta
                    int Respuesta=in.readInt();
                    switch(Respuesta)
                    {
                        case 1:
                        {
                            break;
                        }
                        case 2:
                        {
                            break;
                        }
                        case 3:
                        {
                            break;
                        }
                        case 4:
                        {
                            break;
                        }
                        case 5:
                        {
                            cl.close();
                            System.out.println("Conexion terminada");
                            break;
                        }
                    }
                }
            }
    }
   }catch(Exception e)
   {
       e.printStackTrace();
   }
}
}
 


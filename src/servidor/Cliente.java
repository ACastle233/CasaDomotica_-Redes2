/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ferna
 */
public class Cliente extends JFrame implements ActionListener{
    JFrame Ventana = new JFrame();
    JFrame VentanaA = new JFrame();
    JFrame VentanaU = new JFrame();
    JFrame VentanaAdmU = new JFrame();
    JFrame VentanaAltaU = new JFrame();
    /////////////////Componentes de Ventana///////////////
    //Labels
        JLabel User;
        JLabel Password;
        //Textfield
        JTextField U;
        JPasswordField P;
        //Buttons
        JButton Aceptar;   
    ////////////////Componentes VentanaA//////////////////
        JButton IngrU;
        JButton IngrObjetos;
        JButton SalirA;
    //////VentanaAdmU//////////////7
        JButton RegresarAU;
        JButton SalirAU;
        JButton AltaU;
        JButton AceptarU;
        JButton BajaUsuario;
        JButton btnCambioU;
        JButton btnConsultaU;
        JLabel MostrarU;
    /////////VentanaAltaU////
        JLabel text;
        JTextField nombre;
        JTextField nombreA;
        JTextField passW;
        JTextField tipoU;
        JButton btnUpdateUser;
        JButton RegresarU;
    ////////////////////////////////////////////////////
        /**
         * Ventana de Administración de Objetos
         */
        JButton btnRefrigerador;
        JButton btnAlarma;
        JButton btnCortinas;
        JButton btnDismascota;
        JButton btnIrrigador;
        JButton btnLampara;
        JButton btnLuminaria;
        JButton btnTermostato;
        
        Usuario editaUsuario;
        
        ObjectOutputStream out;
        ObjectInputStream in;
    
    public static ListaUsuarios listaUsuarios;
    public Cliente()
    {
    }
    public static void main(String []args)
    {
        Cliente C=new Cliente();
        C.VentanaPrincipal();//crear ventana
    }
    
    public void Acceder()
    {
        try
        {
            //ARREGLOS DE OBJETOS
            ArrayList <Refrigerador> ListaR;
            ArrayList <Cortinas> ListaC;
            ArrayList <Termostato> ListaT;
            ArrayList <DisMascota> ListaM;
            ArrayList <Irrigador> ListaI;
            ArrayList <Alarma> ListaA;
            ArrayList <Lampara> ListaL;
            ArrayList <Luminaria> ListaLu;
            //Creamos el socket y nos conectamos
            Socket cl = new Socket("127.0.0.1", 9000);
            out = new ObjectOutputStream(cl.getOutputStream());
            in = new ObjectInputStream(cl.getInputStream());
            //Crear usuario
            Usuario u = new Usuario();
            u.setAlias(U.getText());
            u.setPassword(P.getText());
            System.out.println("Usuario:"+U.getText()+" Password:"+P.getText());
            //enviar usuario
            out.writeObject(u);
            out.flush();
            //Recibir respuesta
            boolean verificar=in.readBoolean();
            String res=in.readUTF();
            JOptionPane.showMessageDialog(null,res);
            
            if(verificar==true)
            {
                Ventana.dispose();
                Ventana.removeAll();
                Ventana=new JFrame();
                //recibir usuario
                Usuario Urs=(Usuario)in.readObject();
                if(Urs.getTipoUsuario().equals("Administrador"))
                {
                    listaUsuarios=(ListaUsuarios)in.readObject();//recibir lista de usuarios
                    //recibir listas de objetos
                    ListaR=(ArrayList <Refrigerador>)in.readObject();
                    ListaC=(ArrayList <Cortinas>)in.readObject();
                    ListaT=(ArrayList <Termostato>)in.readObject();
                    ListaM=(ArrayList <DisMascota>)in.readObject();
                    ListaI=(ArrayList <Irrigador>)in.readObject();
                    ListaA=(ArrayList <Alarma>)in.readObject();
                    ListaL=(ArrayList <Lampara>)in.readObject();
                    ListaLu=(ArrayList <Luminaria>)in.readObject();
                    //abrir vetana de administrador
                    VentanaAdmi();
                }
                else
                {
                    //recibir listas de objetos
                    ListaR=(ArrayList <Refrigerador>)in.readObject();
                    ListaC=(ArrayList <Cortinas>)in.readObject();
                    ListaT=(ArrayList <Termostato>)in.readObject();
                    ListaM=(ArrayList <DisMascota>)in.readObject();
                    ListaI=(ArrayList <Irrigador>)in.readObject();
                    ListaA=(ArrayList <Alarma>)in.readObject();
                    ListaL=(ArrayList <Lampara>)in.readObject();
                    ListaLu=(ArrayList <Luminaria>)in.readObject();
                    //abrir ventana de usuario
                    VentanaUs();
                }
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public  void VentanaPrincipal()
    {
       User=new JLabel("Usuario:");
        User.setBounds(10,15,100,20);
        Password=new JLabel("Contraseña:");
        Password.setBounds(10,40,100,20);
        
        U=new JTextField("");
        U.setBounds(115,15,150,20);
        P=new JPasswordField("");
        P.setBounds(115,40,150,20);
        
        Aceptar=new JButton("Entrar");
        Aceptar.setBounds(100,80,80,30);
        Aceptar.addActionListener(this);
        
        Ventana.add(User);
        Ventana.add(Password);
        
        Ventana.add(U);
        Ventana.add(P);
        
        Ventana.add(Aceptar);
        //Caracteristicas de la ventana
        Ventana.setLayout(null);
        Ventana.setTitle("CASA DOMÓTICA");//Nombre de la ventana
        Ventana.setSize(280,170);//Dimensiones de la ventana. Ancho y alto
        Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Permite que la ventana se pueda cerrar cuando le presionemos en la X
        Ventana.setResizable(false);//Para que la ventana no se pueda reedimensionar
        Ventana.setLocationRelativeTo(null);//permitir que la venana se despliegue en el centro de la pantalla
        Ventana.setVisible(true);//Hacer visible la ventana*
        
    }
    public void VentanaAdmi()//ventana para administrador
    {
         ////////////Componentes VentanaA///////////////////
       IngrU=new JButton("Usuarios");
       IngrU.setBounds(20,50,100,30);
       IngrU.addActionListener(this);
       VentanaA.add(IngrU);
       
       IngrObjetos=new JButton("Objetos");
       IngrObjetos.setBounds(155,50,100,30);
       IngrObjetos.addActionListener(this);
       VentanaA.add(IngrObjetos);
       
       SalirA=new JButton("Salir");
       SalirA.setBounds(90,100,100,30);
       SalirA.addActionListener(this);
       VentanaA.add(SalirA);
       
        JLabel Bienvenido=new JLabel("Bienvenid@ !!!");
        Bienvenido.setBounds(10,10,220,20);
        VentanaA.add(Bienvenido);
        //Caracteristicas de la ventana
        VentanaA.setLayout(null);
        VentanaA.setTitle("Administrador");//Nombre de la ventana
        VentanaA.setSize(280,170);//Dimensiones de la ventana. Ancho y alto
        VentanaA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Permite que la ventana se pueda cerrar cuando le presionemos en la X
        VentanaA.setResizable(false);//Para que la ventana no se pueda reedimensionar
        VentanaA.setLocationRelativeTo(null);//permitir que la venana se despliegue en el centro de la pantalla
        VentanaA.setVisible(true);//Hacer visible la ventana*
    }
   
    public void VentanaAdmU()
    {
        crearbotonesAdminUsuario();
        
        creaBotonesRegresarSalir();

        MostrarU=new JLabel("No. Usuario");
        MostrarU.setBounds(165,110,100,20);
        VentanaAdmU.add(MostrarU);
        int aux=140;
        for(int i=0;i<listaUsuarios.getTamLista();i++)
        {
            MostrarU=new JLabel(""+(i+1));
            MostrarU.setBounds(165,aux,100,20);
            VentanaAdmU.add(MostrarU);
            aux=aux+20;
        }
        aux=140;
        MostrarU=new JLabel("Usuarios");
        MostrarU.setBounds(285,110,100,20);
        VentanaAdmU.add(MostrarU);
        for(int i=0;i<listaUsuarios.getTamLista();i++)
        {
            MostrarU=new JLabel(""+listaUsuarios.getUsuario(i).getAlias());
            MostrarU.setBounds(285,aux,100,20);
            VentanaAdmU.add(MostrarU);
            aux=aux+20;
        }
        caracteristicasVentana("Usuarios");
    }
    
    public void AltaU()
    {
        AceptarU=new JButton("Dar de Alta");
        AceptarU.setBounds(50,190,120,30);
        AceptarU.addActionListener(this);
        VentanaAltaU.add(AceptarU);
        setLabelsAndTextFieldsUser();
    }
    public void editaUsuario()
    {
        btnUpdateUser=new JButton("Actualiza Usuario");
        btnUpdateUser.setBounds(50,190,120,30);
        btnUpdateUser.addActionListener(this);
        VentanaAltaU.add(btnUpdateUser);
    }
    public void ConsultaUsuario(String name, String alias, String psw, String userType, boolean editar)
    {
        this.setLabelsAndTextFieldsUser();
        nombre.setText(name);
        nombreA.setText(alias);
        passW.setText(psw);
        tipoU.setText(userType);
       
        nombre.setEditable(editar);
        nombreA.setEditable(editar);
        passW.setEditable(editar);
        tipoU.setEditable(editar);
    }
    public void VentanaUs()//ventana para usuario
    {
        JLabel Bienvenido=new JLabel("Bienvenid@ !!!");
        Bienvenido.setBounds(10,10,220,20);
        VentanaU.add(Bienvenido);
        //Caracteristicas de la ventana
        VentanaU.setLayout(null);
        VentanaU.setTitle("Usuario");//Nombre de la ventana
        VentanaU.setSize(280,170);//Dimensiones de la ventana. Ancho y alto
        VentanaU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Permite que la ventana se pueda cerrar cuando le presionemos en la X
        VentanaU.setResizable(false);//Para que la ventana no se pueda reedimensionar
        VentanaU.setLocationRelativeTo(null);//permitir que la venana se despliegue en el centro de la pantalla
        VentanaU.setVisible(true);//Hacer visible la ventana*
    }
    /**
     * Ventanas para la administración de objetos...
     */
    public void nuevaVenatanaAdminObjetos()
    {
        creaBotonesObje();
        /**
         * Botones de Regresar y salir
         */
        creaBotonesRegresarSalir();
        caracteristicasVentana("Objetos");
    }
    /**
     * ActionListener
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==Aceptar)
        {
            Acceder();
        }
        ///Ventana Administrador
        if(e.getSource()==IngrU)
        {
            VentanaA.dispose();
            VentanaA.removeAll();
            VentanaA=new JFrame();
            VentanaAdmU();
        }
        if(e.getSource()==IngrObjetos)
        {
            VentanaA.dispose();
            VentanaA.removeAll();
            VentanaA=new JFrame();
            nuevaVenatanaAdminObjetos();
        }
        if(e.getSource()==SalirA)
        {
            VentanaA.dispose();
            VentanaA.removeAll();
            VentanaA=new JFrame();
            JOptionPane.showMessageDialog(null,"Hasta Luego!!");
            VentanaPrincipal();
            try {
                out.writeInt(5);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //////////Ventana Administrador (Usuarios) //////
        if(e.getSource()==SalirAU)
        {
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU=new JFrame();
            JOptionPane.showMessageDialog(null,"Hasta Luego!!");
            VentanaPrincipal();
            try {
                //enviar dato 5
                out.writeInt(5);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource()==RegresarAU)
        {
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU=new JFrame();
            VentanaAdmi();
        }
        if(e.getSource()==AltaU)
        {
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU=new JFrame();
            AltaU();
        }
        if(e.getSource()==BajaUsuario)
        {
            String Cnum=JOptionPane.showInputDialog(null,"Ingrese numero de Usuario:");
            int num=Integer.parseInt(Cnum);
            num=num-1;
            Usuario DeleteU=listaUsuarios.getUsuario(num);
            try {
                out.writeInt(2);
                out.flush();
                out.writeInt(0);
                out.flush();
                out.writeObject(DeleteU);
                out.flush();
                listaUsuarios.DeleteUsuario(num);
                JOptionPane.showMessageDialog(null,"Usuario Eliminado");
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if(e.getSource()==btnCambioU)
        {
            String numUsuario = JOptionPane.showInputDialog(null,"Ingrese numero de Usuario:");
            int num = Integer.parseInt(numUsuario)-1;
            editaUsuario = listaUsuarios.getUsuario(num);
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU = new JFrame();
            editaUsuario();
            ConsultaUsuario(editaUsuario.getNombre(), editaUsuario.getAlias(), editaUsuario.getPassword(), editaUsuario.getTipoUsuario(), true);
        }
        if(e.getSource()==btnConsultaU)
        {
            String numUsuario = JOptionPane.showInputDialog(null,"Ingrese numero de Usuario:");
            int num=Integer.parseInt(numUsuario)-1;
            Usuario consultaUsuario = listaUsuarios.getUsuario(num);
            try {
                out.writeInt(4); // Consultamos
                out.flush();
                out.writeInt(0); // Clase usuario
                out.flush();
                out.writeInt(num);
                out.flush();
                VentanaAdmU.dispose();
                VentanaAdmU.removeAll();
                
                VentanaAdmU=new JFrame();
                ConsultaUsuario(consultaUsuario.getNombre(), consultaUsuario.getAlias(), consultaUsuario.getPassword(), consultaUsuario.getTipoUsuario(), false);
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == btnUpdateUser){
            String NombU=nombre.getText();
            String NomA=nombreA.getText();
            String PassU=passW.getText();
            String TipU=tipoU.getText();
            boolean check=true;
            int id = editaUsuario.getId();
            if(TipU.equals("Administrador")||TipU.equals("Usuario"))
            {
                for(int i=0;i<listaUsuarios.getTamLista();i++)
                {
                    Usuario aux=listaUsuarios.getUsuario(i);
                    if(aux.getNombre().equals(NombU) && aux.getId() != editaUsuario.getId())
                    {
                        JOptionPane.showMessageDialog(null,"El nombre completo ya existe");
                        check=false;
                        break;
                    }
                    if(aux.getAlias().equals(NomA) && aux.getId() != editaUsuario.getId())
                    {
                        JOptionPane.showMessageDialog(null,"El usuario ya existe");
                        check=false;
                        break;
                    }
                }
                if(NomA.length()<5)
                {
                    JOptionPane.showMessageDialog(null,"El nombre del Alias es muy corto\nMinimo de 5 caracteres");
                    check=false;
                }
                else if(NomA.length()>20)
                {
                    JOptionPane.showMessageDialog(null,"El nombre del Alias es muy largo\nMaximo de 20 caracteres");
                    check=false;
                }
                if(check==true)
                {
                    Usuario validatedEditUser=new Usuario(id,TipU,NomA,PassU,NombU);
                    try {
                        out.writeInt(3);
                        out.flush();
                        out.writeInt(0);
                        out.flush();
                        out.writeObject(validatedEditUser);
                        out.flush();
                        listaUsuarios.sobreEscribeUsuario(id+1, validatedEditUser);
                    } catch (IOException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    JOptionPane.showMessageDialog(null,"Usuario Actualizadp");
                    VentanaAltaU.dispose();
                    VentanaAltaU.removeAll();
                    VentanaAltaU = new JFrame();
                    VentanaAdmU();
                }    
            }
            else
            {
                JOptionPane.showMessageDialog(null,"El tipo Usuario es incorrecto");
            }
        }
        if(e.getSource()==AceptarU)
        {
            String NombU=nombre.getText();
            String NomA=nombreA.getText();
            String PassU=passW.getText();
            String TipU=tipoU.getText();
            boolean check=true;
            int newId=(listaUsuarios.getUsuario(0).getId()+1);
            if(TipU.equals("Administrador")||TipU.equals("Usuario"))
            {
                for(int i=0;i<listaUsuarios.getTamLista();i++)
                {
                    Usuario aux=listaUsuarios.getUsuario(i);
                    if(aux.getNombre().equals(NombU))
                    {
                        JOptionPane.showMessageDialog(null,"El nombre completo ya existe");
                        check=false;
                        break;
                    }
                    if(aux.getAlias().equals(NomA))
                    {
                        JOptionPane.showMessageDialog(null,"El usuario ya existe");
                        check=false;
                        break;
                    }
                }
                if(NomA.length()<5)
                {
                    JOptionPane.showMessageDialog(null,"El nombre del Alias es muy corto\nMinimo de 5 caracteres");
                    check=false;
                }
                else if(NomA.length()>20)
                {
                    JOptionPane.showMessageDialog(null,"El nombre del Alias es muy largo\nMaximo de 20 caracteres");
                    check=false;
                }
                if(check==true)
                {
                    Usuario newUsuario=new Usuario(newId,TipU,NomA,PassU,NombU);
                    try {
                        out.writeInt(1);
                        out.flush();
                        out.writeInt(0);
                        out.flush();
                        out.writeObject(newUsuario);
                        out.flush();
                        listaUsuarios.AddUsuario(newUsuario);
                    } catch (IOException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    JOptionPane.showMessageDialog(null,"Usuario creado");
                    VentanaAltaU.dispose();
                    VentanaAltaU.removeAll();
                    VentanaAltaU = new JFrame();
                    VentanaAdmU();
                }    
            }
            else
            {
                JOptionPane.showMessageDialog(null,"El tipo Usuario es incorrecto");
            }
        }
        if(e.getSource()==RegresarU)
        {
            VentanaAltaU.dispose();
            VentanaAltaU.removeAll();
            VentanaAltaU=new JFrame();
            VentanaAdmU();
        }
        /////
    }
    
    private void creaBotonesRegresarSalir(){
        RegresarAU=new JButton("Regresar");
        RegresarAU.setBounds(165,420,100,30);
        RegresarAU.addActionListener(this);
        VentanaAdmU.add(RegresarAU);
        
        SalirAU=new JButton("Salir");
        SalirAU.setBounds(285,420,100,30);
        SalirAU.addActionListener(this);
        VentanaAdmU.add(SalirAU);
    }
    private void crearbotonesAdminUsuario(){
        AltaU=new JButton("Dar de Alta un Usuario");
        AltaU.setBounds(95,20,170,30);
        AltaU.addActionListener(this);
        VentanaAdmU.add(AltaU);
        
        BajaUsuario=new JButton("Dar de Baja un Uusario");
        BajaUsuario.setBounds(285,20,170,30);
        BajaUsuario.addActionListener(this);
        VentanaAdmU.add(BajaUsuario);
        
        btnCambioU=new JButton("Modificar un Usuario");
        btnCambioU.setBounds(95,70,170,30);
        btnCambioU.addActionListener(this);
        VentanaAdmU.add(btnCambioU);
        
        btnConsultaU=new JButton("Consultar un Usuario");
        btnConsultaU.setBounds(285,70,170,30);
        btnConsultaU.addActionListener(this);
        VentanaAdmU.add(btnConsultaU);
    }
    private void setLabelsAndTextFieldsUser(){
        text=new JLabel("Nombre Completo:");
        text.setBounds(20,20,150,30);
        VentanaAltaU.add(text);
        nombre=new JTextField();
        nombre.setBounds(180,20,150,30);
        VentanaAltaU.add(nombre);
        text=new JLabel("Alias:");
        text.setBounds(20,60,150,30);
        VentanaAltaU.add(text);
        
        nombreA=new JTextField();
        nombreA.setBounds(180,60,150,30);
        VentanaAltaU.add(nombreA);
        
        text=new JLabel("Contraseña:");
        text.setBounds(20,100,150,30);
        VentanaAltaU.add(text);
        
        passW=new JTextField();
        passW.setBounds(180,100,150,30);
        VentanaAltaU.add(passW);
        
        text=new JLabel("Tipo de Usuario:");
        text.setBounds(20,140,150,30);
        VentanaAltaU.add(text);
        
        tipoU = new JTextField();
        tipoU.setBounds(180,140,150,30);
        VentanaAltaU.add(tipoU);
        RegresarU=new JButton("Regresar");
        RegresarU.setBounds(185,190,120,30);
        RegresarU.addActionListener(this);
        VentanaAltaU.add(RegresarU);
        
        //Caracteristicas de la ventana
        VentanaAltaU.setLayout(null);
        VentanaAltaU.setTitle("Dar de Alta");//Nombre de la ventana
        VentanaAltaU.setSize(355,270);//Dimensiones de la ventana. Ancho y alto
        VentanaAltaU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Permite que la ventana se pueda cerrar cuando le presionemos en la X
        VentanaAltaU.setResizable(false);//Para que la ventana no se pueda reedimensionar
        VentanaAltaU.setLocationRelativeTo(null);//permitir que la venana se despliegue en el centro de la pantalla
        VentanaAltaU.setVisible(true);//Hacer visible la ventana*
    }
    private void creaBotonesObje(){
        /**
         * Izquierda
         */
        btnAlarma = new JButton("Administrar Alarmas");
        btnAlarma.setBounds(95,20,170,30);
        
        btnDismascota = new JButton("Administrar DisMasotas");
        btnDismascota.setBounds(95,70,170,30);
        
        btnLampara = new JButton("Administrar Lámparas");
        btnLampara.setBounds(95,120,170,30);
                
        btnRefrigerador = new JButton("Administrar Refrigeradores");
        btnRefrigerador.setBounds(95,170,170,30);
        /**
         * Derecha
         */
        btnCortinas = new JButton("Administrar Cortinas");
        btnCortinas.setBounds(285,20,170,30);
        
        btnIrrigador = new JButton("Administrar Irrigador");
        btnIrrigador.setBounds(285,70,170,30);
        
        btnLuminaria = new JButton("Administrar Luminaria");
        btnLuminaria.setBounds(285,120,170,30);
        
        btnTermostato = new JButton("Administrar Termostato");
        btnTermostato.setBounds(285,170,170,30);
        
        VentanaAdmU.add(btnIrrigador);
        VentanaAdmU.add(btnCortinas);
        VentanaAdmU.add(btnLuminaria);
        VentanaAdmU.add(btnTermostato);
        VentanaAdmU.add(btnDismascota);
        VentanaAdmU.add(btnAlarma);
        VentanaAdmU.add(btnLampara);
        VentanaAdmU.add(btnRefrigerador);
        
        /**
         * Añadiendo el actionListener
         */
        btnAlarma.addActionListener(this);
        btnCortinas.addActionListener(this);
        btnDismascota.addActionListener(this);
        btnIrrigador.addActionListener(this);
        btnLampara.addActionListener(this);
        btnRefrigerador.addActionListener(this);
        btnLuminaria.addActionListener(this);    
        btnTermostato.addActionListener(this);      
    }
    private void caracteristicasVentana(String titulo){
        //Caracteristicas de la ventana
        VentanaAdmU.setLayout(null);
        VentanaAdmU.setTitle(titulo);//Nombre de la ventana
        VentanaAdmU.setSize(550,500);//Dimensiones de la ventana. Ancho y alto
        VentanaAdmU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Permite que la ventana se pueda cerrar cuando le presionemos en la X
        VentanaAdmU.setResizable(false);//Para que la ventana no se pueda reedimensionar
        VentanaAdmU.setLocationRelativeTo(null);//permitir que la venana se despliegue en el centro de la pantalla
        VentanaAdmU.setVisible(true);//Hacer visible la ventana*
    }
}

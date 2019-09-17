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
    JFrame VentanaAdmObjetos = new JFrame();
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
        JButton RegresarAObj;        
        JButton SalirAObj;
        
        JButton btnRefrigerador;
        JButton btnAlarma;
        JButton btnCortinas;
        JButton btnDismascota;
        JButton btnIrrigador;
        JButton btnLampara;
        JButton btnLuminaria;
        JButton btnTermostato;
        
        /**
         * Ventana Administrador de Refris
         */
        
        JButton btnAltaRefri;
        JButton newRefri;
        JButton btnBajaRefri;
        JButton btnCambioRefri;
        JButton btnConsultaRefri;
        /**
         * Ventana Administrador de Cortinas
         */
        JButton btnAltaCortinas;
        JButton btnBajaCortina;
        JButton btnCambioCortina;
        JButton btnConsultaCortina;
        /**
         * Ventana Administrador de Termostatos
         */
        JButton btnAltaTermostato;
        JButton btnBajaTermostato;
        JButton btnCambioTermostato;
        JButton btnConsultaTermostato;
        /**
         * Ventana Administrador de Dispensadores de alimento
         */
        JButton btnAltaDisAlimento;
        JButton btnBajaDisAlimento;
        JButton btnCambioDisAlimento;
        JButton btnConsultaDisAlimento;
        Usuario editaUsuario;
        /**
         * Ventana Administrador de Irrigadores
         */
        JButton btnAltaIrrigador;
        JButton btnBajaIrrigador;
        JButton btnCambioIrrigador;
        JButton btnConsultaIrrigador;
        /**
         * Ventana Administrador de Alarmas
         */
        JButton btnAltaAlarma;
        JButton btnBajaAlarma;
        JButton btnCambioAlarma;
        JButton btnConsultaAlarma;
        /**
         * Ventana Administrador de Lamparas
         */
        JButton btnAltaLampara;
        JButton btnBajaLampara;
        JButton btnCambioLampara;
        JButton btnConsultaLampara;
        /**
         * Ventana Administrador de Luminarias
         */
        JButton btnAltaLuminaria;
        JButton btnBajaLuminaria;
        JButton btnCambioLuminaria;
        JButton btnConsultaLuminaria;
        ObjectOutputStream out;
        ObjectInputStream in;
    
    public static ListaUsuarios listaUsuarios;
    //ARREGLOS DE OBJETOS
            ArrayList <Refrigerador> listRefri;
            ArrayList <Cortinas> listCortinas;
            ArrayList <Termostato> listTermostato;
            ArrayList <DisMascota> listMascotas;
            ArrayList <Irrigador> listIrrigador;
            ArrayList <Alarma> listAlarma;
            ArrayList <Lampara> listLampara;
            ArrayList <Luminaria> listLuminaria;
    private JTextField tempCharola;
    private JTextField tempFrigo;
    private JTextField tempCentro;
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
            boolean verificar = in.readBoolean();
            String res = in.readUTF();
            JOptionPane.showMessageDialog(null,res);
            
            if(verificar==true)
            {
                Ventana.dispose();
                Ventana.removeAll();
                Ventana=new JFrame();
                //recibir usuario
                Usuario Urs = (Usuario)in.readObject();
                if(Urs.getTipoUsuario().equals("Administrador"))
                {
                    listaUsuarios=(ListaUsuarios)in.readObject();//recibir lista de usuarios
                    //recibir listas de objetos
                    listRefri=(ArrayList <Refrigerador>)in.readObject();
                    listCortinas=(ArrayList <Cortinas>)in.readObject();
                    listTermostato=(ArrayList <Termostato>)in.readObject();
                    listMascotas=(ArrayList <DisMascota>)in.readObject();
                    listIrrigador=(ArrayList <Irrigador>)in.readObject();
                    listAlarma=(ArrayList <Alarma>)in.readObject();
                    listLampara=(ArrayList <Lampara>)in.readObject();
                    listLuminaria=(ArrayList <Luminaria>)in.readObject();
                    //abrir vetana de administrador
                    VentanaAdmi();
                }
                else
                {
                    //recibir listas de objetos
                    listRefri=(ArrayList <Refrigerador>)in.readObject();
                    listCortinas=(ArrayList <Cortinas>)in.readObject();
                    listTermostato=(ArrayList <Termostato>)in.readObject();
                    listMascotas=(ArrayList <DisMascota>)in.readObject();
                    listIrrigador=(ArrayList <Irrigador>)in.readObject();
                    listAlarma=(ArrayList <Alarma>)in.readObject();
                    listLampara=(ArrayList <Lampara>)in.readObject();
                    listLuminaria=(ArrayList <Luminaria>)in.readObject();
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
        
        creaBotonesRegresarSalir(VentanaAdmU);

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
        caracteristicasVentana("Usuarios", VentanaAdmU);
    }
    
    public void labelsVentanasAdminObj(String noObj, String name, ArrayList list){
        MostrarU = new JLabel(noObj);
        MostrarU.setBounds(165,110,100,20);
        VentanaAdmObjetos.add(MostrarU);
        
        MostrarU = new JLabel(name);
        MostrarU.setBounds(285,110,100,20);
        VentanaAdmObjetos.add(MostrarU);
        
        int aux=140;
        for(int i=0;i<list.size();i++)
        {
            MostrarU = new JLabel(""+(i+1));
            MostrarU.setBounds(165,aux,100,20);
            VentanaAdmObjetos.add(MostrarU);
            aux=aux+20;
        }
        aux=140;
        
        for(int i=0;i<list.size();i++)
        {
            MostrarU=new JLabel(""+list.get(i));
            MostrarU.setBounds(285,aux,100,20);
            VentanaAdmObjetos.add(MostrarU);
            aux=aux+20;
        }
        
        caracteristicasVentana(name, VentanaAdmObjetos);
        
    }
    
    public void VentanaAdminRefri(){
        crearbotonesAdminRefris();
        
        creaBotonesRegresarSalirObjetos(VentanaAdmObjetos);

        labelsVentanasAdminObj("No. Refrigerador", "Refrigeradores", listRefri);
    }
    
    public void VentanaAdminAlarma(){
        crearbotonesAdminAlarmas();
        
        creaBotonesRegresarSalirObjetos(VentanaAdmObjetos);
        
        labelsVentanasAdminObj("No. Alarmas", "Alarmas", listAlarma);
    }
    
    public void VentanaAdminCortina(){
        crearbotonesAdminCortinas();
        
        creaBotonesRegresarSalirObjetos(VentanaAdmObjetos);

        labelsVentanasAdminObj("No. Cortinas", "Cortinas", listCortinas);
    }
    
    public void VentanaAdminTermostato(){
        crearbotonesAdminTermostato();
        
        creaBotonesRegresarSalirObjetos(VentanaAdmObjetos);

        labelsVentanasAdminObj("No. Termostatos", "Termostatos", listTermostato);
    }
    
    public void VentanaAdminDisMascota(){
        crearbotonesAdminDisAlimento();
        
        creaBotonesRegresarSalirObjetos(VentanaAdmObjetos);

        labelsVentanasAdminObj("No. Dispensador", "Dispensadores", listMascotas);
    }
    
    public void VentanaAdminIrrigador(){
        crearbotonesAdminIrrigadores();
        
        creaBotonesRegresarSalirObjetos(VentanaAdmObjetos);

        labelsVentanasAdminObj("No. Irrigador", "Irrigador", listIrrigador);
    }
    
    public void VentanaAdminLampara(){
        crearbotonesAdminLamparas();
        
        creaBotonesRegresarSalirObjetos(VentanaAdmObjetos);

        labelsVentanasAdminObj("No. Lampara", "Lampara", listLampara);
    }
    
    public void VentanaAdminIluminaria(){
        crearbotonesAdminLamparas();
        
        creaBotonesRegresarSalirObjetos(VentanaAdmObjetos);

        labelsVentanasAdminObj("No. Luminaria", "Luminaria", listLuminaria);
    }
    
    public void altaRefri()
    {
        newRefri=new JButton("Dar de Alta Refrigerador");
        newRefri.setBounds(50,190,120,30);
        newRefri.addActionListener(this);
        VentanaAltaU.add(newRefri);
        setLabelsAndTextFieldsRefris();
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
        creaBotonesRegresarSalir(VentanaAdmU);
        caracteristicasVentana("Objetos", VentanaAdmU);
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
        /**
         * Administradores de Objetos
         */
        if(e.getSource()==btnRefrigerador)
        {
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU=new JFrame();
            VentanaAdmObjetos.dispose();
            VentanaAdmObjetos.removeAll();
            VentanaAdmObjetos=new JFrame();
            VentanaAdminRefri();
        }
        if(e.getSource()==btnCortinas)
        {
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU=new JFrame();
            VentanaAdmObjetos.dispose();
            VentanaAdmObjetos.removeAll();
            VentanaAdmObjetos=new JFrame();
            VentanaAdminCortina();
        }
        if(e.getSource()==btnIrrigador)
        {
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU=new JFrame();
            VentanaAdmObjetos.dispose();
            VentanaAdmObjetos.removeAll();
            VentanaAdmObjetos=new JFrame();
            VentanaAdminIrrigador();
        }
        if(e.getSource()==btnLuminaria)
        {
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU=new JFrame();
            VentanaAdmObjetos.dispose();
            VentanaAdmObjetos.removeAll();
            VentanaAdmObjetos=new JFrame();
            VentanaAdminIluminaria();
        }
        
        if(e.getSource()==btnTermostato)
        {
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU=new JFrame();
            VentanaAdmObjetos.dispose();
            VentanaAdmObjetos.removeAll();
            VentanaAdmObjetos=new JFrame();
            VentanaAdminTermostato();
        }
        if(e.getSource()==btnLampara)
        {
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU=new JFrame();
            VentanaAdmObjetos.dispose();
            VentanaAdmObjetos.removeAll();
            VentanaAdmObjetos=new JFrame();
            VentanaAdminLampara();
        }
        if(e.getSource()==btnDismascota)
        {
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU=new JFrame();
            VentanaAdmObjetos.dispose();
            VentanaAdmObjetos.removeAll();
            VentanaAdmObjetos=new JFrame();
            VentanaAdminDisMascota();
        }
        if(e.getSource()==btnAlarma)
        {
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU=new JFrame();
            VentanaAdmObjetos.dispose();
            VentanaAdmObjetos.removeAll();
            VentanaAdmObjetos=new JFrame();
            VentanaAdminAlarma();
        }
         /**
         * Administradores de Objetos FIN
         */
         /**
          * CRUD Refrigeradores 
          * JButton btnAltaRefri;
        JButton btnBajaRefri;
        JButton btnCambioRefri;
        JButton btnConsultaRefri;
          */
         if(e.getSource() == btnAltaRefri)
        {
            VentanaAdmObjetos.dispose();
            VentanaAdmObjetos.removeAll();
            VentanaAdmObjetos=new JFrame();
            altaRefri();
        }
         if(e.getSource()==newRefri)
        {
            float temFrigo = Float.parseFloat(tempFrigo.getText());
            float temCentro = Float.parseFloat(tempCentro.getText());
            float temCharola = Float.parseFloat(tempCharola.getText());
            boolean check = true;
            int newId=(listRefri.get(listRefri.size()-1).getId()+1);
            
            if((int)temFrigo<-5 && (int)temFrigo>3){
                JOptionPane.showMessageDialog(null,"Rango válido -5 y 3 grados");
                check=false;
            }else if((int)temCentro < 0 && (int)temCentro > 10){
                JOptionPane.showMessageDialog(null,"Rango válido 0 y 10 grados");
                check=false;
            }else if((int)temCharola < 5 && (int)temCharola > 10){
                JOptionPane.showMessageDialog(null,"Valor mínimo 5 y máximo de temCentro");
                check=false;
            }
            if(check){
                Refrigerador newRefrigerador = new Refrigerador(newId,temFrigo,temCentro,temCharola);
                try {
                    out.writeInt(1);
                    out.flush();
                    out.writeInt(1);
                    out.flush();
                    out.writeObject(newRefrigerador);
                    out.flush();
                    listRefri.add(newRefrigerador);
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null,"Refrigerador Creado creado");
                VentanaAltaU.dispose();
                VentanaAltaU.removeAll();
                VentanaAltaU = new JFrame();
                VentanaAdminRefri();
            }    
        }
         /**
          * CRUD Refrigeradores 
          */
        if(e.getSource()==RegresarAObj) {
            VentanaAdmObjetos.dispose();
            VentanaAdmObjetos.removeAll();
            VentanaAdmObjetos=new JFrame();
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU=new JFrame();
            nuevaVenatanaAdminObjetos();
        }
        if(e.getSource()==SalirA) {
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
        if(e.getSource()==SalirAU) {
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
        if(e.getSource()==RegresarAU) {
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU=new JFrame();
            VentanaAdmi();
        }
        if(e.getSource()==AltaU) {
            VentanaAdmU.dispose();
            VentanaAdmU.removeAll();
            VentanaAdmU=new JFrame();
            AltaU();
        }
        if(e.getSource()==BajaUsuario) {
            String Cnum = JOptionPane.showInputDialog(null,"Ingrese numero de Usuario:");
            int num = Integer.parseInt(Cnum);
            num = num-1;
            Usuario DeleteU = listaUsuarios.getUsuario(num);
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
    
    private void creaBotonesRegresarSalirObjetos(JFrame ventana){
        RegresarAObj=new JButton("Regresar");
        RegresarAObj.setBounds(165,420,100,30);
        RegresarAObj.addActionListener(this);
        ventana.add(RegresarAObj);
        
        SalirAObj=new JButton("Salir");
        SalirAObj.setBounds(285,420,100,30);
        SalirAObj.addActionListener(this);
        ventana.add(SalirAObj);
    }
    private void creaBotonesRegresarSalir(JFrame ventana){
        RegresarAU=new JButton("Regresar");
        RegresarAU.setBounds(165,420,100,30);
        RegresarAU.addActionListener(this);
        ventana.add(RegresarAU);
        
        SalirAU=new JButton("Salir");
        SalirAU.setBounds(285,420,100,30);
        SalirAU.addActionListener(this);
        ventana.add(SalirAU);
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
    
    private void setLabelsAndTextFieldsRefris(){
        text=new JLabel("Temperatura Frigo:");
        text.setBounds(20,20,150,30);
        VentanaAltaU.add(text);
        tempFrigo = new JTextField();
        tempFrigo.setBounds(180,20,150,30);
        VentanaAltaU.add(tempFrigo);
        
        text=new JLabel("Temperatura Centro:");
        text.setBounds(20,60,150,30);
        VentanaAltaU.add(text);
        tempCentro = new JTextField();
        tempCentro.setBounds(180,60,150,30);
        VentanaAltaU.add(tempCentro);
        
        text=new JLabel("Temperatura Charola:");
        text.setBounds(20,100,150,30);
        VentanaAltaU.add(text);
        tempCharola = new JTextField();
        tempCharola.setBounds(180,100,150,30);
        VentanaAltaU.add(tempCharola);
        
        RegresarAObj=new JButton("Regresar");
        RegresarAObj.setBounds(185,190,120,30);
        RegresarAObj.addActionListener(this);
        VentanaAltaU.add(RegresarAObj);
        
        caracteristicasVentanaRegistro("Dar de alta Refrigerador", VentanaAltaU);
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
        caracteristicasVentanaRegistro("Dar de alta", VentanaAltaU);
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
    
    private void caracteristicasVentanaRegistro(String titulo, JFrame ventana){
        //Caracteristicas de la ventana
        ventana.setLayout(null);
        ventana.setTitle(titulo);//Nombre de la ventana
        ventana.setSize(355,270);//Dimensiones de la ventana. Ancho y alto
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Permite que la ventana se pueda cerrar cuando le presionemos en la X
        ventana.setResizable(false);//Para que la ventana no se pueda reedimensionar
        ventana.setLocationRelativeTo(null);//permitir que la venana se despliegue en el centro de la pantalla
        ventana.setVisible(true);//Hacer visible la ventana*
    }
    
    private void caracteristicasVentana(String titulo, JFrame ventana){
        //Caracteristicas de la ventana
        ventana.setLayout(null);
        ventana.setTitle(titulo);//Nombre de la ventana
        ventana.setSize(550,500);//Dimensiones de la ventana. Ancho y alto
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Permite que la ventana se pueda cerrar cuando le presionemos en la X
        ventana.setResizable(false);//Para que la ventana no se pueda reedimensionar
        ventana.setLocationRelativeTo(null);//permitir que la venana se despliegue en el centro de la pantalla
        ventana.setVisible(true);//Hacer visible la ventana*
    }
    
    private void crearbotonesAdminRefris(){
        btnAltaRefri=new JButton("Dar de Alta un Refrigerador");
        btnAltaRefri.setBounds(95,20,170,30);
        btnAltaRefri.addActionListener(this);
        VentanaAdmObjetos.add(btnAltaRefri);
        
        btnBajaRefri=new JButton("Dar de Baja un Refrigerador");
        btnBajaRefri.setBounds(285,20,170,30);
        btnBajaRefri.addActionListener(this);
        VentanaAdmObjetos.add(btnBajaRefri);
        
        btnCambioRefri=new JButton("Modificar un Refrigerador");
        btnCambioRefri.setBounds(95,70,170,30);
        btnCambioRefri.addActionListener(this);
        VentanaAdmObjetos.add(btnCambioRefri);
        
        btnConsultaRefri=new JButton("Consultar un Refrigerador");
        btnConsultaRefri.setBounds(285,70,170,30);
        btnConsultaRefri.addActionListener(this);
        VentanaAdmObjetos.add(btnConsultaRefri);
    }
    
    private void crearbotonesAdminCortinas(){
        btnAltaCortinas=new JButton("Dar de Alta una Cortina");
        btnAltaCortinas.setBounds(95,20,170,30);
        btnAltaCortinas.addActionListener(this);
        VentanaAdmObjetos.add(btnAltaCortinas);
        
        btnBajaCortina=new JButton("Dar de Baja una Cortina");
        btnBajaCortina.setBounds(285,20,170,30);
        btnBajaCortina.addActionListener(this);
        VentanaAdmObjetos.add(btnBajaCortina);
        
        btnCambioCortina=new JButton("Modificar una Cortina");
        btnCambioCortina.setBounds(95,70,170,30);
        btnCambioCortina.addActionListener(this);
        VentanaAdmObjetos.add(btnCambioCortina);
        
        btnConsultaCortina=new JButton("Consultar una Cortina");
        btnConsultaCortina.setBounds(285,70,170,30);
        btnConsultaCortina.addActionListener(this);
        VentanaAdmObjetos.add(btnConsultaCortina);
    }
    
    private void crearbotonesAdminTermostato(){
        btnAltaTermostato=new JButton("Dar de Alta un Termostato");
        btnAltaTermostato.setBounds(95,20,170,30);
        btnAltaTermostato.addActionListener(this);
        VentanaAdmObjetos.add(btnAltaTermostato);
        
        btnBajaTermostato=new JButton("Dar de Baja un Termostato");
        btnBajaTermostato.setBounds(285,20,170,30);
        btnBajaTermostato.addActionListener(this);
        VentanaAdmObjetos.add(btnBajaTermostato);
        
        btnCambioTermostato=new JButton("Modificar un Termostato");
        btnCambioTermostato.setBounds(95,70,170,30);
        btnCambioTermostato.addActionListener(this);
        VentanaAdmObjetos.add(btnCambioTermostato);
        
        btnConsultaTermostato=new JButton("Consultar un Termostato");
        btnConsultaTermostato.setBounds(285,70,170,30);
        btnConsultaTermostato.addActionListener(this);
        VentanaAdmObjetos.add(btnConsultaTermostato);
    }
    
    private void crearbotonesAdminDisAlimento(){
        btnAltaDisAlimento=new JButton("Dar de Alta un Dispensador de Alimento");
        btnAltaDisAlimento.setBounds(95,20,170,30);
        btnAltaDisAlimento.addActionListener(this);
        VentanaAdmObjetos.add(btnAltaDisAlimento);
        
        btnBajaDisAlimento=new JButton("Dar de Baja un Dispensador de Alimento");
        btnBajaDisAlimento.setBounds(285,20,170,30);
        btnBajaDisAlimento.addActionListener(this);
        VentanaAdmObjetos.add(btnBajaDisAlimento);
        
        btnCambioDisAlimento=new JButton("Modificar un Dispensador de Alimento");
        btnCambioDisAlimento.setBounds(95,70,170,30);
        btnCambioDisAlimento.addActionListener(this);
        VentanaAdmObjetos.add(btnCambioDisAlimento);
        
        btnConsultaDisAlimento=new JButton("Consultar un Dispensador de Alimento");
        btnConsultaDisAlimento.setBounds(285,70,170,30);
        btnConsultaDisAlimento.addActionListener(this);
        VentanaAdmObjetos.add(btnConsultaDisAlimento);
    }
    
    private void crearbotonesAdminIrrigadores(){
        btnAltaIrrigador=new JButton("Dar de Alta un Irrigador");
        btnAltaIrrigador.setBounds(95,20,170,30);
        btnAltaIrrigador.addActionListener(this);
        VentanaAdmObjetos.add(btnAltaIrrigador);
        
        btnBajaIrrigador=new JButton("Dar de Baja un Irrigador");
        btnBajaIrrigador.setBounds(285,20,170,30);
        btnBajaIrrigador.addActionListener(this);
        VentanaAdmObjetos.add(btnBajaIrrigador);
        
        btnCambioIrrigador=new JButton("Modificar un Irrigador");
        btnCambioIrrigador.setBounds(95,70,170,30);
        btnCambioIrrigador.addActionListener(this);
        VentanaAdmObjetos.add(btnCambioIrrigador);
        
        btnConsultaIrrigador=new JButton("Consultar un Irrigador");
        btnConsultaIrrigador.setBounds(285,70,170,30);
        btnConsultaIrrigador.addActionListener(this);
        VentanaAdmObjetos.add(btnConsultaIrrigador);
    }
    
    private void crearbotonesAdminAlarmas(){
        btnAltaAlarma=new JButton("Dar de Alta una Alarma");
        btnAltaAlarma.setBounds(95,20,170,30);
        btnAltaAlarma.addActionListener(this);
        VentanaAdmObjetos.add(btnAltaAlarma);
        
        btnBajaAlarma=new JButton("Dar de Baja una Alarma");
        btnBajaAlarma.setBounds(285,20,170,30);
        btnBajaAlarma.addActionListener(this);
        VentanaAdmObjetos.add(btnBajaAlarma);
        
        btnCambioAlarma=new JButton("Modificar una Alarma");
        btnCambioAlarma.setBounds(95,70,170,30);
        btnCambioAlarma.addActionListener(this);
        VentanaAdmObjetos.add(btnCambioAlarma);
        
        btnConsultaAlarma=new JButton("Consultar una Alarma");
        btnConsultaAlarma.setBounds(285,70,170,30);
        btnConsultaAlarma.addActionListener(this);
        VentanaAdmObjetos.add(btnConsultaAlarma);
    }
    
    private void crearbotonesAdminLuminarias(){
        btnAltaLuminaria=new JButton("Dar de Alta una Luminaria");
        btnAltaLuminaria.setBounds(95,20,170,30);
        btnAltaLuminaria.addActionListener(this);
        VentanaAdmObjetos.add(btnAltaLuminaria);
        
        btnBajaLuminaria=new JButton("Dar de Baja una Luminaria");
        btnBajaLuminaria.setBounds(285,20,170,30);
        btnBajaLuminaria.addActionListener(this);
        VentanaAdmObjetos.add(btnBajaLuminaria);
        
        btnCambioLuminaria=new JButton("Modificar una Luminaria");
        btnCambioLuminaria.setBounds(95,70,170,30);
        btnCambioLuminaria.addActionListener(this);
        VentanaAdmObjetos.add(btnCambioLuminaria);
        
        btnConsultaLuminaria=new JButton("Consultar una Luminaria");
        btnConsultaLuminaria.setBounds(285,70,170,30);
        btnConsultaLuminaria.addActionListener(this);
        VentanaAdmObjetos.add(btnConsultaLuminaria);
    }
    
    private void crearbotonesAdminLamparas(){
        btnAltaLampara=new JButton("Dar de Alta una Lampara");
        btnAltaLampara.setBounds(95,20,170,30);
        btnAltaLampara.addActionListener(this);
        VentanaAdmObjetos.add(btnAltaLampara);
        
        btnBajaLampara=new JButton("Dar de Baja una Lampara");
        btnBajaLampara.setBounds(285,20,170,30);
        btnBajaLampara.addActionListener(this);
        VentanaAdmObjetos.add(btnBajaLampara);
        
        btnCambioLampara=new JButton("Modificar una Lampara");
        btnCambioLampara.setBounds(95,70,170,30);
        btnCambioLampara.addActionListener(this);
        VentanaAdmObjetos.add(btnCambioLampara);
        
        btnConsultaLampara=new JButton("Consultar una Lampara");
        btnConsultaLampara.setBounds(285,70,170,30);
        btnConsultaLampara.addActionListener(this);
        VentanaAdmObjetos.add(btnConsultaLampara);
    }
}

package servidor;

import java.io.*;
import java.util.*;


public class Usuario implements Serializable{

    private int TipoClase=0;
    private int id;
    private String TipoUsuario;
    private String Alias;
    private String password;
    private String nombre;
    
    public Usuario(){
        System.out.println("Creando usuario vacio");
    }
    
    Usuario(int ID,String TipoUSUARIO,String ALIAS,String PASSWORD,String NOMBRE){
        System.out.println("Creando usuario\nTipo de Usuario: "+TipoUSUARIO+"\nAlias: "+ALIAS+"\nNombre Completo: "+NOMBRE+"\nContraseña:"+PASSWORD);
        id=ID;
        TipoUsuario=TipoUSUARIO;
        Alias=ALIAS;
        password=PASSWORD;
        nombre=NOMBRE;
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

    public String getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(String usuario) {
        this.TipoUsuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getAlias()
    {
        return Alias;
    }
    public void setAlias(String nameAlias)
    {
        this.Alias=nameAlias;
    }
    
    public void writeExternal(ObjectOutput out) throws IOException{
        System.out.println("Usuario.writeExternal");
        //Explicitamente indicamos cuales atributos se almacenan
        out.writeObject(Alias);
    }
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
        System.out.println("Usuario.readExternal");
        //Explicitamente indicamos cuales atributos vamos a recuperar
        Alias = (String) in.readObject();
    }
    public void muestraUsuario(){
        String cad = "Usuario: " + Alias + " Password: ";
        if(password == null) cad = cad + "No disponible";
        else cad = cad + password;
        System.out.println(cad);
    }
}

class ListaUsuarios implements Serializable{
    private LinkedList lista = new LinkedList();
    int valor;
    ListaUsuarios(int []id,String[] TipoUsuario,String[]Alias,String[] Passwords,String []Nombre){
        for(int i = 0;i<TipoUsuario.length;i++){
            lista.add(new Usuario(id[i],TipoUsuario[i],Alias[i],Passwords[i],Nombre[i]));
        }
    }
    
    public void muestraUsuario(){
        ListIterator li = lista.listIterator();
        Usuario u;
        while(li.hasNext()){
            u = (Usuario)li.next();
            u.muestraUsuario();
        }
    }
    public int getTamLista()
    {
        ListIterator li = lista.listIterator();
        return lista.size();
    }
    public Usuario getUsuario(int i)
    {
        ListIterator li = lista.listIterator();
        return (Usuario)lista.get(i);
    }
    public void AddUsuario(Usuario u)
    {
        ListIterator li = lista.listIterator();
        li.add(u);
        return;
    }
    public void DeleteUsuario(int i)
    {
        lista.remove(i);
        return;
    }
}

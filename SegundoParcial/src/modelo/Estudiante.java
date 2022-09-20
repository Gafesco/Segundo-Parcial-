/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author HP
 */
public class Estudiante extends Persona {
    
    private int id;
    private String carnet;
    Conexion cn;
    public TableModel leer;

    public Estudiante(int id, String carnet) {
        this.id = id;
        this.carnet = carnet;
    }
        public Estudiante() {}
    public Estudiante(int id, String carnet, String nombres, String apellidos, String direccion, String telefono, String genero, String email, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, genero, email, fecha_nacimiento);
        this.id = id;
        this.carnet = carnet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }
    
        public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel ();
        
        try{
        cn = new Conexion();
        cn.abrir_conexion();
        String query;
        query = "Select id_estudiante as id, carnet, nombres, apellidos, direccion, telefono, genero, email, fecha_nacimiento from estudiantes;";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        
        String encabezado[] = {"id","Carnet","Nombres","Apellidos","Direccion","Telefono","Genero" ,"Nacimiento"};
        tabla.setColumnIdentifiers(encabezado);
        
        String datos[] = new String[8];
        
        while (consulta.next()){
            datos[0] = consulta.getString("id");
            datos[1] = consulta.getString("carnet");
            datos[2] = consulta.getString("nombres");
            datos[3] = consulta.getString("apellidos");
            datos[4] = consulta.getString("direccion");
            datos[5] = consulta.getString("telefono");
            datos[6] = consulta.getString("genero");
            datos[7] = consulta.getString("fecha_nacimiento");
            tabla.addRow(datos);

        }
        cn.cerrar_conexion();
        
        } catch (SQLException ex){
                  cn.cerrar_conexion();
            System.out.println("Error: " + ex.getMessage());
            
        }
        return tabla;
        
    }
        @Override
    public void agregar (){
try { 
    PreparedStatement parametro;
    String query = "INSERT INTO estudiantes(carnet,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento) VALUES (?,?,?,?,?,?,?,?);";
    cn = new Conexion();
    cn.abrir_conexion();
    parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
    
    parametro.setString(1, getCarnet());
    parametro.setString(2, getNombres());
    parametro.setString(3, getApellidos());
    parametro.setString(4, getDireccion());
    parametro.setString(5, getTelefono());
    parametro.setString(6, getGenero());
    parametro.setString(7, getEmail());
    parametro.setString(8, getFecha_nacimiento());
    
    
    int executar = parametro.executeUpdate();
    cn.cerrar_conexion();
    JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Ingresado","Agregar", JOptionPane.INFORMATION_MESSAGE );
    
    
} catch(HeadlessException | SQLException ex){
    System.out.println("Error..."+ ex.getMessage());
    
    
}
    }
    
        @Override
    public void modificar(){

try { 
    PreparedStatement parametro;
    String query = "update estudiantes set carnet=?,nombres=?,apellidos=?,direccion=?,telefono=?,genero=?, email=?, fecha_nacimiento=? where id_estudiante =?;";
    cn = new Conexion();
    cn.abrir_conexion();
    parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
    parametro.setString(1, getCarnet());
    parametro.setString(2, getNombres());
    parametro.setString(3, getApellidos());
    parametro.setString(4, getDireccion());
    parametro.setString(5, getTelefono());
    parametro.setString(6, getGenero());
    parametro.setString(7, getEmail());
    parametro.setString(8, getFecha_nacimiento());
    parametro.setInt(9, getId());
    int executar = parametro.executeUpdate();
    cn.cerrar_conexion();
    JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registros Modificados","Agregar", JOptionPane.INFORMATION_MESSAGE );
    
    
} catch(HeadlessException | SQLException ex){
    System.out.println("Error..."+ ex.getMessage());
    
    
}
    
    }
    
        @Override
    public void eliminar(){
            

try { 
    PreparedStatement parametro;
    String query = "delete from estudiantes  where id_estudiante =?;";
    cn = new Conexion();
    cn.abrir_conexion();
    parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);

    parametro.setInt(1, getId());
    int executar = parametro.executeUpdate();
    cn.cerrar_conexion();
    JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registros Eliminados","Agregar", JOptionPane.INFORMATION_MESSAGE );
    
    
} catch(HeadlessException | SQLException ex){
    System.out.println("Error..."+ ex.getMessage());
    
    
}
    
    }
}


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author henri
 */
public class Conexao {
    
    public static void main(String[] args) throws SQLException{
        
        try{
            Class<?> forName = Class.forName("com.mysql.jdbc.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/gerenciadorAgua", "root", "Penelope_11");
            ResultSet rsUsuario = conexao.createStatement().executeQuery("SELECT * FROM USUARIO");
            while(rsUsuario.next()){
                System.out.println("nome: " + rsUsuario.getString("nome"));
            }
        }catch(ClassNotFoundException ex){
            System.out.println("Driver do banco de dados não localizado");
        }catch(SQLException ex){
            System.out.println("Erro ao acessar o banco:" + ex.getMessage());
        }
        
    }
    
    
    
}

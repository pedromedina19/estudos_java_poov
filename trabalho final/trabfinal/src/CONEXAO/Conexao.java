
package CONEXAO;


import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao 
{
    //CONECTION E UMA CLASSE PARA QUE TODOS ELEMENTOS TENHA UMA CONEXÃO BEM SUCEDIDA
    public static Connection getConexao() throws SQLException
    {
        Connection conexao = null;
        try
        {
            //USA UM GERENCIADOR DE DRIVES,GERENTE MOTORISTA,PRA CRIAR A CONEXÃO
            
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/trabfinal","root","@190302@");
            
            System.out.print("\nConectado ao servidor!!");
        }
        catch(SQLException e)
        {
            System.out.print("\nErro ao conectar ao servidor: "+e.toString()+" !\n\n");
            JOptionPane.showMessageDialog(null,"Erro ao conectar o servidor!\nVerifique sua conexão!");
        }
        
        return(conexao);
    }
}
//PEDRO MEDINA

package Conexao;

import Principal.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexao {
    //NÃO POSSUI ATRIBUTOS, APENAS UM MÉTODDO QUE REALIZA A CONEXÃO
    
    //O MÉTODO GETCONEXÃO() REALIZA A CONEXÃO COM O BANCO
    //CONECTION E UMA CLASSE PARA QUE TODOS ELEMENTOS TENHA UMA CONEXÃO BEM SUCEDIDA
    public static Connection getConexao() throws SQLException
    {
        Connection conexao = null;
        try
        {
            //USA UM GERENCIADOR DE DRIVES,GERENTE MOTORISTA,PRA CRIAR A CONEXÃO

            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/p3poov","root","@190302@");

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

//PEDRO MEDINA

package Principal;

import Conexao.Conexao;
import Interfaces.Menu;
import java.sql.Connection;
import java.sql.SQLException;

public class Principal {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        Menu menu = new Menu();
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        
        
        /*try {
            Connection con = Conexao.getConexao();
        } catch (SQLException e1) {
            System.out.print("\n Erro SQLException: "+e1.toString());
        }*/
    }
    
}


package Principal;

import PedroC.Cliente;
import DAOS.DAOCLIENTE;
import INTERFACES.PRINCIPAL;
import java.sql.SQLException;
import CONEXAO.Conexao;


public class Principal {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        
        
        PRINCIPAL tela = new PRINCIPAL();
        tela.setTitle("FÁBRICA DE CHOCOLATES");
        tela.setVisible(true);
        
        
        
        
        
        
        
    }
    
}

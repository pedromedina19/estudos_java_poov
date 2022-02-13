
package DAOS;
import PedroC.Chocolate_Pedido;
import CONEXAO.Conexao;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class DAOCHOCOLATE_PEDIDO {
        Connection connection;

    //AUTOMATICAMENTE VAI TER A CONEXÃO PRONTA NA HORA QUE CRIAR UM DAO
    //CONSTRUTOR!
        public DAOCHOCOLATE_PEDIDO() throws SQLException, ClassNotFoundException {
        this.connection = Conexao.getConexao();
    }
        
        
      public void INSERIRCHOCOPEDIDO(Chocolate_Pedido novochocopedido) {

        String sql = "insert into CHOCOLATE_PEDIDO(quantidade,fk_id_pedido,fk_id_chocolate) values(?,?,?)";
        //CLAUSULA(ALGO QUE VC QUER FAZER NO BANCO,REQUISIÇÃO)
        //PREPARED STATEMENT E UMA CLAUSULA PREPARADA

        try {

            PreparedStatement estado =connection.prepareStatement(sql);
            //VAI DAR CERTO,E O ESTADO JA TA PRONTO E COM ISSO A OPERAÇÃO COM O BANCO VAI DAR CERTO 
            estado.setInt(1,novochocopedido.getQuantidade());
            estado.setInt(2,novochocopedido.getFk_id_pedido());
            estado.setString(3,novochocopedido.getFk_id_chocolate());
            estado.execute();
            estado.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"NÃO FOI POSSIVEL ADICIONAR O PEDIDO DE CHOCOLATE\nTENTE NOVAMENTE!");
                System.out.println("ERRO: "+e.toString());
        }
    }     
      
      
      public void UPTADE(Chocolate_Pedido novochocopedido){
        String sql = "update  CHOCOLATE_PEDIDO set quantidade=?,fk_id_pedido=?,fk_id_chocolate=? where id_chocolate_pedido=?";
        PreparedStatement estado;
        try {
            //VAI DAR CERTO,E O ESTADO JA TA PRONTO E COM ISSO A OPERAÇÃO COM O BANCO VAI DAR CERTO
            estado =connection.prepareStatement(sql);
            estado.setInt(1,novochocopedido.getQuantidade());
            estado.setInt(2,novochocopedido.getFk_id_pedido());
            estado.setString(3,novochocopedido.getFk_id_chocolate());
            estado.setInt(4,novochocopedido.getId_chocolate_pedido());
    
            estado.execute();
            estado.close();
        }catch (SQLException e) {
                System.out.println("ERRO: "+e.toString());
        }
    }
        
     
      public void REMOVERCHOCO_PEDIDO (int IdP)
    {
        try
        {
            try (PreparedStatement es = this.connection.prepareStatement("delete from CHOCOLATE_PEDIDO where (id_chocolate_pedido =?)")) 
            {
                es.setInt(1,IdP);
                es.execute();
                es.close();
            }
           
        }
        catch (HeadlessException | SQLException e)
        {
            System.out.println("Erro: \n" +e+"\n");
            
        }
    }
          
      
       public Chocolate_Pedido RETORNAR(int id_chocolate_pedido){
        try {            
            String sql = "select * from CHOCOLATE_PEDIDO where id_chocolate_pedido="+id_chocolate_pedido;
            System.out.println(sql);
            try (Statement statement = this.connection.createStatement()) {
                
                ResultSet resultSet = statement.executeQuery(sql);
                resultSet.next();
                Chocolate_Pedido choco_pedido = new Chocolate_Pedido(resultSet.getInt("id_chocolate_pedido"),resultSet.getInt("quantidade"),resultSet.getInt("fk_id_pedido"),resultSet.getString("fk_id_chocolate"));
                return (choco_pedido);
            }
        } catch (SQLException e) {
                    System.out.println("ERRO: "+e.toString());
            return (null);
        }
    }
       
       
        public ArrayList<Chocolate_Pedido> TODOSOSPEDIDOSDECHOCO(){
        ArrayList<Chocolate_Pedido> choco_pedidos = new ArrayList();
        try {            
            String sql = "SELECT * FROM CHOCOLATE_PEDIDO";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                     Chocolate_Pedido choco_pedido = new Chocolate_Pedido(resultSet.getInt("id_chocolate_pedido"),resultSet.getInt("quantidade"),resultSet.getInt("fk_id_pedido"),resultSet.getString("fk_id_chocolate"));
                     choco_pedidos.add(choco_pedido);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (choco_pedidos);
    }
     
       
    public ArrayList<Chocolate_Pedido> TODOSOSPEDIDOSDESSEPEDIDO(int i){
        ArrayList<Chocolate_Pedido> choco_pedidos = new ArrayList();
        try {            
            String sql = "SELECT * FROM  CHOCOLATE_PEDIDO where fk_id_pedido="+i;
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                     Chocolate_Pedido choco_pedido = new Chocolate_Pedido(resultSet.getInt("id_chocolate_pedido"),resultSet.getInt("quantidade"),resultSet.getInt("fk_id_pedido"),resultSet.getString("fk_id_chocolate"));
                     choco_pedidos.add(choco_pedido);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (choco_pedidos);
    }  
    
    
    
    public int MAIORIDCHOCOPEDIDO() throws SQLException
    {
        int idmaximo=0;
        
        PreparedStatement instrucao;
        
        ResultSet res;
        
        
        String sql="select MAX(id_chocolate_pedido) from CHOCOLATE_PEDIDO";
        
        try
        {
            instrucao=this.connection.prepareStatement(sql);
            res=instrucao.executeQuery();
            
            while(res.next())
            {
                idmaximo=res.getInt("MAX(id_chocolate_pedido)");
                
            }
            
        }
        catch(SQLException e)
        {
            System.out.println("ERRO: "+e.toString());
        }
        
        
        
        
        
        return idmaximo;
    }   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     
        
}

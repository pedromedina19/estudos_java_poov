
package DAOS;

import PedroC.Chocolate;
import CONEXAO.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class DAOCHOCO {
    
     Connection connection;

    //AUTOMATICAMENTE VAI TER A CONEXÃO PRONTA NA HORA QUE CRIAR UM DAO
    //CONSTRUTOR!
    public DAOCHOCO() throws SQLException, ClassNotFoundException {
        this.connection = Conexao.getConexao();
    }
    
     public void INSERIRCHOCO(Chocolate choco) {

        
        
        String sql = "insert into CHOCOLATE(id_chocolate,peso,valorPorKg,tipoDoChocolate,valorDeVenda,temperaturaIdeal,qtdeDeLeite) values(?,?,?,?,?,?,?)";
        //CLAUSULA(ALGO QUE VC QUER FAZER NO BANCO,REQUISIÇÃO)
        //PREPARED STATEMENT E UMA CLAUSULA PREPARADA
        
        try {

            PreparedStatement estado =connection.prepareStatement(sql);
            //VAI DAR CERTO,E O ESTADO JA TA PRONTO E COM ISSO A OPERAÇÃO COM O BANCO VAI DAR CERTO
            estado.setString(1,choco.getId_chocolate());
            estado.setDouble(2,choco.getPeso());
            estado.setDouble(3,choco.getValorPorKg());
            estado.setString(4,choco.getTipoDoChocolate());
            estado.setDouble(5,choco.getValorDeVenda());
            estado.setInt(6,choco.getTemperaturaIdeal());
            estado.setDouble(7,choco.getQtdeDeLeite()); 

            estado.execute();
            estado.close();
            System.out.println("CHOCOLATE adicionado!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"NÃO FOI POSSIVEL ADICIONAR O CHOCOLATE\nTENTE NOVAMENTE!");
                System.out.println("ERRO: "+e.toString());
        }
    }
     
     
      public Chocolate RETORNAR(String idchoco){
        try {            
            String sql = "select * from CHOCOLATE where id_chocolate='"+idchoco+"'";
            System.out.println(sql);
            try (Statement statement = this.connection.createStatement()) {
                
                ResultSet resultSet = statement.executeQuery(sql);
                resultSet.next();
                
                Chocolate choco = new Chocolate(resultSet.getString("id_chocolate"), resultSet.getDouble("peso"),resultSet.getDouble("valorPorKg"),resultSet.getString("tipoDoChocolate"),resultSet.getDouble("valorDeVenda"),resultSet.getInt("temperaturaIdeal"), resultSet.getDouble("qtdeDeLeite"));
                return (choco);
            }
        } catch (SQLException e) {
                    System.out.println("ERRO: "+e.toString());
            return (null);
        }
    }
    
     public void UPTADE(Chocolate choco){
        String sql = "update CHOCOLATE set peso=?, valorPorKg=?, tipoDoChocolate=?,valorDeVenda=?,temperaturaIdeal=?, qtdeDeLeite=? where id_chocolate=?";
        PreparedStatement estado;
         System.out.println(""+sql);
        try {
            //VAI DAR CERTO,E O ESTADO JA TA PRONTO E COM ISSO A OPERAÇÃO COM O BANCO VAI DAR CERTO
             estado =connection.prepareStatement(sql);
            
            estado.setDouble(1,choco.getPeso());
            estado.setDouble(2,choco.getValorPorKg());
            estado.setString(3,choco.getTipoDoChocolate());
            estado.setDouble(4,choco.getValorDeVenda());
            estado.setInt(5,choco.getTemperaturaIdeal());
            estado.setDouble(6,choco.getQtdeDeLeite()); 
            estado.setString(7,choco.getId_chocolate());
            estado.execute();
            estado.close();
        }catch (SQLException e) {
                System.out.println("ERRO: "+e.toString());
        }
    }
    
      public void REMOVER(String id_choco) throws SQLException{
        PreparedStatement estado;
        String sql = "delete from CHOCOLATE where id_chocolate=?";
        try{
            estado = this.connection.prepareStatement(sql);
            estado.setString(1,id_choco);
            estado.execute();
            estado.close();
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"NÃO FOI POSSIVEL REMOVER O CHOCOLATE\nTENTE NOVAMENTE!");
                System.out.println("ERRO: "+e.toString());
        }
    }
      
    
    
     public ArrayList<Chocolate> TODOSOSCHOCOS(){
         
         
        ArrayList<Chocolate> chocos = new ArrayList();
        try {            
            String sql = "SELECT * FROM CHOCOLATE";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                   Chocolate choco;
                   choco = new Chocolate(resultSet.getString("id_chocolate"), resultSet.getDouble("peso"),resultSet.getDouble("valorPorKg"),resultSet.getString("tipoDoChocolate"),resultSet.getDouble("valorDeVenda"),resultSet.getInt("temperaturaIdeal"), resultSet.getDouble("qtdeDeLeite"));
                   chocos.add(choco);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (chocos);
    }
    
      public ArrayList<Chocolate> CHOCOSCAROS(){
        
         
        ArrayList<Chocolate> chocos = new ArrayList();
        try {            
            String sql = "SELECT * FROM CHOCOLATE where valorDeVenda in (select MAX(valorDeVenda) FROM CHOCOLATE);";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                   Chocolate choco;
                   choco = new Chocolate(resultSet.getString("id_chocolate"), resultSet.getDouble("peso"),resultSet.getDouble("valorPorKg"),resultSet.getString("tipoDoChocolate"),resultSet.getDouble("valorDeVenda"),resultSet.getInt("temperaturaIdeal"), resultSet.getDouble("qtdeDeLeite"));
                   chocos.add(choco);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (chocos);
    }
    
    
    
     public ArrayList<Chocolate> CHOCOSVALORPORKG(){
         
         
        ArrayList<Chocolate> chocos = new ArrayList();
        try {            
            String sql = "SELECT * FROM CHOCOLATE where valorPorKg in (select MAX(valorPorKg) FROM CHOCOLATE);";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                   Chocolate choco;
                   choco = new Chocolate(resultSet.getString("id_chocolate"), resultSet.getDouble("peso"),resultSet.getDouble("valorPorKg"),resultSet.getString("tipoDoChocolate"),resultSet.getDouble("valorDeVenda"),resultSet.getInt("temperaturaIdeal"), resultSet.getDouble("qtdeDeLeite"));
                   chocos.add(choco);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (chocos);
    }
    
    
    
    
    
}

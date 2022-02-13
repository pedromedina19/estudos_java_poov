/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import CONEXAO.Conexao;
import PedroC.Pedidos;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author PEDRO
 */
public class DAOPEDIDO {
    
     Connection connection;

    //AUTOMATICAMENTE VAI TER A CONEXÃO PRONTA NA HORA QUE CRIAR UM DAO
    //CONSTRUTOR!
        public DAOPEDIDO() throws SQLException, ClassNotFoundException {
        this.connection = Conexao.getConexao();
    }
        
         //INSERIR
    public void INSERIRPEDIDOBANCO(Pedidos novopedido) {

        String sql = "insert into PEDIDO(datadopedido,prazoParaEntrega,fk_cpf) values(?,?,?)";
        //CLAUSULA(ALGO QUE VC QUER FAZER NO BANCO,REQUISIÇÃO)
        //PREPARED STATEMENT E UMA CLAUSULA PREPARADA

        try {

            PreparedStatement estado =connection.prepareStatement(sql);
            //VAI DAR CERTO,E O ESTADO JA TA PRONTO E COM ISSO A OPERAÇÃO COM O BANCO VAI DAR CERTO 
            estado.setTimestamp(1,Timestamp.valueOf(novopedido.getData()));
            estado.setTimestamp(2,Timestamp.valueOf(novopedido.getPrazoParaEntrega()));         
            estado.setString(3,novopedido.getFk_cpf());
      
            estado.execute();
            estado.close();
            System.out.println("PEDIDO ADICIONADO!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"NÃO FOI POSSIVEL ADICIONAR O PEDIDO\nTENTE NOVAMENTE!");
                System.out.println("ERRO: "+e.toString());
        }
    }
    
    
      //UPTADE FAZ A TROCA UTILZIANDO O ID COMO BASE
    public void UPTADE(Pedidos novopedido ){
        String sql = "update PEDIDO set  datadopedido=?,prazoParaEntrega=?,fk_cpf=? where id_pedido=?";
        PreparedStatement estado;
        try {
            //VAI DAR CERTO,E O ESTADO JA TA PRONTO E COM ISSO A OPERAÇÃO COM O BANCO VAI DAR CERTO
            estado =connection.prepareStatement(sql);
     
            estado.setTimestamp(1,Timestamp.valueOf(novopedido.getData()));
            estado.setTimestamp(2,Timestamp.valueOf(novopedido.getPrazoParaEntrega()));         
            estado.setString(3,novopedido.getFk_cpf());
            estado.setInt(4,novopedido.getId_pedido());
           
            estado.execute();
            estado.close();
        }catch (SQLException e) {
                System.out.println("ERRO: "+e.toString());
        }
    }
    
    
    
     public void REMOVERPEDIDO (int IdP)
    {
        try
        {
            try (PreparedStatement es = this.connection.prepareStatement("delete from PEDIDO where (id_pedido =?)")) 
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
    
    //FUNÇÃO DE BUSCAR UM DETERMINADO CLIENTE
    public Pedidos RETORNARPEDIDO(int id_pedido){
        try {            
            String sql = "select * from PEDIDO where id_pedido="+id_pedido;
            System.out.println(sql);
            try (Statement statement = this.connection.createStatement()) {
                
                ResultSet resultSet = statement.executeQuery(sql);
                resultSet.next();
                
                Timestamp dt=resultSet.getTimestamp("datadopedido");
                  Timestamp pl=resultSet.getTimestamp("prazoParaEntrega");
                Pedidos pedido = new Pedidos(resultSet.getInt("id_pedido"), dt.toLocalDateTime(),pl.toLocalDateTime(),resultSet.getString("fk_cpf"));
                return (pedido);
            }
        } catch (SQLException e) {
                    System.out.println("ERRO: "+e.toString());
            return (null);
        }
    }
    
  
    //FUNÇÃO QUE RETORNA A LISTA COM TODOS OS CLIENTES
    public ArrayList<Pedidos> TODOSOSPEDIDOS(){
        ArrayList<Pedidos> clientes = new ArrayList();
        try {            
            String sql = "SELECT * FROM PEDIDO";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                 Timestamp dt=resultSet.getTimestamp("datadopedido");
                  Timestamp pl=resultSet.getTimestamp("prazoParaEntrega");
                Pedidos pedido = new Pedidos(resultSet.getInt("id_pedido"), dt.toLocalDateTime(),pl.toLocalDateTime(),resultSet.getString("fk_cpf"));
                    clientes.add(pedido);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (clientes);
    }
    
    public ArrayList<Pedidos> TODOSOSPEDIDOSANTIGOSDESTECLIENTE(String cpf){
        ArrayList<Pedidos> clientes = new ArrayList();
        try {            
            String sql = "SELECT * FROM PEDIDO WHERE datadopedido in (select MIN(datadopedido) FROM 	PEDIDO) and fk_cpf='"+cpf+"'";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                 Timestamp dt=resultSet.getTimestamp("datadopedido");
                  Timestamp pl=resultSet.getTimestamp("prazoParaEntrega");
                Pedidos pedido = new Pedidos(resultSet.getInt("id_pedido"), dt.toLocalDateTime(),pl.toLocalDateTime(),resultSet.getString("fk_cpf"));
                    clientes.add(pedido);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (clientes);
    }
    
    
    
    public int MAIORIDPEDIDO() throws SQLException
    {
        int idmaximo=0;
        
        PreparedStatement instrucao;
        
        ResultSet res;
        
        
        String sql="select MAX(id_pedido) from PEDIDO";
        
        try
        {
            instrucao=this.connection.prepareStatement(sql);
            res=instrucao.executeQuery();
            
            while(res.next())
            {
                idmaximo=res.getInt("MAX(id_pedido)");
                
            }
            
        }
        catch(SQLException e)
        {
            System.out.println("ERRO: "+e.toString());
        }
        
        
        
        
        
        return idmaximo;
    }    
     
    
    
    
    
}

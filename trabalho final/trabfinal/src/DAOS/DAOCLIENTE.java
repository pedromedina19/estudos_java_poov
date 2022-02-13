package DAOS;

import PedroC.Cliente;
import CONEXAO.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class DAOCLIENTE {
    
    Connection connection;

    //AUTOMATICAMENTE VAI TER A CONEXÃO PRONTA NA HORA QUE CRIAR UM DAO
    //CONSTRUTOR!
    public DAOCLIENTE() throws SQLException, ClassNotFoundException {
        this.connection = Conexao.getConexao();
    }
    
    
    //INSERIR
    public void INSERIRCLIENTEBANCO(Cliente cliente) {

        String sql = "insert into CLIENTES(cpf,nome,telefone,endereco,end_instagram,end_facebook,cartaoDeCredito) values(?,?,?,?,?,?,?)";
        //CLAUSULA(ALGO QUE VC QUER FAZER NO BANCO,REQUISIÇÃO)
        //PREPARED STATEMENT E UMA CLAUSULA PREPARADA

        try {

            PreparedStatement estado =connection.prepareStatement(sql);
            //VAI DAR CERTO,E O ESTADO JA TA PRONTO E COM ISSO A OPERAÇÃO COM O BANCO VAI DAR CERTO
            estado.setString(1,cliente.getCpf());
            estado.setString(2,cliente.getNome());
            estado.setString(3,cliente.getTelefone());
            estado.setString(4,cliente.getEndereco());
            estado.setString(5,cliente.getEnd_instagram());
            estado.setString(6,cliente.getEnd_facebook());
            estado.setString(7,cliente.getCartaoDeCredito());
            estado.execute();
            estado.close();
            System.out.println("Cliente adicionado!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"NÃO FOI POSSIVEL ADICIONAR O CLIENTE\nTENTE NOVAMENTE!");
                System.out.println("ERRO: "+e.toString());
        }
    }
    
    
     //UPTADE FAZ A TROCA UTILZIANDO O CPF COMO BASE
    public void UPTADE(Cliente cliente ){
        String sql = "update CLIENTES set nome=?,telefone=?,endereco=?,end_instagram=?,end_facebook=?,cartaoDeCredito=? where cpf=?";
        PreparedStatement estado;
        try {
            //VAI DAR CERTO,E O ESTADO JA TA PRONTO E COM ISSO A OPERAÇÃO COM O BANCO VAI DAR CERTO
             estado =connection.prepareStatement(sql);
     
            estado.setString(1,cliente.getNome());
            estado.setString(2,cliente.getTelefone());
            estado.setString(3,cliente.getEndereco());
            estado.setString(4,cliente.getEnd_instagram());
            estado.setString(5,cliente.getEnd_facebook());
            estado.setString(6,cliente.getCartaoDeCredito());         
            estado.setString(7,cliente.getCpf());
            
            estado.execute();
            estado.close();
        }catch (SQLException e) {
                System.out.println("ERRO: "+e.toString());
        }
    }
    
    
    
    //FUNÇÃO DE REMOVER
    public void REMOVER(String cpf) throws SQLException{
        PreparedStatement estado;
        String sql = "delete from CLIENTES where cpf=?";
        try{
            estado = this.connection.prepareStatement(sql);
            estado.setString(1,cpf);
            estado.execute();
            estado.close();
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"NÃO FOI POSSIVEL REMOVER O CLIENTE\nTENTE NOVAMENTE!");
                System.out.println("ERRO: "+e.toString());
        }
    }
    
    //FUNÇÃO DE BUSCAR UM DETERMINADO CLIENTE
    public Cliente RETORNAR(String cpf){
        try {            
            String sql = "select * from CLIENTES where cpf='"+cpf+"'";
            System.out.println(sql);
            try (Statement statement = this.connection.createStatement()) {
                
                ResultSet resultSet = statement.executeQuery(sql);
                resultSet.next();
                Cliente cliente = new Cliente(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("telefone"),resultSet.getString("endereco"),resultSet.getString("end_instagram"),resultSet.getString("end_facebook"),resultSet.getString("cartaoDeCredito"));
                return (cliente);
            }
        } catch (SQLException e) {
                    System.out.println("ERRO: "+e.toString());
            return (null);
        }
    }
    
  
    //FUNÇÃO QUE RETORNA A LISTA COM TODOS OS CLIENTES
    public ArrayList<Cliente> TODOSOSCLIENTES(){
        ArrayList<Cliente> clientes = new ArrayList();
        try {            
            String sql = "SELECT * FROM CLIENTES";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    Cliente cliente;
                    cliente = new Cliente(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("telefone"),resultSet.getString("endereco"),resultSet.getString("end_instagram"),resultSet.getString("end_facebook"),resultSet.getString("cartaoDeCredito"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (clientes);
    }
    
    
    
     public ArrayList<Cliente> NOMECIMA(){
        ArrayList<Cliente> clientes = new ArrayList();
        try {            
            String sql = "select * from CLIENTES order by nome ASC";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    Cliente cliente;
                    cliente = new Cliente(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("telefone"),resultSet.getString("endereco"),resultSet.getString("end_instagram"),resultSet.getString("end_facebook"),resultSet.getString("cartaoDeCredito"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (clientes);
    }
     
     
      public ArrayList<Cliente> MESMONOME(String Nome){
        ArrayList<Cliente> clientes = new ArrayList();
        try {            
            String sql = "select * from CLIENTES where nome='"+Nome+"'";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    Cliente cliente;
                    cliente = new Cliente(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("telefone"),resultSet.getString("endereco"),resultSet.getString("end_instagram"),resultSet.getString("end_facebook"),resultSet.getString("cartaoDeCredito"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (clientes);
    }
     
     
     
     public ArrayList<Cliente> NOMEBAIXO(){
        ArrayList<Cliente> clientes = new ArrayList();
        try {            
            String sql = "select * from CLIENTES order by nome DESC";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    Cliente cliente;
                    cliente = new Cliente(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("telefone"),resultSet.getString("endereco"),resultSet.getString("end_instagram"),resultSet.getString("end_facebook"),resultSet.getString("cartaoDeCredito"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (clientes);
    }
    public ArrayList<Cliente> ENDERECOCIMA(){
        ArrayList<Cliente> clientes = new ArrayList();
        try {            
            String sql = "select * from CLIENTES order by endereco ASC";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    Cliente cliente;
                    cliente = new Cliente(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("telefone"),resultSet.getString("endereco"),resultSet.getString("end_instagram"),resultSet.getString("end_facebook"),resultSet.getString("cartaoDeCredito"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (clientes);
    }
   
    public ArrayList<Cliente> ENDERECOBAIXO(){
        ArrayList<Cliente> clientes = new ArrayList();
        try {            
            String sql = "select * from CLIENTES order by endereco DESC";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    Cliente cliente;
                    cliente = new Cliente(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("telefone"),resultSet.getString("endereco"),resultSet.getString("end_instagram"),resultSet.getString("end_facebook"),resultSet.getString("cartaoDeCredito"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (clientes);
    }
      public ArrayList<Cliente> INSTAGRAMCIMA(){
        ArrayList<Cliente> clientes = new ArrayList();
        try {            
            String sql = "select * from CLIENTES order by end_instagram ASC";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    Cliente cliente;
                    cliente = new Cliente(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("telefone"),resultSet.getString("endereco"),resultSet.getString("end_instagram"),resultSet.getString("end_facebook"),resultSet.getString("cartaoDeCredito"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (clientes);
    }
     
    public ArrayList<Cliente> INSTAGRABAIXO(){
        ArrayList<Cliente> clientes = new ArrayList();
        try {            
            String sql = "select * from CLIENTES order by end_instagram DESC";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    Cliente cliente;
                    cliente = new Cliente(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("telefone"),resultSet.getString("endereco"),resultSet.getString("end_instagram"),resultSet.getString("end_facebook"),resultSet.getString("cartaoDeCredito"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (clientes);
    }
    
    
    public ArrayList<Cliente> FACEBOOKCIMA(){
        ArrayList<Cliente> clientes = new ArrayList();
        try {            
            String sql = "select * from CLIENTES order by end_facebook ASC";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    Cliente cliente;
                    cliente = new Cliente(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("telefone"),resultSet.getString("endereco"),resultSet.getString("end_instagram"),resultSet.getString("end_facebook"),resultSet.getString("cartaoDeCredito"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (clientes);
    }
    
    public ArrayList<Cliente> FACEBOOKBAIXO(){
        ArrayList<Cliente> clientes = new ArrayList();
        try {            
            String sql = "select * from CLIENTES order by end_facebook DESC";
            try (Statement statement = this.connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()) {
                    Cliente cliente;
                    cliente = new Cliente(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("telefone"),resultSet.getString("endereco"),resultSet.getString("end_instagram"),resultSet.getString("end_facebook"),resultSet.getString("cartaoDeCredito"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
                 System.out.println("ERRO: "+e.toString());
            return (null);
        }
        
        return (clientes);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
